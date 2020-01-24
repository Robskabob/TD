package TD.System;

import processing.core.PApplet;

public abstract class System {

    public System() { }

    public abstract void setup();

    public abstract void update();

    public abstract void draw(PApplet PA);
}
