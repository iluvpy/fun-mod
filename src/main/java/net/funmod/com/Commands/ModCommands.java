package net.funmod.com.Commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;

public class ModCommands {

    public static void register() {
        registerSetYaw();
        registerSetPitch();
    }

    private static void registerSetYaw() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess, registrationEnvironment) -> {
            dispatcher.register(CommandManager.literal("setYaw").then(CommandManager.argument("value", IntegerArgumentType.integer()).executes(ctx -> {
                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.setYaw((float)ctx.getArgument("value", Integer.class));
                return 1;
            })));
        });
    }

    private static void registerSetPitch() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess, registrationEnvironment) -> {
            dispatcher.register(CommandManager.literal("setPitch").then(CommandManager.argument("value", IntegerArgumentType.integer()).executes(ctx -> {
                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.setPitch((float)ctx.getArgument("value", Integer.class));
                return 1;
            })));
        });
    }
}
