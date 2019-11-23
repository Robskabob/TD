package TD.UI.Elements;

import TD.Util.Vec2;

import TD.UI.Elements.PathEditorUI.*;
import processing.core.PApplet;

public class PathModeButton extends Button
{
    public PathModeButton(Vec2 pos, Vec2 scale, PathMode m)
    {
        Pos = pos;
        Scale = scale;
        Mode = m;
    }

    PathMode Mode;
    boolean over;

    @Override
    public char ShortCut() {
        return Mode.ShortCut;
    }

    @Override
    public boolean Select() {
        return false;
    }

    @Override
    public void Selected() {
        Selected = true;
    }

    @Override
    public void DeSelect() {
        Selected = false;
    }

    @Override
    public void MouseOver() {
        over = true;
    }

    public void draw(PApplet PA, Theme T)
    {
        if(Selected)
            PA.fill(90,90,150);
        else if(over)
            PA.fill(95,95,100);
        else
            PA.fill(90,90,90);
        PA.stroke(T.WindowBorder);
        PA.rect(Pos.x,Pos.y,Pos.x+Scale.x,Pos.y+Scale.y);
        over = false;
    }
}
