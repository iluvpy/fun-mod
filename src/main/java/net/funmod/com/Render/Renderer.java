package net.funmod.com.Render;



import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;


public class Renderer {
    public static void DrawRect(MatrixStack matrices, Rectangle rect, int color) {
        DrawableHelper.fill(matrices, rect.x, rect.y, rect.x+rect.width, rect.y+rect.height, color);
    }

    public static void DrawText(MatrixStack matrices, String text, int x, int y, int color) {
        MinecraftClient.getInstance().textRenderer.draw(matrices, text, x, y, color);
    }

    public static void draw3D(MatrixStack matrices) {

    }

    public static int getColor(int r, int g, int b, int a) {
        return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
    }
}
