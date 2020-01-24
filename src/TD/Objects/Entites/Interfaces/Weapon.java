package TD.Objects.Entites.Interfaces;

import TD.Main.GameManager;
import TD.Objects.Entites.Projectile;
import TD.System.EntitySystem;
import TD.Util.Vec2;

public class Weapon
{
    public String Name;
    public float Range;
    public float Damage;
    public float Speed;
    public float FireRate;
    public float Spread;
    public float Radius;
    public Projectile P;

    public Weapon(String name, Projectile p, float radius, float range, float damage, float speed, float fireRate, float spread) {
        Name = name;
        P = p;
        Radius = radius;
        Range = range;
        Damage = damage;
        Speed = speed;
        FireRate = fireRate;
        Spread = spread;
    }

    public float Shoot(EntitySystem ES, Vec2 Pos, float Dir, float fr)
    {
        if(fr>1) {
            fr-=1;
            ES.Add(P.New(Pos, Dir+(((float)Math.random()-.5f)*Spread),Radius, Speed, 3));
        }
        else {
            fr+=FireRate/30;
        }
        return fr;
    }
}
