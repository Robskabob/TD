package TD.UI.Elements.TowerUI;

import TD.Main.GameManager;
import TD.Objects.Entites.Interfaces.Weapon;
import TD.UI.Elements.Element;
import TD.UI.Elements.ElementGroup;
import TD.UI.Elements.Theme;
import processing.core.PApplet;

public class TowerUI extends Element implements ElementGroup {
    public Weapon Sel;
    public TowerButton[] Buttons;

    public TowerUI()
    {
        Buttons = new TowerButton[]{
                new TowerButton(),
        };
    }

    @Override
    public Element GetElement(int i) {
        return Buttons[i];
    }

    @Override
    public int GetLength() {
        return Buttons.length;
    }

    @Override
    public void draw(PApplet PA, Theme T) {
        if (Sel != null) {
            PA.square(PA.mouseX, PA.mouseY, GameManager.GM.GetZoom());
            PA.text(Sel.Name,PA.mouseX, PA.mouseY);
        }
    }
}
