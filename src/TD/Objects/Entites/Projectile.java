package TD.Objects.Entites;

import TD.Main.GameManager;
import TD.Objects.Mob;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Projectile extends Mob
{
    int life = 100;

    public Projectile(Vec2 pos, float dir,float speed,int hight)
    {
        Pos = new Vec2(pos);
        Vel = new Vec2(GameManager.cos(dir)*speed,GameManager.sin(dir)*speed);
        Radius = .1f;
        Height = hight;
        Team = 0;
        Speed = .1f;
        Friction=.01f;
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
    public void Update(PApplet GM)
    {
        life--;
        if(life<0)
        {
            Dead = true;
        }
    }

    @Override
    public void draw(PApplet GM, float x, float y, float rot, float scale)
    {
        GM.pushMatrix();
        GM.translate(x,y);
        GM.rotate(rot);

        GM.ellipseMode(2);
        GM.fill(10);
        GM.stroke(30);
        GM.ellipse(0,0, Radius*scale, Radius*scale);

        GM.popMatrix();
    }
}
