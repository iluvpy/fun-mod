package net.funmod.com.mixin;

import net.funmod.com.Cheats.FullBright;
import net.funmod.com.Cheats.Xray.Xray;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightmapTextureManager.class)
public abstract class LightmapTextureMixin {

    @Inject(method = "getBrightness", at = @At("RETURN"), cancellable = true)
    private static void XrayBrightnessInject(DimensionType type, int lightLevel, CallbackInfoReturnable<Float> cir) {
        if (!Xray.isActive() && !FullBright.isActive()) {
            return;
        }

        cir.setReturnValue(10.0f);
        cir.cancel();
    }
}
