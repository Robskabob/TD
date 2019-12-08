package TD.Objects.Entites;

import TD.Main.GameManager;
import TD.Objects.Unit;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Missle extends Projectile {
    public Missle(Vec2 pos, float dir, float speed, int hight) {
        super(pos, dir, speed, hight);
        Speed = speed/2;
        Friction = speed/4;
    }

    Unit Lock;

    @Override
    public void Update(PApplet GM)
    {
        life--;
        if(life<0)
        {
            Dead = true;
        }
        if(Lock == null) {
            Lock = GameManager.GM.Entity.GetUnitNearPoint(Pos,5f);
        }
        else {
            Vel.Add(Lock.Pos.sub(Pos).normilize().mult(Speed));

            Unit U = GameManager.GM.Entity.GetUnitNearPoint(Pos,.1f);

            if(U!=null)
            {
                U.HP-=10;
                Dead = true;
            }
        }
    }
}
