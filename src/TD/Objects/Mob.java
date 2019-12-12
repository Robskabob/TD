package TD.Objects;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Util.Vec2;

public abstract class Mob extends Entity
{
    public Vec2 Vel;
    public float Speed = 1;
    public float Friction = 1;

    public void OnCollide(){}

    public boolean CanColl(int h)
    {
        return Height == h;
    }

    public void Physics(Engine E)
    {
        if(Vel.x<0)
        {
            if(CanColl(E.GetBlockAt((int)(Pos.x+Vel.x-Radius),(int)(Pos.y)).height))
            {
                Pos.x+=Vel.x;
            }
            else
            {
                OnCollide();
            }
        }
        else
        {
            if(CanColl(E.GetBlockAt((int)(Pos.x+Vel.x+Radius),(int)(Pos.y)).height))
            {
                Pos.x+=Vel.x;
            }
            else
            {
                OnCollide();
            }
        }
        if(Vel.y<0)
        {
            if(CanColl(E.GetBlockAt((int)(Pos.x),(int)(Pos.y+Vel.y-Radius)).height))
            {
                Pos.y+=Vel.y;
            }
            else
            {
                OnCollide();
            }
        }
        else
        {
            if(CanColl(E.GetBlockAt((int)(Pos.x),(int)(Pos.y+Vel.y+Radius)).height))
            {
                Pos.y+=Vel.y;
            }
            else
            {
                OnCollide();
            }
        }
        Vel.Divide(Friction+1);
    }


}
