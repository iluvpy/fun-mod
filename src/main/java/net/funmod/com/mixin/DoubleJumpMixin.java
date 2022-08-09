package net.funmod.com.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class DoubleJumpMixin {

    @Inject(at = @At("HEAD"), method = "jump")
    public void jump(CallbackInfo ci) {

    }

}
