package TD.UI.Elements.PathEditorButtons;

import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;

public class TestPath extends PathModeButton {
    public TestPath(Vec2 pos, Vec2 scale) {
        super(pos, scale, PathEditorUI.PathMode.TestPath);
    }
    boolean a,b;
    @Override
    public boolean Select() {
        if(a)
        {
            a = false;
        }
        Selected = !Selected;
        return false;
    }
}
