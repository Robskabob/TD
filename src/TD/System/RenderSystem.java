package TD.System;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Main.GameMode;
import TD.Objects.Block;
import TD.Objects.Entity;
import TD.Util.Vec2;
import processing.core.PApplet;

public class RenderSystem extends GameSystem
{

    public float Zoom;
    //EntitySystem.Entity Target;
    public Entity Focus;

    public RenderSystem(Engine e, GameMode g) {
        super(e, g);
    }

    public Vec2 Focus()
    {
        return Focus.Pos;
    }

    public float FocusX()
    {
        return Focus.Pos.x - E.ScreenWidth()/Zoom/2;
    }

    public float FocusY()
    {
        return Focus.Pos.y - E.ScreenHeight()/Zoom/2;
    }

    public void setup()
    {
        Zoom = 20;
    }

    public void draw(PApplet PA)
    {
        PA.strokeCap(4);
        PA.fill(50);
        PA.rect(0,0, PA.width, PA.height);
        PA.rectMode(1);
        PA.strokeWeight(0);
        Blocks(PA,FocusX(),FocusY());
        PA.strokeWeight(Zoom/4);
        Shading(PA,FocusX(),FocusY());
        for(int i = 0; i < E.EntitySys.Size(); i++)
        {
            Entity En = E.EntitySys.Get(i);
            En.draw(PA,(En.Pos.x-E.RenderSys.Focus().x)*Zoom+ PA.width/2,(En.Pos.y-E.RenderSys.Focus().y)*Zoom+ PA.height/2,En.Dir,Zoom);
        }
    }

    @Override
    public void update() {

    }

    private void Blocks(PApplet PA,float X,float Y)
    {
        //GM.noStroke();
        for(int x = 0; x <E.M.Width; x++)
        {
            for(int y = 0; y <E.M.Height; y++)
            {
                int i = E.GetIDAt(x,y);
                PA.fill(E.M.BlockMap[i].Color);
                PA.stroke(E.M.BlockMap[i].Color);
                PA.rect((x-X)*Zoom,(y-Y)*Zoom,(x-X+1)*Zoom,(y-Y+1)*Zoom);
            }
        }
    }

    private void ShadeLine(PApplet PA,Block O, Block I, float x, float y, boolean X) {
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

    private void Shading(PApplet PA,float X,float Y)
    {
        X-=.13f;
        Y-=.13f;
        for(int x = 0; x <E.M.Width; x++) {
            for(int y = 0; y <E.M.Height; y++) {
                int i = E.GetIDAt(x,y);
                PA.stroke(E.M.BlockMap[i].Color);

                int up;
                int left;

                if(y-1<0) {
                    up=0;
                } else {
                    up=E.GetIDAt(x,y-1);
                }
                if(x-1<0) {
                    left=0;
                } else {
                    left=E.GetIDAt(x-1,y);
                }

                Block I = E.M.BlockMap[i];

                if(up!=i) {
                    ShadeLine(PA,E.M.BlockMap[up],I,x-X,y-Y,true);
                } else {
                    //GM.stroke(210);
                    //GM.line((x-X)*Zoom,(y-Y)*Zoom,(x-X+1)*Zoom,(y-Y)*Zoom);
                }
                if(left!=i) {
                    ShadeLine(PA,E.M.BlockMap[left],I,x-X,y-Y,false);
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