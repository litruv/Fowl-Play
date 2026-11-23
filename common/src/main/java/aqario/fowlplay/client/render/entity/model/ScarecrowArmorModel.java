package aqario.fowlplay.client.render.entity.model;

import aqario.fowlplay.client.render.entity.state.ScarecrowRenderState;
import aqario.fowlplay.common.entity.ScarecrowEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ScarecrowArmorModel extends HumanoidModel<ScarecrowRenderState<ScarecrowEntity>> {
    public ScarecrowArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(CubeDeformation dilation) {
        MeshDefinition modelData = HumanoidModel.createMesh(dilation, 0.0F);
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(
            PartNames.HAT,
            CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation.extend(0.5F)),
            PartPose.offset(0.0F, 1.0F, 0.0F)
        );
        modelPartData.addOrReplaceChild(
            PartNames.BODY,
            CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation.extend(0.1F)),
            PartPose.offset(0.0F, 10.0F, 0.0F)
        );
        modelPartData.addOrReplaceChild(
            PartNames.HEAD,
            CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation),
            PartPose.offset(0.0F, -2.0F, 0.0F)
        );
        modelPartData.addOrReplaceChild(
            PartNames.LEFT_LEG,
            CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
            PartPose.offset(1.9F, 11.0F, 0.0F)
        );
        modelPartData.addOrReplaceChild(
            PartNames.RIGHT_LEG,
            CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
            PartPose.offset(-1.9F, 11.0F, 0.0F)
        );
        return LayerDefinition.create(modelData, 64, 32);
    }

    @Override
    public void setupAnim(ScarecrowRenderState<ScarecrowEntity> state) {
        this.resetPose();
        ScarecrowEntity scarecrowEntity = state.entity;
        float deg = (float) (Math.PI / 180.0);
        this.head.xRot = deg * scarecrowEntity.getHeadRotation().x();
        this.head.yRot = deg * scarecrowEntity.getHeadRotation().y();
        this.head.zRot = deg * scarecrowEntity.getHeadRotation().z();
        this.body.xRot = deg * scarecrowEntity.getBodyRotation().x();
        this.body.yRot = deg * scarecrowEntity.getBodyRotation().y();
        this.body.zRot = deg * scarecrowEntity.getBodyRotation().z();
        this.leftArm.xRot = deg * scarecrowEntity.getLeftArmRotation().x();
        this.leftArm.yRot = deg * scarecrowEntity.getLeftArmRotation().y();
        this.leftArm.zRot = deg * scarecrowEntity.getLeftArmRotation().z();
        this.rightArm.xRot = deg * scarecrowEntity.getRightArmRotation().x();
        this.rightArm.yRot = deg * scarecrowEntity.getRightArmRotation().y();
        this.rightArm.zRot = deg * scarecrowEntity.getRightArmRotation().z();
    }
}
