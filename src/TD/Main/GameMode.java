package TD.Main;

import TD.Objects.Data.Map;

public abstract class GameMode {
    public Engine E;
    public GameMode(Engine E,Map M){this.E = E;}
    public abstract void SetUp();
    public abstract void Update();
}
