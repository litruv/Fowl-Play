package aqario.fowlplay.client.render.entity.layer;

import aqario.fowlplay.client.render.entity.model.ScarecrowModel;
import aqario.fowlplay.client.render.entity.state.ScarecrowRenderState;
import aqario.fowlplay.common.entity.ScarecrowEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import org.joml.Quaternionf;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;

public class StuckArrowsLayer extends RenderLayer<ScarecrowRenderState<ScarecrowEntity>, ScarecrowModel> {
    private final EntityRenderDispatcher dispatcher;
    private final CameraRenderState cameraState = new CameraRenderState();

    public StuckArrowsLayer(EntityRendererProvider.Context context, RenderLayerParent<ScarecrowRenderState<ScarecrowEntity>, ScarecrowModel> parent) {
        super(parent);
        this.dispatcher = context.getEntityRenderDispatcher();
    }

    protected int getObjectCount(LivingEntity entity) {
        return entity.getArrowCount();
    }

    protected void renderObject(PoseStack matrices, SubmitNodeCollector collectors, int light, LivingEntity entity, float directionX, float directionY, float directionZ) {
        float f = Mth.sqrt(directionX * directionX + directionZ * directionZ);
        Arrow arrow = new Arrow(entity.level(), entity.getX(), entity.getY(), entity.getZ(), ItemStack.EMPTY, ItemStack.EMPTY);
        arrow.setYRot((float) (Math.atan2(directionX, directionZ) * 180.0F / (float) Math.PI));
        arrow.setXRot((float) (Math.atan2(directionY, f) * 180.0F / (float) Math.PI));
        arrow.yRotO = arrow.getYRot();
        arrow.xRotO = arrow.getXRot();

        EntityRenderState state = dispatcher.extractEntity(arrow, 0.0F);
        state.lightCoords = light;
        dispatcher.submit(state, cameraState, 0.0, 0.0, 0.0, matrices, collectors);
    }

    @Override
    public void submit(PoseStack matrices, SubmitNodeCollector collectors, int light, ScarecrowRenderState<ScarecrowEntity> state, float limbSwing, float limbSwingAmount) {
        LivingEntity entity = state.entity;
        cameraState.blockPos = dispatcher.camera.blockPosition();
        cameraState.pos = dispatcher.camera.position();
        cameraState.initialized = dispatcher.camera.isInitialized();
        cameraState.entityPos = dispatcher.camera.position();
        cameraState.orientation = new Quaternionf(dispatcher.camera.rotation());

        int count = this.getObjectCount(entity);
        RandomSource random = RandomSource.create(entity.getId());
        if(count <= 0) {
            return;
        }
        for(int i = 0; i < count; i++) {
            matrices.pushPose();
            ModelPart body = this.getParentModel().body;
            ModelPart.Cube cube = body.getRandomCube(random);
            body.translateAndRotate(matrices);
            float o = random.nextFloat();
            float p = random.nextFloat();
            float q = random.nextFloat();
            matrices.translate(
                Mth.lerp(o, cube.minX, cube.maxX) / 16.0F,
                Mth.lerp(p, cube.minY, cube.maxY) / 16.0F,
                Mth.lerp(q, cube.minZ, cube.maxZ) / 16.0F
            );
            this.renderObject(matrices, collectors, light, entity, -1.0F * (o * 2.0F - 1.0F), -1.0F * (p * 2.0F - 1.0F), -1.0F * (q * 2.0F - 1.0F));
            matrices.popPose();
        }
    }
}
