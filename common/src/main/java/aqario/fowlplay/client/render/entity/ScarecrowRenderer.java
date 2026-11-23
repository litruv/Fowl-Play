package aqario.fowlplay.client.render.entity;

import aqario.fowlplay.client.render.entity.layer.StuckArrowsLayer;
import aqario.fowlplay.client.render.entity.model.ScarecrowArmorModel;
import aqario.fowlplay.client.render.entity.model.ScarecrowModel;
import aqario.fowlplay.client.render.entity.state.ScarecrowRenderState;
import aqario.fowlplay.common.entity.ScarecrowEntity;
import aqario.fowlplay.core.FowlPlay;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.Identifier;

public class ScarecrowRenderer extends LivingEntityRenderer<ScarecrowEntity, ScarecrowRenderState<ScarecrowEntity>, ScarecrowModel> {
    public static final Identifier TEXTURE = FowlPlay.id("textures/entity/scarecrow/scarecrow.png");

    public ScarecrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ScarecrowModel(ctx.bakeLayer(ScarecrowModel.MODEL_LAYER)), 0.0F);
        ScarecrowArmorModel inner = new ScarecrowArmorModel(ctx.bakeLayer(ScarecrowModel.INNER_ARMOR));
        ScarecrowArmorModel outer = new ScarecrowArmorModel(ctx.bakeLayer(ScarecrowModel.OUTER_ARMOR));
        this.addLayer(new HumanoidArmorLayer<>(
            this,
            new ArmorModelSet<>(inner, inner, inner, inner),
            new ArmorModelSet<>(outer, outer, outer, outer),
            ctx.getEquipmentRenderer()
        ));
        this.addLayer(new StuckArrowsLayer(ctx, this));
        this.addLayer(new ItemInHandLayer<>(this));
        this.addLayer(new CustomHeadLayer<>(this, ctx.getModelSet(), ctx.getPlayerSkinRenderCache()));
    }

    @Override
    protected void scale(ScarecrowRenderState<ScarecrowEntity> state, PoseStack matrices) {
        matrices.scale(0.9375F, 0.9375F, 0.9375F);
    }

    @Override
    protected boolean shouldShowName(ScarecrowEntity entity, double distance) {
        return false;
    }

    @Override
    public Identifier getTextureLocation(ScarecrowRenderState<ScarecrowEntity> state) {
        return TEXTURE;
    }

    @Override
    public ScarecrowRenderState<ScarecrowEntity> createRenderState() {
        return new ScarecrowRenderState<>();
    }

    @Override
    public void extractRenderState(ScarecrowEntity entity, ScarecrowRenderState<ScarecrowEntity> state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.entity = entity;
        state.partialTick = partialTick;
        ArmedEntityRenderState.extractArmedEntityRenderState(entity, state, this.itemModelResolver, partialTick);
    }
}
