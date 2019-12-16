package TD.Main;

import TD.Objects.Block;
import TD.Objects.Data.Map;
import TD.Objects.Entites.Player;
import TD.System.*;
import TD.Util.Vec2;
import processing.core.PApplet;

public class Engine
{
    public GameManager GM;

    public GameMode Mode;

    public Map M;
    public PathSystem PathSys;
    public RenderSystem RenderSys;
    public EntitySystem EntitySys;
    //public KeySystem KeySys;
    //public UISystem UISys;

    private boolean Paused;

    public Engine(GameManager GM)
    {
        this.GM = GM;
        Setup();
        M = LoadMap();
        JoinGame(M, new EditorMode(this,M));
    }

    public void Setup()
    {
        PathSys = new PathSystem(this,Mode);
        RenderSys = new RenderSystem(this,Mode);
        EntitySys = new EntitySystem(this,Mode);
    }
    public void Update()
    {
        if(!Paused)
        {
            PathSys.update();
            RenderSys.update();
            EntitySys.update();
        }
    }
    public void Draw(PApplet PA)
    {
        PathSys.draw(PA);
        RenderSys.draw(PA);
        EntitySys.draw(PA);
    }
    public Map LoadMap()
    {
        return new Map(100,100,new Block[]{
            new Block(GM, 0, 10, 100, 0, PathSystem.Terrain.Water),
            new Block(GM, 0, 30, 110, 0, PathSystem.Terrain.Water),
            new Block(GM, 10, 110, 20, 1, PathSystem.Terrain.Land),
            new Block(GM, 80, 110, 60, 1, PathSystem.Terrain.Land),
            new Block(GM, 120, 120, 120, 2, PathSystem.Terrain.Land),
            new Block(GM, 211, 211, 211, 2, PathSystem.Terrain.Land),
            new Block(GM, 120, 120, 120, 4, PathSystem.Terrain.Land),
            new Block(GM, 222, 222, 222, 5, PathSystem.Terrain.Land),
        });
    }
    public void Pause(){
        Paused = true;
    }
    public void Resume(){
        Paused = false;
    }
    public void ExitGame(){}
    public void JoinGame(Map M, GameMode G){
        this.M = M;
        Mode = G;
    }

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
