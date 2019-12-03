package TD.UI.Elements;
import TD.UI.Elements.Element;
import TD.Util.Vec2;

public abstract class Button extends Element implements Selectable
{
    public boolean Selected = false;
    public boolean over = false;

    @Override
    public boolean Select() {
        Selected = true;
        return false;
    }

    @Override
    public void DeSelect() {
        Selected = false;
    }

    @Override
    public Vec2 GetPos() {
        return Pos;
    }

    @Override
    public Vec2 GetScale() {
        return Scale;
    }

    @Override
    public void SetPos(Vec2 val) {
        Pos = val;
    }

    @Override
    public void SetScale(Vec2 val) {
        Scale = val;
    }
}
