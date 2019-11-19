package TD.System;

import TD.Main.GameManager;

public abstract class System {
    protected GameManager GM;

    public System(GameManager gm) {
        GM = gm;
    }

    public abstract void setup();

    public abstract void draw();
}
