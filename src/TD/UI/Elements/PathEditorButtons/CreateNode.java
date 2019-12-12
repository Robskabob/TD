package TD.UI.Elements.PathEditorButtons;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;
import processing.core.PApplet;

public class CreateNode extends PathModeButton {
    public CreateNode(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.CreateNode, PathSys);
    }

    @Override
    public void Selected(PApplet PA)
    {
        if(Pather.GetNodeNearMouse(3) == null && !over && PA.mousePressed && PA.mouseButton == GameManager.LEFT)
        {
            Pather.CreateNode();
        }
    }
}

