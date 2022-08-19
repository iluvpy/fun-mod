package net.funmod.com.Util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;

public class SimpleKeyBinding {

    int keyCodeBinding;
    boolean thisFrameKeyDown;
    boolean lastFrameLastFrameKeyDown;

    public static final int UKNOWN_KEY = -1;
    public void setKeybinding(int keyCode) {
        keyCodeBinding = keyCode;
    }

    public static SimpleKeyBinding init(int keyCode) {
        SimpleKeyBinding keyBinding = new SimpleKeyBinding();
        keyBinding.setKeybinding(keyCode);
        return keyBinding;
    }

    //Usage:
    // # inside main loop ->
    //  key.update();
    //  code that uses key
    //  key.reset();
    public void update() {
        thisFrameKeyDown = KeyboardEvents.isKeyPressed(keyCodeBinding);
    }

    // Usage:
    // # inside main loop ->
    //  key.update();
    //  code that uses key
    //  key.reset();
    public void reset() {
        lastFrameLastFrameKeyDown = thisFrameKeyDown;
    }

    public boolean wasPressed() {
        return thisFrameKeyDown && !lastFrameLastFrameKeyDown && !chatOpen();
    }

    public boolean isDown() {
        return thisFrameKeyDown && !chatOpen();
    }

    public boolean wasReleased() {
        return lastFrameLastFrameKeyDown && !thisFrameKeyDown && !chatOpen();
    }

    private boolean chatOpen() {
        if (MinecraftClient.getInstance().currentScreen == null) return false;
        return MinecraftClient.getInstance().currentScreen instanceof ChatScreen;
    }

}
