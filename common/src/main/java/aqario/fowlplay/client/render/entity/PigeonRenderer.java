package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.layer.PigeonBundleLayer;
import aqario.fowlplay.client.render.entity.model.PigeonModel;
import aqario.fowlplay.common.entity.PigeonEntity;
import aqario.fowlplay.core.FowlPlay;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
public class PigeonRenderer extends MobRenderer<PigeonEntity, BirdRenderState<PigeonEntity>, PigeonModel> {
    private static final Identifier MARTHA_TEXTURE = FowlPlay.id("textures/entity/pigeon/martha.png");

    public PigeonRenderer(EntityRendererProvider.Context context) {
        super(context, new PigeonModel(context.bakeLayer(PigeonModel.MODEL_LAYER)), 0.2f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.0225, -0.1475)
        ));
        this.addLayer(new PigeonBundleLayer(this, context.getEntityRenderDispatcher().getItemInHandRenderer()));
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<PigeonEntity> state) {
        String string = ChatFormatting.stripFormatting(state.entity.getName().getString());
        if ("Martha".equals(string)) {
            return MARTHA_TEXTURE;
        }
        return state.entity.getVariant().value().texture();
    }

    @Override
    public BirdRenderState<PigeonEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(PigeonEntity entity, BirdRenderState<PigeonEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
