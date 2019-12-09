package TD.Objects.Entites.Interfaces;

import TD.Main.GameManager;
import TD.Objects.Entites.Projectile;
import TD.Util.Vec2;

public class Weapon
{
    public String Name;
    public float Range;
    public float Damage;
    public float Speed;
    public float FireRate;
    public float Spread;
    public Projectile P;

    public Weapon(String name, Projectile p, float range, float damage, float speed, float fireRate, float spread) {
        Name = name;
        P = p;
        Range = range;
        Damage = damage;
        Speed = speed;
        FireRate = fireRate;
        Spread = spread;
    }

    public float Shoot(Vec2 Pos, float Dir,float fr)
    {
        GameManager GM = GameManager.GM;
        if(fr>1) {
            fr-=1;
            GM.Entity.Add(P.New(Pos, Dir+GM.random(-Spread,Spread), Speed, 3));
        }
        else {
            fr+=FireRate/GM.frameRate;
        }
        return fr;
    }
}
