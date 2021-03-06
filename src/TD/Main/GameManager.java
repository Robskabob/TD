package TD.Main;

import TD.System.*;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class GameManager extends PApplet
{
    public static GameManager GM;
    public static Engine E;
    //Systems
    //public MapSystem Map = new MapSystem(this);
    //public RenderSystem Render = new RenderSystem(this);
    //public EntitySystem Entity = new EntitySystem(this);
    //public PathSystem Pather = new PathSystem(this);
    public InPutSystem InPut = new InPutSystem(this);
    public UISystem UI = new UISystem(this);
    //End Systems


    //Methods

    //End
    //{new Block(0,10,100,0),new Block(0,30,110,0),new Block(10,110,20,1)};

    public static void main(String[] args) {
        PApplet.main("TD.Main.GameManager");
    }

    public int colorAverage(int A,int B)
    {
        colorMode(1);
        return color(
                ((A >> 16 & 0xFF)+(B >> 16 & 0xFF))/2,
                ((A >> 8 & 0xFF)+(B >> 8 & 0xFF))/2,
                ((A & 0xFF)+(B & 0xFF))/2
        );
    }

    public void settings(){
        GM=this;
        fullScreen();
    }

    public void setup(){
        E = new Engine(this);
        //P = new Player(50,50,.4f,1,5);
        //Map.BlockMap = new Block[]{
        //    new Block(this, 0, 10, 100, 0, PathSystem.Terrain.Water),
        //    new Block(this, 0, 30, 110, 0, PathSystem.Terrain.Water),
        //    new Block(this, 10, 110, 20, 1, PathSystem.Terrain.Land),
        //    new Block(this, 80, 110, 60, 1, PathSystem.Terrain.Land),
        //    new Block(this, 120, 120, 120, 2, PathSystem.Terrain.Land),
        //    new Block(this, 211, 211, 211, 2, PathSystem.Terrain.Land),
        //    new Block(this, 120, 120, 120, 4, PathSystem.Terrain.Land),
        //    new Block(this, 222, 222, 222, 5, PathSystem.Terrain.Land),
        //};
        frameRate(60);
        //BlockMap = new Block[]{new Block(0,10,100,0),new Block(0,30,110,0),new Block(10,110,20,1),new Block(80,110,60,1),new Block(120,120,120,3),new Block(200,200,225,5),new Block(25,25,200,5),new Block(200,25,25,5),new Block(25,200,25,5),new Block(200,200,25,5)};;
        //Map.setup();
        //Render.setup();
        //Entity.setup();
        UI.setup();
        InPut.setup();
        //Render.Focus = P;
        //Entity.Add(P);
        //Entity.Add(new Tower(30,20));
        //Entity.Add(new Tower(30,50));
        //Entity.Add(new Tower(30,80));
    }

    public void draw(){
        //Map.update();
        //Entity.update();
        //Render.update();
        //Pather.update();
        E.Update();
        E.Draw(this);
        UI.update();
        UI.draw(this);
        textSize(32);
        fill(0, 0, 0);
        //text("Zoom:"+GetZoom(), 10, 30);
        text("MouseX:"+MX, 10, 60);
        text("MouseY:"+MY, 10, 90);
        text("MouseYf:"+MYF, 10, 120);
        text("FrameRate:"+frameRate, 10, 150);

        if(mousePressed)
        {
            mouseDragged();
        }
    }

    public void mouseWheel(MouseEvent event)
    {
        float e = event.getCount();
        if(keyCode == CONTROL)
        {
            E.RenderSys.Zoom-=e;
            if(E.RenderSys.Zoom<1)
            {
                E.RenderSys.Zoom = 1;
            }
        }
        else
        {
            M+=e;
            if(M>E.M.BlockMap.length-1)
            {
                M=0;
            }
            else if(M<0)
            {
                M=E.M.BlockMap.length-1;
            }
        }
    }
    public void keyPressed()
    {
        InPut.KeyDown();
        //if(key=='i')
        //{
        //    Entity.RR++;
        //}
        //else if(key=='k')
        //{
        //    Entity.RR--;
        //}
    }
    public void keyReleased()
    {
        InPut.KeyUp();
    }
    public int M;//Current Block type
    public int MX;
    public int MY;
    public float MYF;
    public float MXF;

    public void mouseMoved()
    {
        //MXF=mouseX/Render.Zoom+Render.FocusX();
        //MYF=mouseY/Render.Zoom+Render.FocusY();
        //MX=(int)MXF;
        //MY=(int)MYF;
    }

    public void mouseDragged()
    {
        //MXF=mouseX/Render.Zoom+Render.FocusX();
        //MYF=mouseY/Render.Zoom+Render.FocusY();
        //MX=(int)(MXF);
        //MY=(int)(MYF);
        //if(!((GetZoom()+2) % 4 == 0))
        //    return;
        //if(MX<Map.Width&&MY<Map.Height&&MX>=0&&MY >=0)
        //{
        //    if(mouseButton == LEFT)
        //    {
        //        Map.Map[MX][MY]=M;
        //    }
        //    else if(mouseButton == CENTER)
        //    {
        //        M=Map.Map[MX][MY];
        //    }
        //    else if(mouseButton == RIGHT)
        //    {
        //        if(MX<Map.Width-1&&MY<Map.Height-1&&MX>=1&&MY>=1)
        //        {
        //            Map.Map[MX][MY]=M;
        //            Map.Map[MX-1][MY]=M;
        //            Map.Map[MX][MY-1]=M;
        //            Map.Map[MX+1][MY]=M;
        //            Map.Map[MX][MY+1]=M;
        //        }
        //    }
        //}
    }
}