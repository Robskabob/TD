package TD.Objects;

import TD.Util.Vec2;
import processing.core.PApplet;

public abstract class Entity
{
    public Vec2 Pos;
    public float Dir;
    public float Radius = 1;
    public int Height;
    public int Team;
    public boolean Dead = false;

    public void Physics(PApplet GM) {   }

    public abstract void Update(PApplet GM);

    //change to imput x y scale rot transfor pop push inside

    public abstract void draw(PApplet GM, float x, float y, float rot, float scale);
    //{
    //    GM.ellipseMode(2);
    //    GM.fill(10);
    //    GM.stroke(30);
    //    GM.ellipse(GM.Entity.P.Pos.x*GM.Render.Zoom, GM.Entity.P.Pos.y*GM.Render.Zoom, GM.Entity.P.Radius*GM.Render.Zoom, GM.Entity.P.Radius*GM.Render.Zoom);
    //}
}
