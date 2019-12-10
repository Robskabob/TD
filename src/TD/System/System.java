package TD.System;

import processing.core.PApplet;

public abstract class System {
    public PApplet PA;

    public System(PApplet pa) {
        PA = pa;
    }

    public abstract void setup();

    public abstract void draw();
}
