package net.funmod.com.Util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Util {
    public static void sendCheatActivation(String cheat, boolean active) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal(cheat + " was " + (active ? "Enabled" : "Disabled")));
    }

    public static void displayMessage(String message) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal(message));
    }
}
