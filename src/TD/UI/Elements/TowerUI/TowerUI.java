package TD.UI.Elements.TowerUI;

import TD.Main.GameManager;
import TD.Objects.Entites.Interfaces.Weapon;
import TD.Objects.Entites.Missile;
import TD.Objects.Entites.Projectile;
import TD.Objects.Entites.Tower;
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

        int c = 5;

        Buttons = new TowerButton[]{
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),null,this),
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("MG",new Projectile(), .05f, 10,5,.1f,15,.5f),this),
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("sniper",new Projectile(), .15f, 100,50,1,.3f,0),this),
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("Missile",new Missile(), .3f, 20,20,.1f,1,.1f),this),
                new TowerButton(new Vec2((s-h)/2,si += s), new Vec2(h/(c-1),h),new Weapon("Missile MG",new Missile(), .2f, 15,10,.3f,5,.2f),this),
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
int w = 0;
    @Override
    public void draw(PApplet PA, Theme T) {
        if (Sel != null) {
            PA.rectMode(0);
            GameManager GM = GameManager.GM;
            float z = GM.E.GetZoom();
            Vec2 P = new Vec2((GM.MX-GM.E.RenderSys.Focus().x)*z+GM.width/2,(GM.MY-GM.E.RenderSys.Focus().y)*z+GM.height/2);
            PA.square(P.x,P.y, z);
            PA.text(Sel.Name,PA.mouseX, PA.mouseY);
            PA.rectMode(1);
            if(GM.mousePressed && GM.mouseButton == PApplet.LEFT) {
                if(w<0&&GM.E.EntitySys.GetTowerNearPoint(P,1)==null) {
                    GM.E.EntitySys.Add(new Tower(GM.MX,GM.MY,Sel));
                    w=100;
                }
            }
            w--;
        }
    }
}
