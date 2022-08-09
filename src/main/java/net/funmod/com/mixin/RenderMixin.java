package net.funmod.com.mixin;

import net.funmod.com.Render.Rectangle;
import net.funmod.com.Render.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;




@Mixin(InGameHud.class)
public class RenderMixin {
    private int r = 0;

    private int aAdd = 1;

    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        MinecraftClient.getInstance().textRenderer.draw(matrices, "Fun mod 1.0", 20.0f, 20.0f, 0);
        if (MinecraftClient.getInstance().player == null) return;


        Renderer.DrawRect(matrices, new Rectangle(100, 20, 30, 30), Renderer.getColor(r, 120, 160, 255));
        r += aAdd;
        if (r > 254 || r < 1) {
            aAdd *= -1;
        }

    }
}


