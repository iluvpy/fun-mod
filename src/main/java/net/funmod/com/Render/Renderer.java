package net.funmod.com.Render;



import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;


public class Renderer {
    public static void DrawRect(MatrixStack matrices, Rectangle rect, int color) {
        DrawableHelper.fill(matrices, rect.x, rect.y, rect.x+rect.width, rect.y+rect.height, color);
    }



    public static int getColor(int r, int g, int b, int a) {
        return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
    }
}
