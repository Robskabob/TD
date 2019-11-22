package TD.System;

import TD.Main.GameManager;
import TD.UI.Elements.Element;
import TD.UI.Elements.Selectable;
import TD.UI.Elements.Theme;
import processing.core.PApplet;

import java.util.ArrayList;

public class UISystem extends System
{
    public Theme T = new Theme();
    public ArrayList<Element> Elements = new ArrayList<Element>();
    public Selectable Sel;

    public UISystem(PApplet gm) {
        super(gm);
    }

    public boolean IsMouseOver(Selectable S)
    {
        return  (( GM.mouseX >= S.GetPos().x && GM.mouseX <= S.GetPos().x + S.GetScale().x) && ( GM.mouseY >= S.GetPos().y && GM.mouseY <= S.GetPos().y + S.GetScale().y));
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        for(int i = 0; i < Elements.size(); i++)
        {
            Element E = Elements.get(i);
            E.draw(GM,T);
            if(E instanceof Selectable)
            {
                Selectable S = (Selectable)E;
                if(IsMouseOver(S))
                {
                    if(GM.mousePressed&&GM.mouseButton==PApplet.LEFT)
                    {
                        if(Sel != null)
                            Sel.DeSelect();
                        if(S.Select())
                            Sel = S;
                    }
                }
                //GM only
                //if(GameManager.GM.GetKey(S.ShortCut()))
                //{
                //    if(Sel != null)
                //        Sel.DeSelect();
                //    if(S.Select())
                //    Sel = S;
                //}
            }
        }
    }
}
