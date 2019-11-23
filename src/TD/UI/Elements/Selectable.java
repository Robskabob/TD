package TD.UI.Elements;

import TD.Util.Vec2;

public interface Selectable
{
    char ShortCut();

    boolean Select();
    void Selected();
    void DeSelect();

    void MouseOver();

    Vec2 GetPos();
    Vec2 GetScale();
    void SetPos(Vec2 val);
    void SetScale(Vec2 val);
}
