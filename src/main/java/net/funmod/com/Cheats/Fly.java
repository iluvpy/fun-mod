package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Fly {

    private static SimpleKeyBinding keyBinding;
    private static boolean active;
    private static double yWhenActivated;
    public static void registerFly() {
        active = false;
        keyBinding = SimpleKeyBinding.init(InputUtil.GLFW_KEY_F);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            keyBinding.update();
            if (keyBinding.wasPressed()) {
                active = !active;
                yWhenActivated = MinecraftClient.getInstance().player.getY();
                Util.sendCheatActivation("Fly", active);
            }

            if (active) {
                Vec3d playerPos = MinecraftClient.getInstance().player.getPos();
                MinecraftClient.getInstance().player.setPos(playerPos.x, yWhenActivated, playerPos.z);
            }

            keyBinding.reset();
        });
    }
}
