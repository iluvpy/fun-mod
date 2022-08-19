package net.funmod.com.Cheats;

import net.funmod.com.Cheats.Xray.Xray;
import net.minecraft.block.Blocks;

public class Cheats {

    public static void registerModules() {

        Scaffold.register();
        Fly.registerFly();
        DoubleJump.register();
        AntiCheatSafe.register();
        Killaura.register();
        // xray
        Xray.register();
        // default blocks
        Xray.addBlock(Blocks.DIAMOND_ORE);
        Xray.addBlock(Blocks.LAVA);
        Xray.addBlock(Blocks.WATER);
        Xray.addBlock(Blocks.COAL_ORE);
        Xray.addBlock(Blocks.IRON_ORE);
        Xray.addBlock(Blocks.GOLD_ORE);
        Xray.addBlock(Blocks.COPPER_ORE);
        Xray.addBlock(Blocks.BEDROCK);

        FullBright.register();
    }
}
