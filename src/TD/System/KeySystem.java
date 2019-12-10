package TD.System;

import TD.Main.GameManager;

import java.util.HashMap;

public class KeySystem extends System {
    private char Last;
    private HashMap<Character, Boolean> Keys = new HashMap();

    public KeySystem(GameManager gm) {
        super(gm);
    }

    public void setup() {
        Keys.put('s', false);
        Keys.put('d', false);
    }

    @Override
    public void draw() {

    }

    public boolean Get(Character c) {
        return Keys.containsKey(c) && Keys.get(c);
    }

    public void KeyDown() {
        if (Last != PA.key) {
            Keys.put(PA.key, true);
        }
    }

    public void KeyUp() {
        if (Last != PA.key) {
            Keys.put(PA.key, false);
        }
    }
}
