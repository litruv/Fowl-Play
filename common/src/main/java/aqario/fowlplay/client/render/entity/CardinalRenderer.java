package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.CardinalModel;
import aqario.fowlplay.common.entity.CardinalEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class CardinalRenderer extends MobRenderer<CardinalEntity, BirdRenderState<CardinalEntity>, CardinalModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/cardinal/cardinal.png");

    public CardinalRenderer(EntityRendererProvider.Context context) {
        super(context, new CardinalModel(context.bakeLayer(CardinalModel.MODEL_LAYER)), 0.15f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.085, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<CardinalEntity> state) {
        return TEXTURE;
    }

    @Override
    public BirdRenderState<CardinalEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(CardinalEntity entity, BirdRenderState<CardinalEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
