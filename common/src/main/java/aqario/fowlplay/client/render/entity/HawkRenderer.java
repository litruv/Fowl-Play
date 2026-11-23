package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.HawkModel;
import aqario.fowlplay.common.entity.HawkEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class HawkRenderer extends MobRenderer<HawkEntity, BirdRenderState<HawkEntity>, HawkModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/hawk/red_tailed_hawk.png");

    public HawkRenderer(EntityRendererProvider.Context context) {
        super(context, new HawkModel(context.bakeLayer(HawkModel.MODEL_LAYER)), 0.3f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.05375, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<HawkEntity> state) {
        return TEXTURE;
    }

    @Override
    public BirdRenderState<HawkEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(HawkEntity entity, BirdRenderState<HawkEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
