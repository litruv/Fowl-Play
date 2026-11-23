package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.CrowModel;
import aqario.fowlplay.common.entity.CrowEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class CrowRenderer extends MobRenderer<CrowEntity, BirdRenderState<CrowEntity>, CrowModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/crow/crow.png");

    public CrowRenderer(EntityRendererProvider.Context context) {
        super(context, new CrowModel(context.bakeLayer(CrowModel.MODEL_LAYER)), 0.15f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.0225, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<CrowEntity> state) {
        return TEXTURE;
    }

    @Override
    public BirdRenderState<CrowEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(CrowEntity entity, BirdRenderState<CrowEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
