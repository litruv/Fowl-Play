package aqario.fowlplay.common.entity;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.scores.PlayerTeam;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public abstract class TameableBirdEntity extends TrustingBirdEntity implements OwnableEntity {
    protected static final EntityDataAccessor<Byte> TAMEABLE_FLAGS = SynchedEntityData.defineId(TameableBirdEntity.class, EntityDataSerializers.BYTE);
    protected static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> OWNER = SynchedEntityData.defineId(TameableBirdEntity.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
    private boolean sitting;

    protected TameableBirdEntity(EntityType<? extends BirdEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(TAMEABLE_FLAGS, (byte) 0);
        builder.define(OWNER, Optional.empty());
    }

    @Override
    public void addAdditionalSaveData(ValueOutput nbt) {
        super.addAdditionalSaveData(nbt);
        EntityReference<LivingEntity> owner = this.getOwnerReference();
        if(owner != null) {
            nbt.store("owner", EntityReference.codec(), owner);
        }

        nbt.putBoolean("sitting", this.sitting);
    }

    @Override
    public void readAdditionalSaveData(ValueInput nbt) {
        super.readAdditionalSaveData(nbt);
        EntityReference<LivingEntity> owner = EntityReference.readWithOldOwnerConversion(nbt, "owner", this.level());
        if(owner != null) {
            this.setOwnerUuid(owner.getUUID());
            this.setTamed(true);
        }

        this.sitting = nbt.getBooleanOr("sitting", false);
        this.setInSittingPose(this.sitting);
    }

    protected void showEmoteParticle(boolean positive) {
        ParticleOptions particleEffect = ParticleTypes.HEART;
        if(!positive) {
            particleEffect = ParticleTypes.SMOKE;
        }

        for(int i = 0; i < 7; ++i) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.level().addParticle(particleEffect, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d, e, f);
        }
    }

    @Override
    public void handleEntityEvent(byte status) {
        if(status == EntityEvent.TAMING_SUCCEEDED) {
            this.showEmoteParticle(true);
        }
        else if(status == EntityEvent.TAMING_FAILED) {
            this.showEmoteParticle(false);
        }
        else {
            super.handleEntityEvent(status);
        }
    }

    public boolean isTamed() {
        return (this.entityData.get(TAMEABLE_FLAGS) & 4) != 0;
    }

    public void setTamed(boolean tamed) {
        byte b = this.entityData.get(TAMEABLE_FLAGS);
        if(tamed) {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (b | 4));
        }
        else {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (b & -5));
        }

        this.onTamedChanged();
    }

    protected void onTamedChanged() {
    }

    public boolean isInSittingPose() {
        return (this.entityData.get(TAMEABLE_FLAGS) & 1) != 0;
    }

    public void setInSittingPose(boolean inSittingPose) {
        byte b = this.entityData.get(TAMEABLE_FLAGS);
        if(inSittingPose) {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (b | 1));
        }
        else {
            this.entityData.set(TAMEABLE_FLAGS, (byte) (b & -2));
        }
    }

    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        this.setSitting(false);
        return super.hurtServer(world, source, amount);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getOwnerUUID() != null) {
            if(!this.trustsUuid(this.getOwnerUUID())) {
                this.addTrustedUuid(this.getOwnerUUID());
            }
            if(!this.isPersistenceRequired()) {
                this.setPersistenceRequired();
            }
        }
        if(this.isFlying()) {
            this.setSitting(false);
        }
        if(!this.level().isClientSide()) {
            if(this.isSitting()) {
                this.getNavigation().stop();
                this.setInSittingPose(true);
            }
            else {
                this.setInSittingPose(false);
            }
        }
    }

    @Override
    public EntityReference<LivingEntity> getOwnerReference() {
        return this.entityData.get(OWNER).orElse(null);
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.entityData.get(OWNER).map(EntityReference::getUUID).orElse(null);
    }

    public void setOwnerUuid(@Nullable UUID uuid) {
        this.entityData.set(OWNER, uuid == null ? Optional.empty() : Optional.of(EntityReference.<LivingEntity>of(uuid)));
    }

    public void setOwner(Player player) {
        this.setTamed(true);
        this.setOwnerUuid(player.getUUID());
        if(player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.TAME_ANIMAL.trigger(serverPlayer, this);
        }
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !this.isOwner(target) && super.canAttack(target);
    }

    public boolean isOwner(LivingEntity entity) {
        return entity == this.getOwner();
    }

    @Override
    public PlayerTeam getTeam() {
        if(this.isTamed()) {
            LivingEntity livingEntity = this.getOwner();
            if(livingEntity != null) {
                return livingEntity.getTeam();
            }
        }

        return super.getTeam();
    }

    @Override
    public void die(DamageSource source) {
        if(this.level() instanceof ServerLevel world && world.getGameRules().get(GameRules.SHOW_DEATH_MESSAGES) && this.getOwner() instanceof ServerPlayer player) {
            player.sendSystemMessage(this.getCombatTracker().getDeathMessage());
        }

        super.die(source);
    }

    public boolean isSitting() {
        return this.sitting;
    }

    public void setSitting(boolean sitting) {
        this.sitting = sitting;
    }
}
