package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class DoubleJump {
    private static int jumps;
    private static boolean active;    private static KeyBinding keyBinding;
    private static boolean lastFrameJumping;
    private static boolean thisFrameJumping;
    public static void register() {
        jumps = 0;
        active = false;
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Double jump",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Y,
                "Cheats"
        ));

        lastFrameJumping = false;
        thisFrameJumping = false;

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {return;}
            thisFrameJumping = client.player.input.jumping;
            if (keyBinding.wasPressed()) {
                active = !active;
                client.player.sendMessage(Text.literal("Double Jump " + (active ? "Enabled" : "Disabled")));
            }
            // if player is standing on something
            float offset = ((int)client.player.getY()) == client.player.getY() ? 0.01f : 0.0f;
            BlockPos standingOn = new BlockPos(client.player.getX(), client.player.getY()-offset, client.player.getZ());
            if (client.world.getBlockState(standingOn) != Blocks.AIR.getDefaultState() && jumps != 1) {
                jumps = 0;
            }


            if (jumps < 2 && thisFrameJumping && !lastFrameJumping && active) {
                if (jumps > 0) {
                    client.player.jump();
                }
                jumps++;
            }
            lastFrameJumping = thisFrameJumping;
        });

    }
}
