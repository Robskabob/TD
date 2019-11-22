package TD.UI.Elements;

import TD.Util.Vec2;

import TD.UI.Elements.PathEditorUI.*;

public class PathModeButton implements Button
{
    private Vec2 Pos;
    private Vec2 Scale;

    public PathModeButton(Vec2 pos, Vec2 scale, PathMode m)
    {
        Pos = pos;
        Scale = scale;
        Mode = m;
    }


    PathMode Mode;

    @Override
    public char ShortCut() {
        return Mode.ShortCut;
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
        return null;
    }

    @Override
    public Vec2 GetScale() {
        return null;
    }

    @Override
    public void SetPos(Vec2 val) {

    }

    @Override
    public void SetScale(Vec2 val) {

    }
}
