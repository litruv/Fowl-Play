package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.DuckModel;
import aqario.fowlplay.common.entity.DuckEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class DuckRenderer extends MobRenderer<DuckEntity, BirdRenderState<DuckEntity>, DuckModel> {
    private static final Identifier QUACKERS_TEXTURE = FowlPlay.id("textures/entity/duck/quackers.png");

    public DuckRenderer(EntityRendererProvider.Context context) {
        super(context, new DuckModel(context.bakeLayer(DuckModel.MODEL_LAYER)), 0.3f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.05375, -0.1475)
        ));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<DuckEntity> state) {
        String customName = ChatFormatting.stripFormatting(state.entity.getName().getString());
        if(customName.equals("Quackers")) {
            return QUACKERS_TEXTURE;
        }
        return state.entity.getVariant().value().texture(false);
    }

    @Override
    public BirdRenderState<DuckEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(DuckEntity entity, BirdRenderState<DuckEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
