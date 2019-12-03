package TD.Main;

import TD.System.UISystem;
import processing.core.PApplet;

public class UITester extends PApplet
{
    public static void main(String[] args) {
        PApplet.main("TD.Main.UITester");
    }

    public UISystem UI = new UISystem(null);

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
