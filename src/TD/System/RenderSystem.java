package TD.System;

import TD.Main.GameManager;
import TD.Objects.Block;
import TD.Objects.Entity;

public class RenderSystem extends System
{
    public RenderSystem(GameManager gm) {
        super(gm);
    }

    public float Zoom;
    //EntitySystem.Entity Target;
    public Entity Focus;

    public float FocusX()
    {
        return Focus.Pos.x - GM.width/Zoom/2;
    }

    public float FocusY()
    {
        return Focus.Pos.y - GM.height/Zoom/2;
    }

    public void setup()
    {
        Zoom = 20;
    }

    public void draw()
    {
        GM.strokeCap(4);
        GM.fill(50);
        GM.rect(0,0,GM.width,GM.height);
        GM.rectMode(1);
        GM.strokeWeight(Zoom/10);
        Blocks(FocusX(),FocusY());
        GM.strokeWeight(Zoom/10);
        Shading(FocusX(),FocusY());
        for(int i = 0; i < GameManager.GM.Entity.Size(); i++)
        {
            Entity E = GameManager.GM.Entity.Get(i);
            E.draw(GM,(E.Pos.x-GameManager.GM.P.Pos.x)*Zoom+GM.width/2,(E.Pos.y-GameManager.GM.P.Pos.y)*Zoom+GM.height/2,E.Dir,Zoom);
        }
    }

    private void Blocks(float X,float Y)
    {
        //GM.noStroke();
        for(int x = 0; x <GameManager.GM.Map.Width; x++)
        {
            for(int y = 0; y <GameManager.GM.Map.Height; y++)
            {
                int i = GameManager.GM.Map.Map[x][y];
                GM.fill(GameManager.GM.Map.BlockMap.get(i).Color);
                GM.stroke(GameManager.GM.Map.BlockMap.get(i).Color);
                GM.rect((x-X)*Zoom,(y-Y)*Zoom,(x-X+1)*Zoom,(y-Y+1)*Zoom);
            }
        }
    }

    private void ShadeLine(Block O, Block I, float x, float y, boolean X)
    {
        int Ic=I.Color;
        if(O.Depth==I.Depth)
        {
            GM.stroke(GameManager.GM.colorAverage(O.Color,Ic));
            GM.colorMode(1);
        }
        else if(O.Depth>I.Depth)
        {
            GM.colorMode(3);
            GM.stroke(GM.color(GM.hue(Ic), GM.saturation(Ic), GM.brightness(Ic)/(1+.3f*(O.Depth-I.Depth))));
            GM.colorMode(1);
        }
        else if(O.Depth<I.Depth)
        {
            GM.colorMode(3);
            GM.stroke(GM.color(GM.hue(Ic), GM.saturation(Ic)/(1+.3f*(O.Depth-I.Depth)), GM.brightness(Ic)*(1.1f+.1f*(I.Depth-O.Depth))));
            GM.colorMode(1);
        }
        if(X)
        {
            GM.line(x*Zoom,y*Zoom,(x+1)*Zoom,y*Zoom);
        }
        else
        {
            GM.line(x*Zoom,y*Zoom,x*Zoom,(y+1)*Zoom);
        }

    }

    private void Shading(float X,float Y)
    {
        GM.strokeWeight(Zoom/2);
        for(int x = 0; x <GameManager.GM.Map.Width; x++)
        {
            for(int y = 0; y <GameManager.GM.Map.Height; y++)
            {
                int i = GameManager.GM.Map.Map[x][y];
                GM.stroke(GameManager.GM.Map.BlockMap.get(i).Color);
                //if(i!=3)
                //{


                int up;
                int left;

                if(y-1<0)
                {
                    up=0;
                }
                else
                {
                    up=GameManager.GM.Map.Map[x][y-1];
                }
                if(x-1<0)
                {
                    left=0;
                }
                else
                {
                    left=GameManager.GM.Map.Map[x-1][y];
                }

                Block I = GameManager.GM.Map.BlockMap.get(i);

                if(up!=i)
                {
                    ShadeLine(GameManager.GM.Map.BlockMap.get(up),I,x-X,y-Y,true);
                }
                else
                {
                    //GM.stroke(210);
                    //GM.line((x-X)*Zoom,(y-Y)*Zoom,(x-X+1)*Zoom,(y-Y)*Zoom);
                }
                if(left!=i)
                {
                    ShadeLine(GameManager.GM.Map.BlockMap.get(left),I,x-X,y-Y,false);
                }
                else
                {
                    //GM.stroke(210);
                    //GM.line((x-X)*Zoom,(y-Y)*Zoom,(x-X)*Zoom,(y-Y+1)*Zoom);
                }
            }
        }
    }

}