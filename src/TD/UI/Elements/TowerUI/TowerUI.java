package TD.UI.Elements.TowerUI;

import TD.Main.GameManager;
import TD.Objects.Entites.Interfaces.Weapon;
import TD.Objects.Entites.Missile;
import TD.Objects.Entites.Projectile;
import TD.UI.Elements.Element;
import TD.UI.Elements.ElementGroup;
import TD.UI.Elements.Theme;
import TD.Util.Vec2;
import processing.core.PApplet;

public class TowerUI extends Element implements ElementGroup {
    public Weapon Sel;
    public TowerButton[] Buttons;

    public TowerUI()
    {
        int s = GameManager.GM.height/9;
        int h = s - GameManager.GM.height/100;
        int si = (s-h)/2;

        int c = 4;

        Buttons = new TowerButton[]{
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("MG",new Projectile(),10,5,1,25,.5f),this),
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("sniper",new Projectile(),100,50,3,1,.001f),this),
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("Missile",new Missile(),20,20,1,5,.1f),this),
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("Missile MG",new Missile(),15,10,3,10,.2f),this),
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
            PA.rectMode(0);
            GameManager GM = GameManager.GM;
            float z = GM.GetZoom();
            PA.square((GM.MX-GM.P.Pos.x)*z+GM.width/2,(GM.MY-GM.P.Pos.y)*z+GM.height/2, z);
            PA.text(Sel.Name,PA.mouseX, PA.mouseY);
            PA.rectMode(1);
        }
    }
}
