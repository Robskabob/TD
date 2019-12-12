package TD.Main;

import TD.Objects.Block;
import TD.Objects.Data.Map;
import TD.Objects.Entites.Player;
import TD.System.*;
import TD.Util.Vec2;

public class Engine
{
    GameManager GM;

    public Map M;
    public PathSystem PathSys;
    public RenderSystem RenderSys;
    public EntitySystem EntitySys;
    //public KeySystem KeySys;
    //public UISystem UISys;

    public void Setup(){}
    public void Update(){}

    public void LoadMap(){}
    public void Pause(){}
    public void Resume(){}
    public void ExitGame(){}
    public void JoinGame(){}

    public int ScreenWidth() {
        return GM.width;
    }

    public int ScreenHeight() {
        return GM.height;
    }

    public Vec2 GetMouse(){return new Vec2(GM.mouseX,GM.mouseY);}

    public Block GetBlockAt(int X, int Y)
    {
        return M.GetBlock(X,Y);
    }
    public Block GetBlockAt(Vec2 v)
    {
        return M.GetBlock((int)v.x,(int)v.y);
    }

    public void SetIDAt(int X,int Y,int ID)
    {
        M.Set(X, Y, ID);
    }

    public int GetIDAt(int X,int Y)
    {
        return M.Get(X, Y);
    }

    public boolean GetKey(char key)
    {
        return GM.Key.Get(key);
    }

    public float GetZoom()
    {
        if(RenderSys.Zoom>0)
        {
            return RenderSys.Zoom;
        }
        return 1;
    }
}
