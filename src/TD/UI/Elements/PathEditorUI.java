package TD.UI.Elements;

import TD.System.PathSystem;
import TD.System.UISystem;
import TD.UI.Elements.PathEditorButtons.*;
import TD.Util.Vec2;
import processing.core.PApplet;

public class PathEditorUI extends Element implements ElementGroup
{
    protected PathSystem Pather;
    public PathEditorUI(UISystem ui,PathSystem PathSys)
    {
        Pather = PathSys;
        int s = ui.PA.width/9;
        int w = s - ui.PA.width/100;
        int si = (s-w)/2;

        int c = 7;

        Buttons = new PathModeButton[]{
                new MoveNode(new Vec2(si += s, (s-w)/2), new Vec2(w, w/(c-1)),Pather),
                new CreateNode(new Vec2(si += s, (s-w)/2), new Vec2(w, w/(c-1)),Pather),
                new RemoveNode(new Vec2(si += s, (s-w)/2), new Vec2(w, w/(c-1)),Pather),
                new CreateConnection(new Vec2(si += s, (s-w)/2), new Vec2(w, w/(c-1)),Pather),
                new RemoveConnection(new Vec2(si += s, (s-w)/2), new Vec2(w, w/(c-1)),Pather),
                new TestPath(new Vec2(si += s, (s-w)/2), new Vec2(w, w/(c-1)),Pather),
                new HidePath(new Vec2(si += s, (s-w)/2), new Vec2(w, w/(c-1)),Pather),
        };
    }
    public PathModeButton[] Buttons;

    @Override
    public Element GetElement(int i) {
        return Buttons[i];
    }

    @Override
    public int GetLength() {
        return Buttons.length;
    }

    public enum PathMode
    {
        MoveNode('m'),
        CreateNode('n'),
        RemoveNode('N'),
        CreateConnection('c'),
        RemoveConnection('C'),
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
