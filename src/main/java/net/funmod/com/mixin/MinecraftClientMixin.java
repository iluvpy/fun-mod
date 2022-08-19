package net.funmod.com.mixin;

import net.funmod.com.Cheats.FullBright;
import net.funmod.com.Cheats.Xray.Xray;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "isAmbientOcclusionEnabled()Z", at = @At("HEAD"), cancellable = true)
    private static void injectXRayFullBright(CallbackInfoReturnable<Boolean> callback) {
        if (!Xray.isActive() || !FullBright.isActive()) {
            return;
        }

        callback.setReturnValue(false);
        callback.cancel();
    }
}
