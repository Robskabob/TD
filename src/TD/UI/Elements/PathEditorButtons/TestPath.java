package TD.UI.Elements.PathEditorButtons;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.UI.Elements.Theme;
import TD.Util.Vec2;
import processing.core.PApplet;

public class TestPath extends PathModeButton {
    public TestPath(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.TestPath, PathSys);
    }
    boolean a,b,s;
    @Override
    public boolean Select(PApplet PA) {
        s = !a;
        if(b)
        {
            b=false;
            Selected = !Selected;
        }
        return false;
    }

    @Override public void draw(PApplet PA, Theme T)
    {
        if(Selected)
        {
            if(!over)
            {
                if (PA.mousePressed) {
                    PathSystem.Node N = Pather.GetNodeNearMouse(3);
                    if(N != null) {
                        if (PA.mouseButton == GameManager.LEFT) {
                            Pather.Sel = N;
                        }
                        if (PA.mouseButton == GameManager.RIGHT) {
                            Pather.las = N;
                        }
                    }
                }
                Pather.TestPath();
            }
        }
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
        if(s==a)
            b = true;
        a=s;
    }
}
