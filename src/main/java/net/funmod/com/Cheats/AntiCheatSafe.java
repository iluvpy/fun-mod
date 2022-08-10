package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.funmod.com.Util.SimpleKeyBinding;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class AntiCheatSafe {
    private static SimpleKeyBinding keyBinding;

    public static void register() {
        keyBinding = SimpleKeyBinding.init(InputUtil.GLFW_KEY_H);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            keyBinding.update();
            if (keyBinding.wasPressed()) {
                client.player.setPos(client.player.getX(), client.player.getY()+3, client.player.getZ());
            }
            keyBinding.reset();
        });
    }
}
