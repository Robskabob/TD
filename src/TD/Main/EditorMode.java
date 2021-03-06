package TD.Main;

import TD.Objects.Data.Map;
import TD.Objects.Entites.Player;
import TD.UI.Elements.FloatBox;
import TD.UI.Elements.PathEditorUI;
import TD.UI.Elements.TextBox;
import TD.UI.Elements.TowerUI.TowerUI;
import TD.Util.Vec2;

public class EditorMode extends GameMode
{
    public Player P;

    public EditorMode(Engine E, Map M) {
        super(E,M);
        P = new Player(M.Width/2f,M.Height/2f,.5f,M.GetBlock(M.Width/2,M.Height/2).height,1);
        E.EntitySys.Add(P);
        E.RenderSys.Focus = P;
        E.GM.UI.Elements.add(new PathEditorUI(E.GM.UI,E.PathSys));
        E.GM.UI.Elements.add(new TowerUI());
        E.GM.UI.Elements.add(new TextBox(new Vec2(190,190),new Vec2(250,40)));
    }

    @Override
    public void SetUp() {
    }

    @Override
    public void Update() {

    }
}
