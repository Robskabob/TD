package TD.Objects.Entites.Interfaces;

import TD.Main.GameManager;
import TD.Objects.Entites.Missle;
import TD.Objects.Entites.Projectile;
import TD.Util.Vec2;

public class Weapon
{
    public String Name;
    public float Range;
    public float Dammage;
    public float Speed;
    public float FireRate;
    public float Spread;

    public Weapon(String name,float range, float dammage, float speed, float fireRate) {
        Range = range;
        Dammage = dammage;
        Speed = speed;
        FireRate = fireRate;
    }

    public float Shoot(Vec2 Pos, float Dir,float fr)
    {
        GameManager GM = GameManager.GM;
        if(fr>1) {
            fr-=1;
            GM.Entity.Add(new Missle(Pos, Dir+GM.random(-.1f,.1f), Speed, 3));
        }
        else {
            fr+=FireRate/GM.frameRate;
        }
        return fr;
    }
}
