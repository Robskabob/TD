package TD.Objects;

import TD.Main.GameManager;

public class Unit extends Mob
{

    @Override
    public void Update(GameManager GM) {

    }

    @Override
    public void draw(GameManager GM, float x, float y, float rot, float scale) {
        GM.pushMatrix();
        GM.translate(x,y);
        GM.rotate(rot);

        GM.ellipseMode(2);
        GM.fill(60);
        GM.stroke(30);
        GM.strokeWeight(1);
        GM.ellipse(0, 0, Radius*scale, Radius*scale);

        GM.popMatrix();
    }
}
