package TD.UI.Elements.PathEditorButtons;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;
import processing.core.PApplet;

public class RemoveNode extends PathModeButton {
    public RemoveNode(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.RemoveNode, PathSys);
    }

    @Override
    public void Selected(PApplet PA)
    {
        PathSystem.Node N = Pather.GetNodeNearMouse(1);
        if(N != null && !over && PA.mousePressed && PA.mouseButton == GameManager.LEFT)
        {
            Pather.RemoveNode(N);
        }
    }
}
