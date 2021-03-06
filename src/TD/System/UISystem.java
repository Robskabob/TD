package TD.System;

import TD.Main.GameManager;
import TD.UI.Elements.*;
import processing.core.PApplet;

import java.util.ArrayList;

public class UISystem extends MainSystem
{
    public Theme T = new Theme();
    public ArrayList<Element> Elements = new ArrayList<>();
    public Selectable Sel;

    public UISystem(GameManager pa) {
        super(pa);
    }

    public boolean IsMouseOver(Selectable S)
    {
        return  (( GM.mouseX >= S.GetPos().x && GM.mouseX <= S.GetPos().x + S.GetScale().x) && ( GM.mouseY >= S.GetPos().y && GM.mouseY <= S.GetPos().y + S.GetScale().y));
    }

    @Override
    public void setup() {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(PApplet PA) {
        for (Element element : Elements) {
            UpdateElement(PA, element);
        }
    }

    private void UpdateElement(PApplet PA,Element E) {
        E.draw(GM,T);
        if(E instanceof Selectable)
        {
            Selectable S = (Selectable)E;
            if(S!=Sel) {
                if (IsMouseOver(S)) {
                    S.MouseOver(PA);
                    if (GM.mousePressed && GM.mouseButton == PApplet.LEFT) {
                        if (Sel != S) {
                            if (Sel != null)
                                Sel.DeSelect(GM);
                            if (S.Select(PA))
                                Sel = S;
                            else
                                Sel = null;
                        }
                    }
                }
            }
            else
                S.Selected(PA);

            if(GM.InPut.GetKey(S.ShortCut()))
            {
                if(Sel != null)
                    Sel.DeSelect(GM);
                if(S.Select(PA))
                Sel = S;
            }
        }
        if(E instanceof ElementGroup)
        {
            ElementGroup EG = (ElementGroup)E;
            for(int i = 0; i < EG.GetLength(); i++)
            {
                UpdateElement(PA,EG.GetElement(i));
            }
        }
    }
}
