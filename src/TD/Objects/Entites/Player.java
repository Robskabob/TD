package TD.Objects.Entites;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Objects.Mob;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Player extends Mob {
    public Player(float x,float y,float radius,int hight,int team)
    {
        Pos = new Vec2(x,y);
        Vel = new Vec2(0,0);
        Radius=radius;
        Height =hight;
        Team=team;
        Speed = .1f;
        Friction = .3f;
    }

    @Override
    public void Update(Engine E)
    {
        Dir = PApplet.atan2(E.GetMouse().y-(E.ScreenHeight()/2), E.GetMouse().x-(E.ScreenWidth()/2));
        if(E.GetKey('w'))
        {
            Vel.y -= Speed;
        }
        if(E.GetKey('a'))
        {
            Vel.x-=Speed;
        }
        if(E.GetKey('s'))
        {
            Vel.y+=Speed;
        }
        if(E.GetKey('d'))
        {
            Vel.x+=Speed;
        }
        if(GameManager.GM.mousePressed && GameManager.GM.mouseButton == GameManager.LEFT)
        {
            E.EntitySys.Add(new Projectile(Pos, Dir,.1f,.5f,Height+1));
        }
    }

    @Override
    public void draw(PApplet PA, float x, float y, float rot, float scale)
    {
        PA.pushMatrix();
        PA.translate(x,y);
        PA.rotate(rot);

        PA.ellipseMode(2);
        PA.fill(60);
        PA.stroke(30);
        PA.strokeWeight(1);
        PA.ellipse(0, 0, Radius*scale, Radius*scale);

        PA.popMatrix();
    }
}
