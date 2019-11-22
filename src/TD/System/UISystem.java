package TD.System;

import TD.UI.Elements.Element;
import TD.UI.Elements.Theme;
import processing.core.PApplet;

import java.util.ArrayList;

public class UISystem extends System
{
    public Theme T = new Theme();
    public ArrayList<Element> Elements = new ArrayList<Element>();

    public UISystem(PApplet gm) {
        super(gm);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        for(int i = 0; i < Elements.size(); i++)
        {
            Element E = Elements.get(i);
            E.draw();
        }
    }
}
