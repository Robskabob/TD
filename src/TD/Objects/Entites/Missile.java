package TD.Objects.Entites;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Objects.Unit;
import TD.Util.Vec2;

public class Missile extends Projectile {

    public Missile() { }

    @Override
    public Missile New(Vec2 pos, float dir, float radius, float speed,int height)
    {
        return new Missile(pos, dir, radius, speed, height);
    }

    public Missile(Vec2 pos, float dir, float radius, float speed, int height) {
        super(pos, dir, radius, speed*4, height);
        Speed = speed;
        Friction = speed/4;
    }

    Unit Lock;

    @Override
    public void Update(Engine E)
    {
        life--;
        if(life<0)
        {
            Dead = true;
        }
        if(Lock == null) {
            Lock = E.EntitySys.GetUnitNearPoint(Pos,5f);
        }
        else {
            Vel.Add(Lock.Pos.sub(Pos).normilize().mult(Speed));

            Unit U = E.EntitySys.GetUnitNearPoint(Pos,Lock.Radius*2);

            if(U!=null)
            {
                U.HP-=10;
                Dead = true;
            }
        }
    }
}
