package TD.UI.Elements;

import TD.System.UISystem;
import TD.Util.Vec2;
import processing.core.PApplet;

public class PathEditorUI extends Element
{
    public PathEditorUI(UISystem ui)
    {
        for(int i = 0; i < Buttons.length; i++)
        {
            ui.Elements.add(Buttons[i]);
        }
    }

    public PathModeButton[] Buttons = {
            new PathModeButton(new Vec2(00,0),new Vec2(75,75),PathMode.MoveNode),
            new PathModeButton(new Vec2(100,0),new Vec2(75,75),PathMode.CreateNode),
            new PathModeButton(new Vec2(30,0),new Vec2(75,75),PathMode.RemoveNode),
            new PathModeButton(new Vec2(45,0),new Vec2(75,75),PathMode.CreateConnection),
            new PathModeButton(new Vec2(60,0),new Vec2(75,75),PathMode.RemoveConnection),
            new PathModeButton(new Vec2(75,0),new Vec2(75,75),PathMode.TestPath),
            new PathModeButton(new Vec2(90,0),new Vec2(75,75),PathMode.HidePath),
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

    public void draw(PApplet PA, Theme T)
    {
        //for(int i = 0; i < Buttons.length; i++)
        //{
        //    Buttons[i].draw(PA,T);
        //}
    }
}
