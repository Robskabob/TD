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
        return Focus.Pos.x - PA.width/Zoom/2;
    }

    public float FocusY()
    {
        return Focus.Pos.y - PA.height/Zoom/2;
    }

    public void setup()
    {
        Zoom = 20;
    }

    public void draw()
    {
        PA.strokeCap(4);
        PA.fill(50);
        PA.rect(0,0, PA.width, PA.height);
        PA.rectMode(1);
        PA.strokeWeight(0);
        Blocks(FocusX(),FocusY());
        PA.strokeWeight(Zoom/4);
        Shading(FocusX(),FocusY());
        for(int i = 0; i < GameManager.GM.Entity.Size(); i++)
        {
            Entity E = GameManager.GM.Entity.Get(i);
            E.draw(PA,(E.Pos.x-GameManager.GM.P.Pos.x)*Zoom+ PA.width/2,(E.Pos.y-GameManager.GM.P.Pos.y)*Zoom+ PA.height/2,E.Dir,Zoom);
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
                PA.fill(GameManager.GM.Map.BlockMap.get(i).Color);
                PA.stroke(GameManager.GM.Map.BlockMap.get(i).Color);
                PA.rect((x-X)*Zoom,(y-Y)*Zoom,(x-X+1)*Zoom,(y-Y+1)*Zoom);
            }
        }
    }

    private void ShadeLine(Block O, Block I, float x, float y, boolean X) {
        int Ic=I.Color;
        if(O.height ==I.height) {
            PA.stroke(GameManager.GM.colorAverage(O.Color,Ic));
            PA.colorMode(1);
        } else if(O.height >I.height) {
            PA.colorMode(3);
            PA.stroke(PA.color(PA.hue(Ic), PA.saturation(Ic), PA.brightness(Ic)/(1+.3f*(O.height -I.height))));
            PA.colorMode(1);
        } else if(O.height <I.height) {
            PA.colorMode(3);
            PA.stroke(PA.color(PA.hue(Ic), PA.saturation(Ic)/(1+.3f*(O.height -I.height)), PA.brightness(Ic)*(1.1f+.1f*(I.height -O.height))));
            PA.colorMode(1);
        }
        if(X) {
            PA.line(x*Zoom,y*Zoom,(x+1)*Zoom,y*Zoom);
        } else {
            PA.line(x*Zoom,y*Zoom,x*Zoom,(y+1)*Zoom);
        }
    }

    private void Shading(float X,float Y)
    {
        X-=.13f;
        Y-=.13f;
        for(int x = 0; x <GameManager.GM.Map.Width; x++) {
            for(int y = 0; y <GameManager.GM.Map.Height; y++) {
                int i = GameManager.GM.Map.Map[x][y];
                PA.stroke(GameManager.GM.Map.BlockMap.get(i).Color);

                int up;
                int left;

                if(y-1<0) {
                    up=0;
                } else {
                    up=GameManager.GM.Map.Map[x][y-1];
                }
                if(x-1<0) {
                    left=0;
                } else {
                    left=GameManager.GM.Map.Map[x-1][y];
                }

                Block I = GameManager.GM.Map.BlockMap.get(i);

                if(up!=i) {
                    ShadeLine(GameManager.GM.Map.BlockMap.get(up),I,x-X,y-Y,true);
                } else {
                    //GM.stroke(210);
                    //GM.line((x-X)*Zoom,(y-Y)*Zoom,(x-X+1)*Zoom,(y-Y)*Zoom);
                }
                if(left!=i) {
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