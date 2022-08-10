package net.funmod.com.Util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

import java.util.HashMap;

public class KeyboardEvents {
    private static final HashMap<Integer, Boolean> keys = new HashMap<>();

    public static void init() {
        for (int i = InputUtil.GLFW_KEY_0; i < InputUtil.GLFW_KEY_Z; i++) {
            keys.put(i, false);
        }
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            update();
        });
    }
    public static void update() {
        keys.forEach((key, value) -> {
            keys.put(key, InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), key));
        });
    }

    public static boolean isKeyPressed(int keyCode) {
        return  keys.getOrDefault(keyCode, false);
    }
}
