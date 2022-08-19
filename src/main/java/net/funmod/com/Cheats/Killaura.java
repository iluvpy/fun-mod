package net.funmod.com.Cheats;

import com.google.common.eventbus.EventBus;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class Killaura {
    private static SimpleKeyBinding keyBinding;
    private static boolean active;
    private static final int ENTITIY_SEARCH_RANGE = 10;
    public static double range;
    public static void register() {
        keyBinding = SimpleKeyBinding.init(InputUtil.GLFW_KEY_R);
        active = false;
        range = 3.0f;
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

                // find the closest entity
                double closest = range;
                Entity target = null;
                for (var enemy: enemies) {
                    double distance = clientPlayer.distanceTo(enemy);
                    if (distance <= closest) {
                        closest = distance;
                        target = enemy;

                    }
                }
                if (target != null) {
                    Vec3d center = target.getBoundingBox().getCenter();
                    lookAt(clientPlayer, center);
                    if (clientPlayer.handSwingProgress == 0.0) {
                        assert MinecraftClient.getInstance().currentScreen != null;
                        assert MinecraftClient.getInstance().interactionManager != null;
                        MinecraftClient.getInstance().interactionManager.attackEntity(clientPlayer, target);
                        clientPlayer.swingHand(Hand.MAIN_HAND);
                    }
                }
            }

            keyBinding.reset();
        });
    }

    public static void lookAt(ClientPlayerEntity client, Vec3d pos) {
        Vec3d eyesPos = client.getEyePos();

        double diffX = pos.x - eyesPos.x;
        double diffY = pos.y - eyesPos.y;
        double diffZ = pos.z - eyesPos.z;

        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);

        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
        float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));

        client.setYaw(yaw);
        client.setPitch(pitch);
    }

}
