package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.RobinModel;
import aqario.fowlplay.common.entity.RobinEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class RobinRenderer extends MobRenderer<RobinEntity, BirdRenderState<RobinEntity>, RobinModel> {
    public RobinRenderer(EntityRendererProvider.Context context) {
        super(context, new RobinModel(context.bakeLayer(RobinModel.MODEL_LAYER)), 0.15f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.085, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<RobinEntity> state) {
        return FowlPlay.id("textures/state.entity/robin/" + state.entity.getVariant().getId() + "_robin.png");
    }

    @Override
    public BirdRenderState<RobinEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(RobinEntity entity, BirdRenderState<RobinEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
