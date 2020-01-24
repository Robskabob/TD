package TD.UI.Elements;

import TD.Util.Vec2;
import processing.core.PApplet;

public class FloatBox extends TextBox {
    public FloatBox(Vec2 pos, Vec2 scale) {
        super(pos, scale);
    }

    @Override
    public boolean isCharValid(char c)
    {
        return Character.isDigit(c)||(c=='.'&&!Value.contains("."));
    }
}
