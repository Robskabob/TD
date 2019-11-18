package TD.Objects.Entites;

import TD.Main.GameManager;
import TD.Objects.Entity;
import TD.Util.Vec2;

public class Player extends Entity {
    public Player(float x,float y,float radius,int hight,int team)
    {
        Pos = new Vec2(x,y);
        Vel = new Vec2(0,0);
        Radius=radius;
        Height =hight;
        Team=team;
        Speed = .1f;
    }

    @Override
    public void Physics(GameManager GM)
    {
        if(Vel.x<0)
        {
            if(GM.GetBlockAt((int)(Pos.x+Vel.x-Radius),(int)(Pos.y)).Depth == Height)
            {
                Pos.x+=Vel.x;
            }
        }
        else
        {
            if(GM.GetBlockAt((int)(Pos.x+Vel.x+Radius),(int)(Pos.y)).Depth == Height)
            {
                Pos.x+=Vel.x;
            }
        }
        if(Vel.y<0)
        {
            if(GM.GetBlockAt((int)(Pos.x),(int)(Pos.y+Vel.y-Radius)).Depth == Height)
            {
                Pos.y+=Vel.y;
            }
        }
        else
        {
            if(GM.GetBlockAt((int)(Pos.x),(int)(Pos.y+Vel.y+Radius)).Depth == Height)
            {
                Pos.y+=Vel.y;
            }
        }
        Vel.Divide(1.1f);
    }

    @Override
    public void Update(GameManager GM)
    {
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
            GM.Entity.Add(new Projectile(Pos,0,1000));
        }
    }

    public void draw(float x, float y, GameManager GM)
    {
        GM.textSize(32);
        GM.fill(0, 0, 0);
        GM.text("Zoom:"+GM.GetZoom(), 10, 30);
        GM.text("MouseX:"+GM.MX, 10, 60);
        GM.text("MouseY:"+GM.MY, 10, 90);
        GM.text("MouseYf:"+GM.MYF, 10, 120);

        x=(Pos.x-x)*GM.Render.Zoom;
        y=(Pos.y-y)*GM.Render.Zoom;

        GM.ellipseMode(2);
        GM.fill(60);
        GM.stroke(30);
        GM.strokeWeight(1);
        GM.ellipse(x, y, Radius*GM.Render.Zoom, Radius*GM.Render.Zoom);
    }
}
