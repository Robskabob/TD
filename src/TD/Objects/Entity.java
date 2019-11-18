package TD.Objects;

import TD.Main.GameManager;
import TD.Util.Vec2;

public abstract class Entity
{
    public Vec2 Pos;
    public Vec2 Vel;
    public float Radius;
    public int Height;
    public int Team;
    public boolean Dead = false;

    public void Physics(GameManager GM)
    {
        Pos.Add(Vel);
        Vel.Divide(2);
    }

    public abstract void Update(GameManager GM);

    public abstract void draw(float x,float y,GameManager GM);
    //{
    //    GM.ellipseMode(2);
    //    GM.fill(10);
    //    GM.stroke(30);
    //    GM.ellipse(GM.Entity.P.Pos.x*GM.Render.Zoom, GM.Entity.P.Pos.y*GM.Render.Zoom, GM.Entity.P.Radius*GM.Render.Zoom, GM.Entity.P.Radius*GM.Render.Zoom);
    //}
}
