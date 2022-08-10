package net.funmod.com.Cheats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.funmod.com.Util.SimpleKeyBinding;
import net.funmod.com.Util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.function.Predicate;

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
                                playerPos.x + ENTITIY_SEARCH_RANGE, playerPos.y + ENTITIY_SEARCH_RANGE, playerPos.z + ENTITIY_SEARCH_RANGE),
                        new Predicate<PlayerEntity>() {
                            @Override
                            public boolean test(PlayerEntity playerEntity) {
                                return true;
                            }
                        });
                for (PlayerEntity player: players) {
                    Util.displayMessage("player found nearby: " + player.getName().getString());
                }
            }

            keyBinding.reset();
        });
    }
}
