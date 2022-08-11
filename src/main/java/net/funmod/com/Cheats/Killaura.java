package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class Killaura {
    private static SimpleKeyBinding keyBinding;
    private static boolean active;
    private static final int ENTITIY_SEARCH_RANGE = 10;
    public static void register() {
        keyBinding = SimpleKeyBinding.init(InputUtil.GLFW_KEY_R);
        active = false;
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            assert MinecraftClient.getInstance().player != null;
            keyBinding.update();
            if (keyBinding.wasPressed()) {
                active = !active;
                Util.sendCheatActivation("Killaura", active);
            }

            if (active) {
                assert MinecraftClient.getInstance().world != null;
                Vec3d playerPos = MinecraftClient.getInstance().player.getPos();
                List<PlayerEntity> players = MinecraftClient.getInstance().world.getEntitiesByClass(PlayerEntity.class,
                        new Box(playerPos.x - ENTITIY_SEARCH_RANGE, playerPos.y - ENTITIY_SEARCH_RANGE, playerPos.z - ENTITIY_SEARCH_RANGE,
                                playerPos.x + ENTITIY_SEARCH_RANGE, playerPos.y + ENTITIY_SEARCH_RANGE, playerPos.z + ENTITIY_SEARCH_RANGE), PlayerEntity::isMainPlayer);


                for (PlayerEntity player: players) {
                    Vec3d otherPlayerPos = player.getPos();
                    double distance = Math.abs(otherPlayerPos.x - playerPos.x + otherPlayerPos.y - playerPos.y + otherPlayerPos.z - playerPos.z);
                    Util.displayMessage("distance from " + player.getName().getString() + " " + (int)distance + " blocks!");
                    if (distance < 3) {
                        MinecraftClient.getInstance().player.lookAt(player.getCommandSource().getEntityAnchor(), otherPlayerPos);
                        MinecraftClient.getInstance().player.attack(player);
                    }
                }
            }

            keyBinding.reset();
        });
    }
}
