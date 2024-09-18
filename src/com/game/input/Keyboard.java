package com.game.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Keyboard {

    private static final Map<Integer, Boolean> pressedKeys = new HashMap<>();

    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            synchronized (Keyboard.class) {
                if (event.getID() == KeyEvent.KEY_PRESSED) pressedKeys.put(event.getKeyCode(), true);
                else if (event.getID() == KeyEvent.KEY_RELEASED) pressedKeys.put(event.getKeyCode(), false);
                return false;
            }
        });
    }

    public static boolean isKeyPressed(int keyCode) { // Any key code from the KeyEvent class
        return pressedKeys.getOrDefault(keyCode, false);
    }
    
    public static boolean isKeyReleased(int keyCode) {
        return !pressedKeys.getOrDefault(keyCode, true);
    }
}
// Reference: https://stackoverflow.com/questions/18037576/how-do-i-check-if-the-user-is-pressing-a-key
