package TD.System;

import TD.Main.GameManager;

public abstract class MainSystem extends System {
    public GameManager GM;
    public MainSystem(GameManager GM){
        this.GM = GM;}
}
