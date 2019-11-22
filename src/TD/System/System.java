package TD.System;

import TD.Main.GameManager;
import processing.core.PApplet;

public abstract class System {
    protected PApplet GM;

    public System(PApplet gm) {
        GM = gm;
    }

    public abstract void setup();

    public abstract void draw();
}
