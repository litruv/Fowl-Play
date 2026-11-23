package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.SparrowModel;
import aqario.fowlplay.common.entity.SparrowEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class SparrowRenderer extends MobRenderer<SparrowEntity, BirdRenderState<SparrowEntity>, SparrowModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/sparrow/house_sparrow.png");

    public SparrowRenderer(EntityRendererProvider.Context context) {
        super(context, new SparrowModel(context.bakeLayer(SparrowModel.MODEL_LAYER)), 0.15f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.085, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<SparrowEntity> state) {
        return TEXTURE;
    }

    @Override
    public BirdRenderState<SparrowEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(SparrowEntity entity, BirdRenderState<SparrowEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
