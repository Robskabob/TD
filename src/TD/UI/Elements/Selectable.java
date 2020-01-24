package TD.UI.Elements;

import TD.Util.Vec2;
import processing.core.PApplet;

public interface Selectable
{
    char ShortCut();

    boolean Select(PApplet PA);
    void Selected(PApplet PA);
    void DeSelect(PApplet PA);

    void MouseOver(PApplet PA);

    Vec2 GetPos();
    Vec2 GetScale();
    void SetPos(Vec2 val);
    void SetScale(Vec2 val);
}
