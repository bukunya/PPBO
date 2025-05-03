import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class InputHandler extends KeyAdapter {
    public enum Key {
        W, A, S, D, UP, DOWN, LEFT, RIGHT
    }

    private HashMap<Integer, Key> keyMap;
    private HashMap<Key, Boolean> keyState;

    public InputHandler() {
        keyMap = new HashMap<>();
        keyState = new HashMap<>();

        // Initialize key mappings
        keyMap.put(KeyEvent.VK_W, Key.W);
        keyMap.put(KeyEvent.VK_A, Key.A);
        keyMap.put(KeyEvent.VK_S, Key.S);
        keyMap.put(KeyEvent.VK_D, Key.D);
        keyMap.put(KeyEvent.VK_UP, Key.UP);
        keyMap.put(KeyEvent.VK_DOWN, Key.DOWN);
        keyMap.put(KeyEvent.VK_LEFT, Key.LEFT);
        keyMap.put(KeyEvent.VK_RIGHT, Key.RIGHT);

        // Initialize all keys as not pressed
        for (Key key : Key.values()) {
            keyState.put(key, false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Key key = keyMap.get(e.getKeyCode());
        if (key != null) {
            keyState.put(key, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Key key = keyMap.get(e.getKeyCode());
        if (key != null) {
            keyState.put(key, false);
        }
    }

    public boolean isKeyPressed(Key key) {
        return keyState.getOrDefault(key, false);
    }
}
