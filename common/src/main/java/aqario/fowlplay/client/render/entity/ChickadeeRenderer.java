package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.ChickadeeModel;
import aqario.fowlplay.common.entity.ChickadeeEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class ChickadeeRenderer extends MobRenderer<ChickadeeEntity, BirdRenderState<ChickadeeEntity>, ChickadeeModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/chickadee/black_capped_chickadee.png");

    public ChickadeeRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickadeeModel(context.bakeLayer(ChickadeeModel.MODEL_LAYER)), 0.15f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.085, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<ChickadeeEntity> state) {
        return TEXTURE;
    }

    @Override
    public BirdRenderState<ChickadeeEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(ChickadeeEntity entity, BirdRenderState<ChickadeeEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
