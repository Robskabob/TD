package TD.UI.Elements;

import TD.System.PathSystem;
import TD.Util.Vec2;

import TD.UI.Elements.PathEditorUI.*;
import processing.core.PApplet;

public class PathModeButton extends Button
{
    protected PathSystem Pather;
    public PathModeButton(Vec2 pos, Vec2 scale, PathMode m, PathSystem PathSys)
    {
        Pather = PathSys;
        Pos = pos;
        Scale = scale;
        Mode = m;
    }

    public PathMode Mode;
    public boolean over;

    @Override
    public char ShortCut() {
        return Mode.ShortCut;
    }

    @Override
    public boolean Select() {
        Selected = true;
        return true;
    }

    @Override
    public void Selected() {

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
        PA.pushMatrix();
        if(Selected)
            PA.fill(50,50,250);
        else if(over)
            PA.fill(155,55,100);
        else
            PA.fill(50,50,50);
        PA.stroke(T.WindowBorder);
        PA.rect(Pos.x,Pos.y,Scale.x,Scale.y);
        PA.fill(200,200,200);
        PA.textSize(40);
        PA.text(Mode.name(),Pos.x,Pos.y+Scale.y/2);
        over = false;
        PA.popMatrix();
    }
}
