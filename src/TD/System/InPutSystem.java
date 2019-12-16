package TD.System;

import TD.Main.GameManager;
import TD.Util.Vec2;
import processing.core.PApplet;

import java.util.HashMap;

public class InPutSystem extends MainSystem {
    private char Last;
    private HashMap<Character, Boolean> Keys = new HashMap();

    public boolean Disable;

    public InPutSystem(GameManager pa) {
        super(pa);
    }

    @Override
    public void setup() {
    }

    @Override
    public void update() {

    }

    public Vec2 MousePos(){return null;}

    @Override
    public void draw(PApplet PA) {

    }

    public boolean GetKey(Character c) {
        if(Disable)
            return false;
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
