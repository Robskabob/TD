package TD.Objects.Entites.Units;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Objects.Unit;
import TD.System.PathSystem;
import TD.Util.Vec2;

public class WaterMan extends Unit {
    public WaterMan(Engine E, PathSystem.Node las, PathSystem.Node sel) {
        Terrain = PathSystem.Terrain.SetBit(Terrain,PathSystem.Terrain.Land,true);
        Terrain = PathSystem.Terrain.SetBit(Terrain,PathSystem.Terrain.Water,true);
        path = E.PathSys.GetPath(las,sel,this);
        Pos = new Vec2(path.path.get(0).Pos);
        Vel = new Vec2(0,0);
        Radius=.3f;
        Height = 1;
        Team = 5;
        Speed = .02f;
        Friction = .2f;
    }
}
