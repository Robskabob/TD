package TD.UI.Elements.PathEditorButtons;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.System.PathSystem.Node;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;
import processing.core.PApplet;

public class CreateConnection extends PathModeButton {
    public CreateConnection(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.CreateConnection, PathSys);
    }

    @Override
    public void Selected(PApplet PA)
    {
        if(!over)
        {
            if (PA.mousePressed) {
                Node N = Pather.GetNodeNearMouse(3);
                if(N != null) {
                    if (PA.mouseButton == GameManager.LEFT) {
                        if(Pather.Sel != null)
                        Pather.las = Pather.Sel;
                        Pather.Sel = N;
                        if(Pather.las != null)
                            Pather.CreateConnection(Pather.las,N);
                    }
                    if (PA.mouseButton == GameManager.RIGHT) {
                        Pather.CreateConnection(Pather.Sel,N);
                    }
                }
            }
        }
    }
}
