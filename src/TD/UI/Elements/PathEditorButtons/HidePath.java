package TD.UI.Elements.PathEditorButtons;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.System.SaveSystem;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.UI.Elements.Theme;
import TD.Util.Vec2;
import processing.core.PApplet;

public class HidePath extends PathModeButton {
    public HidePath(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.HidePath, PathSys);
    }
    boolean a,b,s;
    @Override
    public boolean Select() {
        s = !a;
        if(b)
        {
            SaveSystem.SaveMap(GameManager.GM,"NEWMAP");
            b=false;
            Selected = !Selected;
            Pather.HidePath();
        }
        return false;
    }

    @Override public void draw(PApplet PA, Theme T)
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
        if(s==a)
            b = true;
        a=s;
    }
}
