package TD.UI.Elements;

import TD.System.UISystem;
import TD.UI.Elements.PathEditorButtons.*;
import TD.Util.Vec2;
import processing.core.PApplet;

public class PathEditorUI extends Element
{
    public PathEditorUI(UISystem ui)
    {
        int s = ui.GM.width/9;
        int w = s - ui.GM.width/100;
        int si = (s-w)/2;

        Buttons = new PathModeButton[]{
                new MoveNode(new Vec2(si += s, (s-w)/2), new Vec2(w, w/6)),
                new CreateNode(new Vec2(si += s, (s-w)/2), new Vec2(w, w/6)),
                new RemoveNode(new Vec2(si += s, (s-w)/2), new Vec2(w, w/6)),
                new CreateConnection(new Vec2(si += s, (s-w)/2), new Vec2(w, w/6)),
                new RemoveConnection(new Vec2(si += s, (s-w)/2), new Vec2(w, w/6)),
                new TestPath(new Vec2(si += s, (s-w)/2), new Vec2(w, w/6)),
                new HidePath(new Vec2(si += s, (s-w)/2), new Vec2(w, w/6)),
        };

        for(int i = 0; i < Buttons.length; i++)
        {
            ui.Elements.add(Buttons[i]);
        }
    }
    public PathModeButton[] Buttons;

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

    public void draw(PApplet PA, Theme T)
    {
        //for(int i = 0; i < Buttons.length; i++)
        //{
        //    Buttons[i].draw(PA,T);
        //}
    }
}
