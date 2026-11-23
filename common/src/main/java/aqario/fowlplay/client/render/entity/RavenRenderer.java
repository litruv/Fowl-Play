package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.RavenModel;
import aqario.fowlplay.common.entity.RavenEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class RavenRenderer extends MobRenderer<RavenEntity, BirdRenderState<RavenEntity>, RavenModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/raven/raven.png");

    public RavenRenderer(EntityRendererProvider.Context context) {
        super(context, new RavenModel(context.bakeLayer(RavenModel.MODEL_LAYER)), 0.3f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.05375, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<RavenEntity> state) {
        return TEXTURE;
    }

    @Override
    public BirdRenderState<RavenEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(RavenEntity entity, BirdRenderState<RavenEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
