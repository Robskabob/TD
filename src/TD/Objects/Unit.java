package TD.Objects;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.Util.Vec2;

public class Unit extends Mob
{
    private int pathIndex;
    public PathSystem.Path path;

    public Unit(PathSystem.Path Path)
    {
        path = Path;
        Pos = new Vec2(path.path.get(0).Pos);
        Vel = Vec2.Zero;
        Radius=.3f;
        Height = 1;
        Team = 5;
        Speed = .2f;
        Friction = .5f;
        pathIndex = 0;
    }

    @Override
    public void Update(GameManager GM) {
        Vel.Add(path.getNext(Pos,pathIndex).sub(Pos).normilize().mult(Speed));
        if(path.isEnd(pathIndex))
            Dead = true;
    }

    @Override
    public void draw(GameManager GM, float x, float y, float rot, float scale) {
        GM.pushMatrix();
        GM.translate(x,y);
        GM.rotate(rot);

        GM.ellipseMode(2);
        GM.fill(60);
        GM.stroke(30);
        GM.strokeWeight(1);
        GM.ellipse(0, 0, Radius*scale, Radius*scale);

        GM.popMatrix();
    }
}
