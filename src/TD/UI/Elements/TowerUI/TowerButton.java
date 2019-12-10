package TD.UI.Elements.TowerUI;

import TD.Objects.Entites.Interfaces.Weapon;
import TD.UI.Elements.Button;
import TD.UI.Elements.Theme;
import TD.Util.Vec2;
import processing.core.PApplet;

import java.util.Scanner;

public class TowerButton extends Button {
    public TowerButton(Vec2 pos, Vec2 scale, Weapon tw, TowerUI tui)
    {
        Pos = pos;
        Scale = scale;
        TW = tw;
        TUI = tui;
    }

    protected TowerUI TUI;
    public Weapon TW;

    @Override
    public char ShortCut() {
        return 0;
    }

    @Override
    public boolean Select() {
        Selected = true;
        TUI.Sel = TW;
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
        if(Selected)
            PA.fill(50,50,250);
        else if(over)
            PA.fill(155,55,100);
        else
            PA.fill(50,50,50);
        PA.stroke(T.WindowBorder);
        PA.rect(Pos.x,Pos.y,Pos.x+Scale.x,Pos.y+Scale.y);
        PA.fill(200,200,200);
        PA.textSize(Scale.x);
        PA.pushMatrix();
        PA.translate(Pos.x+Scale.x/5,Pos.y);
        PA.rotate(PApplet.HALF_PI);
        if(TW == null)
            PA.text("Deselect", 0,0);
        else
            PA.text(TW.Name, 0,0);
        PA.popMatrix();
        over = false;
    }
}
