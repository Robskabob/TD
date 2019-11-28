package TD.UI.Elements.PathEditorButtons;

import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;

public class HidePath extends PathModeButton {
    public HidePath(Vec2 pos, Vec2 scale) {
        super(pos, scale, PathEditorUI.PathMode.HidePath);
    }

    @Override
    public boolean Select() {
        Selected = true;
        return false;
    }
}
