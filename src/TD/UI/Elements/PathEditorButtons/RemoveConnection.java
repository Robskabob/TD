package TD.UI.Elements.PathEditorButtons;

import TD.System.PathSystem;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;

public class RemoveConnection extends PathModeButton {
    public RemoveConnection(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.RemoveConnection, PathSys);
    }
}
