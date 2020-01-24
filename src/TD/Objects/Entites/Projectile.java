package TD.Objects.Entites;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Objects.Mob;
import TD.Objects.Unit;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Projectile extends Mob
{
    int life = 50;

    public Projectile New(Vec2 pos, float dir, float radius,float speed,int height)
    {
        return new Projectile(pos, dir,radius, speed, height);
    }

    public Projectile(){}

    public Projectile(Vec2 pos, float dir, float radius, float speed,int height)
    {
        Pos = new Vec2(pos);
        Vel = new Vec2(GameManager.cos(dir)*speed,GameManager.sin(dir)*speed);
        Radius = radius;
        Height = height;
        Team = 0;
        Speed = speed;
        Friction=.01f;
        life = (int)(50/speed);
    }

    @Override
    public boolean CanColl(int h)
    {
        return h <= Height;
    }

    @Override
    public void OnCollide()
    {
        Dead = true;
    }

    @Override
    public void Update(Engine E)
    {
        life--;
        if(life<0)
        {
            Dead = true;
        }
        Unit U = E.EntitySys.GetUnitNearPoint(Pos,.7f);
        if(U!=null)
        {
            U.HP-=10;
            Dead = true;
        }
    }

    @Override
    public void draw(PApplet PA, float x, float y, float rot, float scale)
    {
        PA.pushMatrix();
        PA.translate(x,y);
        PA.rotate(rot);

        PA.ellipseMode(2);
        PA.fill(10);
        PA.stroke(30);
        PA.ellipse(0,0, Radius*scale, Radius*scale);

        PA.popMatrix();
    }
}
