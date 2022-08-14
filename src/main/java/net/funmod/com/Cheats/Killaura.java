package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
            ClientPlayerEntity clientPlayer = MinecraftClient.getInstance().player;
            keyBinding.update();
            if (keyBinding.wasPressed()) {
                active = !active;
                Util.sendCheatActivation("Killaura", active);
            }

            if (active) {
                assert MinecraftClient.getInstance().world != null;
                Vec3d playerPos = MinecraftClient.getInstance().player.getPos();
                var targetClass = LivingEntity.class;
                var enemies = MinecraftClient.getInstance().world.getEntitiesByClass(targetClass,
                        new Box(playerPos.x - ENTITIY_SEARCH_RANGE, playerPos.y - ENTITIY_SEARCH_RANGE, playerPos.z - ENTITIY_SEARCH_RANGE,
                                playerPos.x + ENTITIY_SEARCH_RANGE, playerPos.y + ENTITIY_SEARCH_RANGE, playerPos.z + ENTITIY_SEARCH_RANGE), playerEntity -> playerEntity.getId() != clientPlayer.getId());


                for (var enemy: enemies) {
                    Vec3d enemyPos = enemy.getPos();
                    double distance = Math.abs(enemyPos.x - playerPos.x + enemyPos.y - playerPos.y + enemyPos.z - playerPos.z);
                    Util.displayMessage("distance from " + enemy.getName().getString() + " " + (int)distance + " blocks!");
                    if (distance < 3) {
                        clientPlayer.lookAt(enemy.getCommandSource().getEntityAnchor(), enemy.getBoundingBox().getCenter());
                        clientPlayer.swingHand(clientPlayer.getActiveHand());
                    }
                }
            }

            keyBinding.reset();
        });
    }

}
