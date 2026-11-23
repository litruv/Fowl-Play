package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.BlueJayModel;
import aqario.fowlplay.common.entity.BlueJayEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class BlueJayRenderer extends MobRenderer<BlueJayEntity, BirdRenderState<BlueJayEntity>, BlueJayModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/blue_jay/blue_jay.png");

    public BlueJayRenderer(EntityRendererProvider.Context context) {
        super(context, new BlueJayModel(context.bakeLayer(BlueJayModel.MODEL_LAYER)), 0.15f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.085, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<BlueJayEntity> state) {
        return TEXTURE;
    }

    @Override
    public BirdRenderState<BlueJayEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(BlueJayEntity entity, BirdRenderState<BlueJayEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
