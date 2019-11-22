package TD.UI.Elements;

import TD.Util.Vec2;
import processing.core.PApplet;

public class Element
{
    public Vec2 Pos;
    public Vec2 Scale;

    public void draw(PApplet PA, Theme T)
    {
        PA.fill(T.WindowBackground);
        PA.stroke(T.WindowBorder);
        PA.rect(Pos.x,Pos.y,Pos.x+Scale.x,Pos.y+Scale.y);
    }
}
