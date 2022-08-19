package net.funmod.com.mixin;

import net.funmod.com.Cheats.Xray.Xray;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Block.class)
public abstract class XrayRenderMixin {

    @Inject(at = @At("RETURN"), method = "shouldDrawSide", cancellable = true)
    private static void injectXray(BlockState state, BlockView world, BlockPos pos, Direction side, BlockPos otherPos, CallbackInfoReturnable<Boolean> cir) {
        if (!Xray.isActive()) {
            return;
        }
        cir.setReturnValue(Xray.shouldRender(state.getBlock()));
        cir.cancel();
    }
}
