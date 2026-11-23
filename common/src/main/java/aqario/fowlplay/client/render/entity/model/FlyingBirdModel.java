package aqario.fowlplay.client.render.entity.model;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
import aqario.fowlplay.common.entity.FlyingBirdEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public abstract class FlyingBirdModel<E extends FlyingBirdEntity> extends BirdModel<E> {
    public final ModelPart leftWingOpen;
    public final ModelPart rightWingOpen;

    public FlyingBirdModel(ModelPart root) {
        super(root);
        this.leftWingOpen = this.body.getChild("left_wing_open");
        this.rightWingOpen = this.body.getChild("right_wing_open");
    }

    @Override
    public final void setupAnim(BirdRenderState<E> state) {
        this.resetPose();

        float netHeadYaw = Mth.wrapDegrees(state.yRot - state.bodyRot);
        float headPitch = state.xRot;
        if(state.isUpsideDown) {
            headPitch *= -1.0F;
            netHeadYaw *= -1.0F;
        }

        if(!state.entity.isFlying()) {
            this.updateHeadRotation(netHeadYaw, headPitch);
        }
        if(state.entity.isFlying()) {
            this.root.xRot = state.entity.getViewXRot(state.partialTick) * (float) (Math.PI / 180.0);
            this.root.zRot = state.entity.getRoll(state.partialTick) * (float) (Math.PI / 180.0);
        }
        if(this.shouldRenderWings(state.entity)) {
            this.leftWingOpen.visible = true;
            this.rightWingOpen.visible = true;
            this.leftWing.visible = false;
            this.rightWing.visible = false;
        }
        else {
            this.leftWingOpen.visible = false;
            this.rightWingOpen.visible = false;
            this.leftWing.visible = true;
            this.rightWing.visible = true;
        }

        this.setAnimations(state.entity, state.walkAnimationPos, state.walkAnimationSpeed, state.ageInTicks, netHeadYaw, headPitch, state.partialTick);
    }

    protected boolean shouldRenderWings(E entity) {
        return entity.isFlying();
    }
}
