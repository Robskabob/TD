package TD.UI.Elements;
import TD.Util.Vec2;
import processing.core.PApplet;

public abstract class Button extends Element implements Selectable
{
    public boolean Selected = false;
    public boolean over = false;

    @Override
    public boolean Select(PApplet PA) {
        Selected = true;
        return false;
    }

    @Override
    public void DeSelect(PApplet PA) {
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
