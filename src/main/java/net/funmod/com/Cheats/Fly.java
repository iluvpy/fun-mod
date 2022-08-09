package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Fly {
    private static KeyBinding keyBinding;

    public static void registerFly() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Fly",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                "Cheats"
        ));
    }
}
