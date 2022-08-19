package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.text.*;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;


public class Scaffold {

    private static SimpleKeyBinding keyBinding;
    private static boolean scaffoldActive;

    public static void register() {
        keyBinding = SimpleKeyBinding.init(GLFW.GLFW_KEY_N);
        scaffoldActive = false;
        initScaffold();
    }

    private static void initScaffold() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            keyBinding.update();
            if (keyBinding.wasPressed()) {
                scaffoldActive = !scaffoldActive;
                Util.sendCheatActivation("Scaffold", scaffoldActive);
            }

            if (scaffoldActive) {
                assert client.player != null;
                assert client.world != null;
                Vec3d playerPos = client.player.getPos();
                String msg = String.format("x: %d y: %d z: %d  holding: %s", (int)playerPos.x, (int)playerPos.y, (int)playerPos.z,  client.player.getActiveItem().getName());
                client.player.sendMessage(Text.literal(msg), true);

                if (client.world.getBlockState(new BlockPos(playerPos.x, playerPos.y-1, playerPos.z)).getMaterial() == Blocks.AIR.getDefaultState().getMaterial()) {
                    float yaw = client.player.getYaw();
                    float pitch = client.player.getPitch();
                    switch (getFacing(client.player)) {
                        case 'S' -> {
                            client.player.setYaw(128.0f);
                            client.player.setPitch(75.0f);
                        }
                    }
                    BlockHitResult blockHitResult = (BlockHitResult) client.crosshairTarget;
                    Packet<ServerPlayPacketListener> packet = new PlayerInteractBlockC2SPacket(Hand.MAIN_HAND , blockHitResult, 1);
                    client.getNetworkHandler().sendPacket(packet);

                    client.player.setYaw(yaw);
                    client.player.setPitch(pitch);


                }
            }
            keyBinding.reset();
        });
    }

    private static char getFacing(ClientPlayerEntity player) {
        float yaw = player.getYaw();
        if (yaw > -45.0f && yaw < 45.0f) {
            return 'S'; // south
        }
        if (yaw > 45.0f && yaw < 135.0f) {
            return 'W'; // west
        }
        if ((yaw > 135.0f && yaw <= 180.0f) || (yaw < -180.0f && yaw < -135.0f)) {
            return 'N'; // north
        }
        return 'E'; // est
    }

}
