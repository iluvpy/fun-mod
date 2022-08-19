package net.funmod.com.Cheats.Xray;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.funmod.com.mixin.MinecraftClientMixin;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Xray {

    private static SimpleKeyBinding keyBinding;
    private static boolean xrayActive;

    private static Set<Block> renderedBLocks;
    public static void register() {
        keyBinding = SimpleKeyBinding.init(InputUtil.GLFW_KEY_X);
        xrayActive = false;
        renderedBLocks = new HashSet<>();

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            keyBinding.update();
            if (client.player == null) return;
            if (keyBinding.wasPressed()) {
                xrayActive = !xrayActive;
                Util.sendCheatActivation("Xray", xrayActive);
                MinecraftClient.getInstance().worldRenderer.reload();
                // the rest is handled by the XrayRenderMixin class

                if (xrayActive) {
                    MinecraftClient.getInstance().options.getGamma().setValue(15.0);
                }

            }

            keyBinding.reset();
        });
    }

    public static void addBlock(Block newBlock) {
        renderedBLocks.add(newBlock);
    }

    public static void removeBlock(Block removeBlock) {
        renderedBLocks.remove(removeBlock);
    }

    public static boolean shouldRender(Block block) {
        return renderedBLocks.contains(block);
    }

    public static boolean isActive() {
        return xrayActive;
    }
}
