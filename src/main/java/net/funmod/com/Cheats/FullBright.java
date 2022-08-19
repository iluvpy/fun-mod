package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.minecraft.client.util.InputUtil;

public class FullBright {
    private static SimpleKeyBinding keyBinding;
    private static boolean fullBrightActive;
    public static void register() {
        keyBinding = SimpleKeyBinding.init(InputUtil.GLFW_KEY_K);
        fullBrightActive = false;

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            keyBinding.update();

            if (keyBinding.wasPressed()) {
                fullBrightActive = !fullBrightActive;
                Util.sendCheatActivation("FullBright", fullBrightActive);
            }

            keyBinding.reset();
        });
    }

    public static boolean isActive() {
        return fullBrightActive;
    }
}
