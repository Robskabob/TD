package TD.Main;

import TD.System.UISystem;
import processing.core.PApplet;

public class UITester extends PApplet
{
    public UISystem UI = new UISystem(this);

    public void settings(){
        size(displayWidth,displayHeight);
    }

    public void setup(){
        UI.setup();
    }

    public void draw() {
        UI.draw();
    }

}
