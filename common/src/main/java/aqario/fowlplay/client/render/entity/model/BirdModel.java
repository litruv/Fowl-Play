package aqario.fowlplay.client.render.entity.model;

import aqario.fowlplay.client.render.entity.state.BirdRenderState;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;

import java.util.HashMap;
import java.util.Map;

public abstract class BirdModel<E> extends EntityModel<BirdRenderState<E>> {
    public final ModelPart root;
    public final ModelPart body;
    public final ModelPart neck;
    public final ModelPart head;
    public final ModelPart torso;
    public final ModelPart leftWing;
    public final ModelPart rightWing;
    public final ModelPart leftLeg;
    public final ModelPart rightLeg;
    public final ModelPart tail;

    private final Map<AnimationDefinition, KeyframeAnimation> baked = new HashMap<>();

    public BirdModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.torso = this.body.getChild("torso");
        this.leftWing = this.body.getChild("left_wing");
        this.rightWing = this.body.getChild("right_wing");
        this.leftLeg = this.root.getChild("left_leg");
        this.rightLeg = this.root.getChild("right_leg");
        this.tail = this.body.getChild("tail");
    }

    @Override
    public void setupAnim(BirdRenderState<E> state) {
        this.resetPose();

        float netHeadYaw = Mth.wrapDegrees(state.yRot - state.bodyRot);
        float headPitch = state.xRot;
        if(state.isUpsideDown) {
            headPitch *= -1.0F;
            netHeadYaw *= -1.0F;
        }

        this.setAnimations(state.entity, state.walkAnimationPos, state.walkAnimationSpeed, state.ageInTicks, netHeadYaw, headPitch, state.partialTick);
    }

    protected void setAnimations(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTick) {
    }

    // HierarchicalModel is gone: bake each definition once against the model root, then apply it.
    protected void animate(AnimationState animationState, AnimationDefinition definition, float ageInTicks) {
        this.baked(definition).apply(animationState, ageInTicks);
    }

    protected void animate(AnimationState animationState, AnimationDefinition definition, float ageInTicks, float scale) {
        this.baked(definition).apply(animationState, ageInTicks, scale);
    }

    protected void animateWalk(AnimationDefinition definition, float limbSwing, float limbSwingAmount, float maxRotation, float maxAnimationSpeed) {
        this.baked(definition).applyWalk(limbSwing, limbSwingAmount, maxRotation, maxAnimationSpeed);
    }

    private KeyframeAnimation baked(AnimationDefinition definition) {
        return this.baked.computeIfAbsent(definition, d -> d.bake(this.root()));
    }

    protected void updateHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -135.0F, 135.0F);
        headPitch = Mth.clamp(headPitch, -45.0F, 45.0F);
        this.neck.yRot = headYaw * (float) (Math.PI / 180.0);
        this.neck.xRot = headPitch * (float) (Math.PI / 180.0);
    }
}
