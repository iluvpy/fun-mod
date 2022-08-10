package net.funmod.com.Util;

public class SimpleKeyBinding {

    int keyCodeBinding;
    boolean thisFrameKeyDown;
    boolean lastFrameLastFrameKeyDown;

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
        return thisFrameKeyDown && !lastFrameLastFrameKeyDown;
    }

    public boolean isDown() {
        return thisFrameKeyDown;
    }

    public boolean wasReleased() {
        return lastFrameLastFrameKeyDown && !thisFrameKeyDown;
    }

}
