package TD.UI.Elements.PathEditorButtons;

import TD.System.PathSystem;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;

public class RemoveNode extends PathModeButton {
    public RemoveNode(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.RemoveNode, PathSys);
    }
}
