package TD.System;

import TD.Main.GameManager;
import processing.core.PApplet;

import java.util.HashMap;

public class KeySystem extends MainSystem {
    private char Last;
    private HashMap<Character, Boolean> Keys = new HashMap();

    public KeySystem(GameManager pa) {
        super(pa);
    }

    @Override
    public void setup() {
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PApplet PA) {

    }

    public boolean Get(Character c) {
        return Keys.containsKey(c) && Keys.get(c);
    }

    public void KeyDown() {
        if (Last != GM.key) {
            Keys.put(GM.key, true);
        }
    }

    public void KeyUp() {
        if (Last != GM.key) {
            Keys.put(GM.key, false);
        }
    }
}
