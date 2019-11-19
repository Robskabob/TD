package TD.Objects.Entites;

import TD.Main.GameManager;
import TD.Objects.Entity;
import TD.Util.Vec2;

public class Tower extends Entity
{
    public Tower(int x, int y)
    {
        Pos = new Vec2(x,y);
        Radius = 1;
    }

    @Override
    public void Update(GameManager GM) {
        Rot = GM.atan2(GM.P.Pos.y-Pos.y-Radius/2,GM.P.Pos.x-Pos.x-Radius/2);
    }

    @Override
    public void draw(GameManager GM, float x, float y, float rot, float scale) {
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
