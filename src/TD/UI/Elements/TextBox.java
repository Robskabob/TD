package TD.UI.Elements;

import TD.Main.GameManager;
import TD.System.PathSystem;
import TD.Util.Vec2;
import processing.core.PApplet;

public class TextBox extends Element implements Selectable
{

    public TextBox(Vec2 pos, Vec2 scale)
    {
        Pos = pos;
        Scale = scale;
    }

    public boolean Selected = false;
    public boolean over = false;

    public String Value ="";
    int courser=0;

    public char shortcut;

    @Override
    public char ShortCut() {
        return shortcut;
    }

    int presstime = 0;
    char lastc = ' ';
    @Override
    public void Selected(PApplet PA) {
        if((PA.frameCount/15)%3==0||PA.keyPressed) {
            float w = PA.textWidth(Value.substring(0, courser));
            PA.rect(Pos.x + w - 1, Pos.y, Pos.x + w + 1, Pos.y + Scale.y);
        }
        if(PA.keyPressed)
        {
            if(PA.key != lastc)
            {
                lastc = PA.key;
                presstime = 0;
            }
            if(PA.keyCode == PA.LEFT)
            {
                if(courser>0)
                courser--;
            }
            else if(PA.keyCode == PA.RIGHT)
            {
                if(courser<Value.length())
                courser++;
            }
            if(presstime==0)
            {
                AddKey(PA);
            }
            if(presstime>25&&presstime%3==0)
            {
                AddKey(PA);
            }
            presstime++;
        }
        else
        presstime = 0;
    }

    public boolean isCharValid(char c){return true;};

    private void AddKey(PApplet PA)
    {
        char c = PA.key;
        if(c == '\b'&&Value.length()!=0)
        {
            Value = Value.substring(0,courser-1)+Value.substring(courser);
            courser--;
        }
        else if(isCharValid(c)) {
            Value = Value.substring(0,courser)+c+Value.substring(courser);
            courser++;
        }
    }

    @Override
    public void MouseOver(PApplet PA) {
        over = true;
    }

    @Override
    public boolean Select(PApplet PA) {
        Selected = true;
        GameManager.GM.InPut.Disable = true;
        GameManager.GM.E.Pause();
        return true;
    }

    @Override
    public void DeSelect(PApplet PA) {
        Selected = false;
        GameManager.GM.InPut.Disable = false;
        GameManager.GM.E.Resume();
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

    @Override
    public void draw(PApplet PA, Theme T) {
        super.draw(PA, T);
        if(Selected)
            PA.fill(50,50,250);
        else if(over)
            PA.fill(155,55,100);
        else
            PA.fill(50,50,50);
        PA.stroke(T.WindowBorder);
        PA.rect(Pos.x,Pos.y,Pos.x+Scale.x,Pos.y+Scale.y);
        PA.fill(200,200,200);

        PA.textSize(Scale.y);
        PA.text(Value,Pos.x,Pos.y+Scale.y/1.25f);

        over = false;
    }
}
