package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.BirdHeldItemLayer;
import aqario.fowlplay.client.render.entity.model.AdultBabyModelPair;
import aqario.fowlplay.client.render.entity.model.BabyGooseModel;
import aqario.fowlplay.client.render.entity.model.DomesticGooseModel;
import aqario.fowlplay.client.render.entity.model.GooseModel;
import aqario.fowlplay.common.entity.GooseEntity;
import aqario.fowlplay.common.entity.GooseVariant;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;
public class GooseRenderer extends MobRenderer<GooseEntity, BirdRenderState<GooseEntity>, GooseModel> {
    private final Map<GooseVariant.ModelType, AdultBabyModelPair<GooseModel>> models;

    public GooseRenderer(EntityRendererProvider.Context context) {
        super(context, new GooseModel(context.bakeLayer(GooseModel.MODEL_LAYER)), 0.3f);
        this.addLayer(new BirdHeldItemLayer<>(
            this,
            context.getEntityRenderDispatcher().getItemInHandRenderer(),
            new Vec3(0.0, -0.05375, -0.1475)
        ));
        this.models = bakeModels(context);
    }

    private static Map<GooseVariant.ModelType, AdultBabyModelPair<GooseModel>> bakeModels(EntityRendererProvider.Context context) {
        return Map.of(
            GooseVariant.ModelType.WILD,
            new AdultBabyModelPair<>(
                new GooseModel(context.bakeLayer(GooseModel.MODEL_LAYER)),
                new BabyGooseModel(context.bakeLayer(BabyGooseModel.MODEL_LAYER))
            ),
            GooseVariant.ModelType.DOMESTIC,
            new AdultBabyModelPair<>(
                new DomesticGooseModel(context.bakeLayer(DomesticGooseModel.MODEL_LAYER)),
                new BabyGooseModel(context.bakeLayer(BabyGooseModel.MODEL_LAYER))
            )
        );
    }

    @Override
    public void submit(BirdRenderState<GooseEntity> state, PoseStack matrices, SubmitNodeCollector collectors, CameraRenderState camera) {

        this.model = this.models.get(state.entity.getVariant().value().modelType())
            .getModel(state.entity.isBaby());
        super.submit(state, matrices, collectors, camera);
    }

    @Override
    public Identifier getTextureLocation(BirdRenderState<GooseEntity> state) {
        return state.entity.getVariant().value().texture(state.entity.isBaby());
    }

    @Override
    public BirdRenderState<GooseEntity> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(GooseEntity entity, BirdRenderState<GooseEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
