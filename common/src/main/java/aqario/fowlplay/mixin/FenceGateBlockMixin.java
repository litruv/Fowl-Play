package aqario.fowlplay.mixin;

import aqario.fowlplay.common.util.Birds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(FenceGateBlock.class)
public abstract class FenceGateBlockMixin extends HorizontalDirectionalBlock {
    @Shadow
    @Final
    protected static Map<Direction.Axis, VoxelShape> SHAPE_COLLISION;

    protected FenceGateBlockMixin(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Inject(method = "getCollisionShape", at = @At("RETURN"), cancellable = true)
    private void fowlplay$lowerFenceGateHeight(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if(context instanceof EntityCollisionContext entityContext
            && entityContext.getEntity() != null
            && Birds.isNotFlightless(entityContext.getEntity())
        ) {
            VoxelShape shape = SHAPE_COLLISION.get(state.getValue(FACING).getAxis());
            cir.setReturnValue(Shapes.box(
                shape.min(Direction.Axis.X),
                shape.min(Direction.Axis.Y),
                shape.min(Direction.Axis.Z),
                shape.max(Direction.Axis.X),
                1,
                shape.max(Direction.Axis.Z)
            ));
        }
    }
}
