package net.funmod.com.mixin;

import net.funmod.com.GUI.Gui;
import net.funmod.com.Render.Rectangle;
import net.funmod.com.Render.Renderer;
import net.funmod.com.Util.KeyboardEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;




@Mixin(InGameHud.class)
public class RenderMixin {

    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        Renderer.DrawText(matrices, "Fun mod", 20, 20, Renderer.getColor(255, 255, 255, 255));
        if (MinecraftClient.getInstance().player == null) return;
        Gui.updateAndRender(matrices);
    }
}


