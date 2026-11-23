package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.AdultBabyModelPair;
import aqario.fowlplay.client.render.entity.model.BabyPenguinModel;
import aqario.fowlplay.client.render.entity.model.PenguinModel;
import aqario.fowlplay.common.entity.PenguinEntity;
import aqario.fowlplay.core.FowlPlay;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;
public class PenguinRenderer extends MobRenderer<PenguinEntity, BirdRenderState<PenguinEntity>, PenguinModel> {
    private static final Identifier TEXTURE = FowlPlay.id("textures/entity/penguin/penguin.png");
    private static final Identifier BABY_TEXTURE = FowlPlay.id("textures/entity/penguin/penguin_baby.png");
    private final AdultBabyModelPair<PenguinModel> modelPair;

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.MODEL_LAYER)), 0.3f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.145, -0.1475)
        ));
        this.modelPair = bakeModels(context);
    }

    private static AdultBabyModelPair<PenguinModel> bakeModels(EntityRendererProvider.Context context) {
        return new AdultBabyModelPair<>(
            new PenguinModel(context.bakeLayer(PenguinModel.MODEL_LAYER)),
            new BabyPenguinModel(context.bakeLayer(BabyPenguinModel.MODEL_LAYER))
        );
    }

    @Override
    public void submit(BirdRenderState<PenguinEntity> state, PoseStack matrices, SubmitNodeCollector collectors, CameraRenderState camera) {

        this.model = this.modelPair.getModel(state.entity.isBaby());
        if(state.entity.isBaby()) {
            matrices.scale(0.8F, 0.8F, 0.8F);
        }
        super.submit(state, matrices, collectors, camera);
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<PenguinEntity> state) {
        return state.entity.isBaby() ? BABY_TEXTURE : TEXTURE;
    }

    @Override
    protected void scale(BirdRenderState<PenguinEntity> state, PoseStack matrices) {
        super.scale(state, matrices);

        String name = ChatFormatting.stripFormatting(state.entity.getName().getString());
        if(name.equalsIgnoreCase("rico")) {
            matrices.scale(1.1F, 1F, 1F);
        }
        if(name.equalsIgnoreCase("skipper")) {
            matrices.scale(1.25F, 0.9F, 1F);
        }
        if(name.equalsIgnoreCase("kowalski")) {
            matrices.scale(1F, 1.1F, 1F);
        }
        if(name.equalsIgnoreCase("private")) {
            matrices.scale(1.2F, 0.85F, 1F);
        }
    }

    @Override
    public BirdRenderState<PenguinEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(PenguinEntity entity, BirdRenderState<PenguinEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
