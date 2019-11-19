package TD.Objects.Entites;

import TD.Main.GameManager;
import TD.Objects.Mob;
import TD.Util.Vec2;

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
    public void Update(GameManager GM)
    {
        Rot = GM.atan2(GM.mouseY-(GM.height/2),GM.mouseX-(GM.width/2));
        if(GM.GetKey('w'))
        {
            Vel.y -= Speed;
        }
        if(GM.GetKey('a'))
        {
            Vel.x-=Speed;
        }
        if(GM.GetKey('s'))
        {
            Vel.y+=Speed;
        }
        if(GM.GetKey('d'))
        {
            Vel.x+=Speed;
        }
        if(GM.mousePressed && GM.mouseButton == GameManager.LEFT)
        {
            GM.Entity.Add(new Projectile(Pos,Rot,.5f,Height+1));
        }
    }

    @Override
    public void draw(GameManager GM, float x, float y, float rot, float scale)
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
