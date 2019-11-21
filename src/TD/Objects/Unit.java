package TD.Objects;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.Util.Vec2;

public class Unit extends Mob
{
    public PathSystem.Path path;

    public Unit(PathSystem.Path Path)
    {
        //Pos = path.
    }

    @Override
    public void Update(GameManager GM) {
        if(path!=null)
        {
            Vel.Add(path.getNext(Pos).sub(Pos).normilize());
        }
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
