package aqario.fowlplay.client.render.entity.layer;

import aqario.fowlplay.client.render.entity.model.PigeonModel;
import aqario.fowlplay.client.render.entity.state.BirdRenderState;
import aqario.fowlplay.common.entity.PigeonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class PigeonBundleLayer extends RenderLayer<BirdRenderState<PigeonEntity>, PigeonModel> {
    private final ItemInHandRenderer itemRenderer;

    public PigeonBundleLayer(RenderLayerParent<BirdRenderState<PigeonEntity>, PigeonModel> parent, ItemInHandRenderer heldItemRenderer) {
        super(parent);
        this.itemRenderer = heldItemRenderer;
    }

    @Override
    public void submit(PoseStack matrices, SubmitNodeCollector collectors, int light, BirdRenderState<PigeonEntity> state, float limbSwing, float limbSwingAmount) {
        PigeonModel model = this.getParentModel();
        matrices.pushPose();
        model.root().translateAndRotate(matrices);
        model.leftLeg.translateAndRotate(matrices);

        matrices.translate(0.03125F, 0.075F, 0.0F);
        matrices.mulPose(Axis.XP.rotationDegrees(180.0F));
        matrices.mulPose(Axis.YP.rotationDegrees(90.0F));
        matrices.scale(0.25F, 0.25F, 0.25F);

        ItemStack stack = state.entity.getItemBySlot(EquipmentSlot.OFFHAND);
        this.itemRenderer.renderItem(state.entity, stack, ItemDisplayContext.GROUND, matrices, collectors, light);
        matrices.popPose();
    }
}
