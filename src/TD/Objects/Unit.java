package TD.Objects;

import TD.Main.GameManager;
import TD.Objects.Interfaces.Pather;
import TD.System.PathSystem;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Unit extends Mob implements Pather
{
    public String Name;
    public int Step = 1;
    public byte Terrain;
    private int pathIndex = 0;
    public PathSystem.Path path;
    public float HP = 100;
    public boolean Water;

    public Unit(){}

    public Unit(Unit U, PathSystem.Path p){
        path = p;
        Pos = new Vec2(path.path.get(0).Pos);
        Vel = new Vec2(0,0);
        Radius = U.Radius;
        Height = U.Height;
        Team = U.Team;
        Speed = U.Speed;
        Friction = U.Friction;
        HP = U.HP;
        pathIndex = 0;
    }

    public Unit(PathSystem.Path Path)
    {
        path = Path;
        Pos = new Vec2(path.path.get(0).Pos);
        Vel = new Vec2(0,0);
        Radius=.3f;
        Height = 1;
        Team = 5;
        Speed = .02f;
        Friction = .2f;
        pathIndex = 0;
    }

    public Unit(PathSystem.Node las, PathSystem.Node sel) {
        path = GameManager.GM.Pather.GetPath(las,sel,this);
        Pos = new Vec2(path.path.get(0).Pos);
        Vel = new Vec2(0,0);
        Radius=.3f;
        Height = 1;
        Team = 5;
        Speed = .02f;
        Friction = .2f;
        pathIndex = 0;
    }

    @Override
    public boolean CanColl(int h)
    {
        return Math.abs(h - Height) <= Step;
    }

    @Override
    public void Update(PApplet GM) {
        Vel.Add(path.getNext(Pos,this).sub(Pos).normilize().mult(Speed));
        if(path.isEnd(this)||0 > HP)
            Dead = true;
    }

    @Override
    public void draw(PApplet GM, float x, float y, float rot, float scale) {
        GM.pushMatrix();
        GM.translate(x,y);
        GM.rotate(rot);

        GM.ellipseMode(2);
        if(Water)
            GM.fill(60,60,160);
        else
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
