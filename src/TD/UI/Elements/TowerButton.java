package TD.UI.Elements;

import TD.Util.Vec2;

public class TowerButton implements Button
{
    Vec2 Pos;
    Vec2 Scale;

    @Override
    public char ShortCut() {
        return 'w';
    }

    @Override
    public boolean Select() {
        return false;
    }

    @Override
    public void Selected() {

    }

    @Override
    public void DeSelect() {

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
