package net.funmod.com;

import net.fabricmc.api.ModInitializer;
import net.funmod.com.Cheats.*;
import net.funmod.com.Commands.ModCommands;
import net.funmod.com.Util.KeyboardEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("funmod");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		KeyboardEvents.init();
		ModCommands.register();
		Scaffold.register();
		Fly.registerFly();
		DoubleJump.register();
		AntiCheatSafe.register();
		Killaura.register();
	}
}
