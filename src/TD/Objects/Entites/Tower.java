package TD.Objects.Entites;

import TD.Main.GameManager;
import TD.Objects.Entites.Interfaces.Weapon;
import TD.Objects.Entity;
import TD.Objects.Unit;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Tower extends Entity
{
    Weapon W = new Weapon("gay",15,5,.8f,5);
    float fr;

    public Tower(int x, int y)
    {
        Pos = new Vec2(x,y);
        Radius = 1;
    }

    @Override
    public void Update(PApplet PA) {
        GameManager GM = GameManager.GM;
        Unit Target = GM.Entity.GetUnitNearPoint(Pos,W.Range);
        if(Target != null) {
            Dir = PA.atan2(Target.Pos.y - Pos.y, Target.Pos.x - Pos.x);
            fr = W.Shoot(Pos, Dir,fr);
        }
    }

    @Override
    public void draw(PApplet GM, float x, float y, float rot, float scale) {
        GM.pushMatrix();
        GM.translate(x,y);

        GM.fill(100);
        GM.rect(.1f*scale,.1f*scale,(Radius-.1f)*scale,(Radius-.1f)*scale);


        GM.translate((Radius/2)*scale,(Radius/2)*scale);
        GM.rotate(rot);
        GM.ellipse(0, 0, (Radius/2-.2f)*scale, (Radius/2-.2f)*scale);
        GM.rect(0,-(Radius/8)*scale,(Radius)*scale,(Radius/8)*scale);

        GM.popMatrix();
    }
}
