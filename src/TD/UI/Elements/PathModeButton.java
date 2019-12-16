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

    @Override
    public char ShortCut() {
        return Mode.ShortCut;
    }

    @Override
    public boolean Select(PApplet PA) {
        Selected = true;
        return true;
    }

    @Override
    public void Selected(PApplet PA) {

    }

    @Override
    public void MouseOver(PApplet PA) {
        over = true;
    }

    public void draw(PApplet PA, Theme T)
    {
        if(Selected)
            PA.fill(50,50,250);
        else if(over)
            PA.fill(155,55,100);
        else
            PA.fill(50,50,50);
        PA.stroke(T.WindowBorder);
        PA.rect(Pos.x,Pos.y,Pos.x+Scale.x,Pos.y+Scale.y);
        PA.fill(200,200,200);
        PA.textSize(Scale.y);
        PA.text(Mode.name(),Pos.x,Pos.y+Scale.y/1.25f);
        over = false;
    }
}
