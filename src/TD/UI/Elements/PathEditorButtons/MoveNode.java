package TD.UI.Elements.PathEditorButtons;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.PathModeButton;
import TD.Util.Vec2;
import processing.core.PApplet;

public class MoveNode extends PathModeButton {
    public MoveNode(Vec2 pos, Vec2 scale, PathSystem PathSys) {
        super(pos, scale, PathEditorUI.PathMode.MoveNode, PathSys);
    }

    @Override
    public void Selected(PApplet PA)
    {
        if(!over)
        {
            if (PA.mousePressed) {

                if(Pather.Sel == null)
                {
                    PathSystem.Node N = Pather.GetNodeNearMouse(3);
                    if(N != null) {
                        Pather.Sel = N;
                    }
                }
                else{
                    Pather.Sel.Pos.Set(GameManager.GM.MXF, GameManager.GM.MYF);
                    Pather.Sel.PlaceNode();
                }
            }
            else if(Pather.Sel != null)
            {
                Pather.las = Pather.Sel;
                Pather.Sel = null;
            }
        }
    }
}
