package net.funmod.com.GUI;

import net.funmod.com.Render.Rectangle;
import net.funmod.com.Render.Renderer;
import net.funmod.com.Util.SimpleKeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;

public class Gui {
    private static SimpleKeyBinding keyBinding;
    private static boolean guiOpen;

    public static void init() {
        keyBinding = SimpleKeyBinding.init(InputUtil.GLFW_KEY_RIGHT_SHIFT);
        guiOpen = false;
    }

    public static void updateAndRender(MatrixStack matrices) {
        keyBinding.update();

        if (keyBinding.wasPressed()) {
            guiOpen = !guiOpen;
            if (guiOpen) {
                Renderer.DrawRect(matrices, new Rectangle(100, 20, 10, 10), Renderer.getColor(100, 100, 100, 255));
            }
        }

        keyBinding.reset();
    }
}
