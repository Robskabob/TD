package TD.UI.Elements;

import TD.Util.Vec2;

public class PathEditorUI
{
    public PathModeButton[] Buttons = {
            new PathModeButton(new Vec2(0,0),new Vec2(0,0),PathMode.MoveNode),
            new PathModeButton(new Vec2(0,0),new Vec2(0,0),PathMode.CreateNode),
            new PathModeButton(new Vec2(0,0),new Vec2(0,0),PathMode.RemoveNode),
            new PathModeButton(new Vec2(0,0),new Vec2(0,0),PathMode.CreateConnection),
            new PathModeButton(new Vec2(0,0),new Vec2(0,0),PathMode.RemoveConnection),
            new PathModeButton(new Vec2(0,0),new Vec2(0,0),PathMode.TestPath),
            new PathModeButton(new Vec2(0,0),new Vec2(0,0),PathMode.HidePath),
    };

    public enum PathMode
    {
        MoveNode('m'),
        CreateNode('n'),
        RemoveNode('r'),
        CreateConnection('N'),
        RemoveConnection('R'),
        TestPath('t'),
        HidePath('h');

        PathMode(char shortCut)
        {
            ShortCut = shortCut;
        }

        public char ShortCut;
    }
}
