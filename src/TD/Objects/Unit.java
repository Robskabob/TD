package TD.Objects;

import TD.Objects.Interfaces.Pather;
import TD.System.PathSystem;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Unit extends Mob implements Pather
{
    private int pathIndex;
    public PathSystem.Path path;

    public Unit(PathSystem.Path Path)
    {
        path = Path;
        Pos = new Vec2(path.path.get(0).Pos);
        Vel = new Vec2(0,0);
        Radius=.3f;
        Height = 1;
        Team = 5;
        Speed = .1f;
        Friction = .2f;
        pathIndex = 0;
    }

    @Override
    public void Update(PApplet GM) {
        Vel.Add(path.getNext(Pos,this).sub(Pos).normilize().mult(Speed));
        if(path.isEnd(this))
            Dead = true;
    }

    @Override
    public void draw(PApplet GM, float x, float y, float rot, float scale) {
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

    @Override
    public int GetPathIndex() {
        return pathIndex;
    }

    @Override
    public void SetPathIndex(int i) {
        pathIndex = i;
    }
}
