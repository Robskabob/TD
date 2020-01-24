package TD.System;

import TD.Main.Engine;
import TD.Main.GameMode;
import processing.core.PApplet;

public abstract class GameSystem extends System {
    public Engine E;
    public GameMode G;
    public GameSystem(Engine e, GameMode g) {
        E = e;
        G = g;
    }
}
