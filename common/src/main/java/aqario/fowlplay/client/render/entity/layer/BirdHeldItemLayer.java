package aqario.fowlplay.client.render.entity.layer;

import aqario.fowlplay.client.render.entity.model.BirdModel;
import aqario.fowlplay.client.render.entity.state.BirdRenderState;
import aqario.fowlplay.common.entity.BirdEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class BirdHeldItemLayer<E extends BirdEntity, M extends BirdModel<E>> extends RenderLayer<BirdRenderState<E>, M> {
    private final ItemInHandRenderer itemRenderer;
    // Z should be ((number of pixels offset from head pivot point) / 16 + 0.0225) * -1
    private final Vec3 heldItemOffset;

    public BirdHeldItemLayer(RenderLayerParent<BirdRenderState<E>, M> parent, ItemInHandRenderer heldItemRenderer, Vec3 heldItemOffset) {
        super(parent);
        this.itemRenderer = heldItemRenderer;
        this.heldItemOffset = heldItemOffset;
    }

    @Override
    public void submit(PoseStack matrices, SubmitNodeCollector collectors, int light, BirdRenderState<E> state, float limbSwing, float limbSwingAmount) {
        M model = this.getParentModel();
        matrices.pushPose();
        model.root().translateAndRotate(matrices);
        model.body.translateAndRotate(matrices);
        model.neck.translateAndRotate(matrices);
        model.head.translateAndRotate(matrices);

        matrices.translate(this.heldItemOffset.x, this.heldItemOffset.y, this.heldItemOffset.z);
        matrices.mulPose(Axis.XN.rotationDegrees(90.0F));
        matrices.scale(0.5F, 0.5F, 0.5F);

        ItemStack stack = state.entity.getItemBySlot(EquipmentSlot.MAINHAND);
        this.itemRenderer.renderItem(state.entity, stack, ItemDisplayContext.GROUND, matrices, collectors, light);
        matrices.popPose();
    }
}
