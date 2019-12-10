package TD.System;

import TD.Main.GameManager;
import TD.UI.Elements.*;
import TD.UI.Elements.TowerUI.TowerUI;
import processing.core.PApplet;

import java.util.ArrayList;

public class UISystem extends System
{
    public Theme T = new Theme();
    public ArrayList<Element> Elements = new ArrayList<Element>();
    public Selectable Sel;


    public UISystem(GameManager gm) {
        super(gm);
    }

    public boolean IsMouseOver(Selectable S)
    {
        return  (( PA.mouseX >= S.GetPos().x && PA.mouseX <= S.GetPos().x + S.GetScale().x) && ( PA.mouseY >= S.GetPos().y && PA.mouseY <= S.GetPos().y + S.GetScale().y));
    }

    @Override
    public void setup() {
        Elements.add(new PathEditorUI(this,GameManager.GM.Pather));
        Elements.add(new TowerUI());
    }

    @Override
    public void draw() {
        for(int i = 0; i < Elements.size(); i++)
        {
            UpdateElement(Elements.get(i));
        }
    }

    private void UpdateElement(Element E) {
        E.draw(PA,T);
        if(E instanceof Selectable)
        {
            Selectable S = (Selectable)E;
            if(S!=Sel) {
                if (IsMouseOver(S)) {
                    S.MouseOver();
                    if (PA.mousePressed && PA.mouseButton == PApplet.LEFT) {
                        if (Sel != S) {
                            if (Sel != null)
                                Sel.DeSelect();
                            if (S.Select())
                                Sel = S;
                            else
                                Sel = null;
                        }
                    }
                }
            }
            else
                S.Selected();
            if(GameManager.GM.GetKey(S.ShortCut()))
            {
                if(Sel != null)
                    Sel.DeSelect();
                if(S.Select())
                Sel = S;
            }
        }
        if(E instanceof ElementGroup)
        {
            ElementGroup EG = (ElementGroup)E;
            for(int i = 0; i < EG.GetLength(); i++)
            {
                UpdateElement(EG.GetElement(i));
            }
        }
    }
}
