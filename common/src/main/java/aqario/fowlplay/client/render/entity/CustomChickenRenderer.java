package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.model.AdultBabyModelPair;
import aqario.fowlplay.client.render.entity.model.CustomBabyChickenModel;
import aqario.fowlplay.client.render.entity.model.CustomChickenModel;
import aqario.fowlplay.common.entity.ChickenVariant;
import aqario.fowlplay.core.platform.DataAttachmentHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.chicken.Chicken;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;
public class CustomChickenRenderer extends MobRenderer<Chicken, BirdRenderState<Chicken>, CustomChickenModel> {
    private final AdultBabyModelPair<CustomChickenModel> modelPair;

    public CustomChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new CustomChickenModel(context.bakeLayer(CustomChickenModel.MODEL_LAYER)), 0.3f);
        this.modelPair = bakeModels(context);
    }

    private static AdultBabyModelPair<CustomChickenModel> bakeModels(EntityRendererProvider.Context context) {
        return new AdultBabyModelPair<>(
            new CustomChickenModel(context.bakeLayer(CustomChickenModel.MODEL_LAYER)),
            new CustomBabyChickenModel(context.bakeLayer(CustomBabyChickenModel.MODEL_LAYER))
        );
    }

    @Override
    public void submit(BirdRenderState<Chicken> state, PoseStack matrices, SubmitNodeCollector collectors, CameraRenderState camera) {

        this.model = this.modelPair.getModel(state.entity.isBaby());
        if(state.entity.isBaby()) {
            matrices.scale(0.8F, 0.8F, 0.8F);
        }
        super.submit(state, matrices, collectors, camera);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Identifier getTextureLocation(BirdRenderState<Chicken> state) {
        return DataAttachmentHelper.getChickenVariant(state.entity).value().texture(state.entity.isBaby());
    }

    @Override
    public BirdRenderState<Chicken> createRenderState() {
        return new BirdRenderState<>();
    }

    @Override
    public void extractRenderState(Chicken entity, BirdRenderState<Chicken> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
    }
}
