package TD.Objects.Entites;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Objects.Entites.Interfaces.Weapon;
import TD.Objects.Entity;
import TD.Objects.Unit;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Tower extends Entity
{
    Weapon W = new Weapon("gay",new Missile(),.1f,15,5,.1f,5,.1f);
    float fr;

    public Tower(int x, int y) {
        Pos = new Vec2(x,y);
        Radius = 1;
    }

    public Tower(int x, int y, Weapon w) {
        W = w;
        Pos = new Vec2(x,y);
        Radius = 1;
    }

    @Override
    public void Update(Engine E) {
        Unit Target = E.EntitySys.GetUnitNearPoint(Pos,W.Range);
        if(Target != null) {
            Dir = PApplet.atan2(Target.Pos.y - Pos.y, Target.Pos.x - Pos.x);
            fr = W.Shoot(E.EntitySys,Pos, Dir,fr);
        }
    }

    @Override
    public void draw(PApplet PA, float x, float y, float rot, float scale) {
        PA.pushMatrix();
        PA.translate(x,y);

        PA.fill(100);
        PA.rect(.1f*scale,.1f*scale,(Radius-.1f)*scale,(Radius-.1f)*scale);


        PA.translate((Radius/2)*scale,(Radius/2)*scale);
        PA.rotate(rot);
        PA.ellipse(0, 0, (Radius/2-.2f)*scale, (Radius/2-.2f)*scale);
        PA.rect(0,-(Radius/8)*scale,(Radius)*scale,(Radius/8)*scale);

        PA.popMatrix();
    }
}
