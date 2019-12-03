package TD.Objects.Entites;

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
    public void Update(PApplet GM)
    {
        Dir = GM.atan2(GM.mouseY-(GM.height/2),GM.mouseX-(GM.width/2));
        if(GameManager.GM.GetKey('w'))
        {
            Vel.y -= Speed;
        }
        if(GameManager.GM.GetKey('a'))
        {
            Vel.x-=Speed;
        }
        if(GameManager.GM.GetKey('s'))
        {
            Vel.y+=Speed;
        }
        if(GameManager.GM.GetKey('d'))
        {
            Vel.x+=Speed;
        }
        if(GameManager.GM.mousePressed && GM.mouseButton == GameManager.LEFT)
        {
            GameManager.GM.Entity.Add(new Projectile(Pos, Dir,.5f,Height+1));
        }
    }

    @Override
    public void draw(PApplet GM, float x, float y, float rot, float scale)
    {
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
