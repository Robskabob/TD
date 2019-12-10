package TD.System;

import TD.Main.GameManager;
import TD.Objects.Entites.Tower;
import TD.Objects.Entity;
import TD.Objects.Unit;
import TD.Util.Vec2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntitySystem extends System
{
    public EntitySystem(GameManager gm) {
        super(gm);
    }

    Random Rnd = new Random();

    private List<Entity> Entities = new ArrayList<>();
    public List<Unit> Units = new ArrayList<Unit>();
    public List<Tower> Towers = new ArrayList<Tower>();

    //Resource R[];

    public void setup()
    {
        //R = new Resource[] {
        //        new Resource(42,42,Resources.Wood),
        //        new Resource(41,42,Resources.Wood),
        //        new Resource(40,41,Resources.Wood),
        //        new Resource(42,41,Resources.Wood),
        //        new Resource(41,41,Resources.Wood),
        //        new Resource(40,41,Resources.Wood),
        //        new Resource(42,40,Resources.Wood),
        //        new Resource(41,40,Resources.Wood),
        //        new Resource(40,40,Resources.Wood),
        //        new Resource(45,45,Resources.Wood),
        //        new Resource(47,45,Resources.Stone),
        //        new Resource(48,45,Resources.Stone),
        //        new Resource(47,47,Resources.Stone),
        //        new Resource(100,100,Resources.Iron)
        //};

    }
    public int RR=0;
    @Override
    public void draw()
    {
        for(int i = 0; i < Size(); i++)
        {
            Entity E = Get(i);
            if(E.Dead)
            {
                Remove(E);
                i--;
                continue;
            }
            E.Update(PA);
            E.Physics(PA);
        }

        PA.strokeCap(2);
        Rnd.setSeed(RR);
        PA.textSize(10);
        //for(int i = 0; i < R.length; i++)
        //{
        //    R[i].draw(GM.Render.FocusX(), GM.Render.FocusY(),GM);
        //}
    }

    public Tower GetTowerNearPoint(Vec2 Pos, float MaxDist)
    {
        MaxDist *= MaxDist;
        Tower N = null;

        for(int i = 0; i < Towers.size(); i++)
        {
            Tower I = Towers.get(i);
            float f = I.Pos.sub(Pos).sqMag();
            if (f < MaxDist)
            {
                MaxDist = f;
                N = I;
            }
        }

        return N;
    }

    public Unit GetUnitNearPoint(Vec2 Pos, float MaxDist)
    {
        MaxDist *= MaxDist;
        Unit N = null;

        for(int i = 0; i < Units.size(); i++)
        {
            Unit I = Units.get(i);
            float f = I.Pos.sub(Pos).sqMag();
            if (f < MaxDist)
            {
                MaxDist = f;
                N = I;
            }
        }

        return N;
    }

    public void Add(Entity E)
    {
        Entities.add(E);
        if(E instanceof Tower)
            Towers.add((Tower) E);
        if(E instanceof Unit)
            Units.add((Unit) E);
    }

    private void Remove(Entity E)
    {
        Entities.remove(E);
        if(E instanceof Tower)
            Towers.remove(E);
        if(E instanceof Unit)
            Units.remove(E);
    }

    public Entity Get(int i)
    {
        return Entities.get(i);
    }
    public int Size()
    {
        return Entities.size();
    }
/*
    public class Building extends Entity
    {
        HashMap<Resources,Float> Contents;
        float CurrentWeight;
        float CurrentVolume;
        float MaxWeight;
        float MaxVolume;

        public void DrawContents(float x,float y)
        {
            //GM.text(X, (X-x)*GM.GetZoom(), (Y-y)*GM.GetZoom()-10);
            if(GM.MX>Pos.x-1&&GM.MX<Pos.x+1&&GM.MY>Pos.y-1&&GM.MY<Pos.y+1)
            {
                GM.fill(0, 0, 0);
                int i = 0;
                for (HashMap.Entry<Resources, Float> entry : Contents.entrySet())
                {
                    GM.text(entry.getKey() + ":" + entry.getValue(), (Pos.x-x)*GM.GetZoom(), (Pos.y-y)*GM.GetZoom()+(i*10));
                    i++;
                }
            }
            //Collection<Resources> Res=Contents.keySet();
            //Collection<Float> Val=Contents.values();
            //for(int i = 0; i < Contents.size();i++)
            //{
            //	GM.text(Res., X-x+(i*10), Y-y);
            //}
        }

        public void AddResourceW(Resources R,float W)
        {
            if(MaxWeight>CurrentWeight+W&&MaxVolume>CurrentVolume+W/R.Weight)
            {
                Contents.put(R, Contents.get(R)+W/R.Weight);
            }
        }
        public void AddResourceV(Resources R,float V)
        {
            if(MaxWeight>CurrentWeight+V*R.Weight&&MaxVolume>CurrentVolume+V)
            {
                Contents.put(R, Contents.get(R)+V);
            }
        }

        @Override
        public void Update() {

        }

        @Override
        public void draw(float X, float Y, GameManager GM) {

        }
    }

    public class Resource extends Building
    {
        Resource(float x,float y,Resources r)
        {
            Pos = new Vec2(x,y);
            R=r;
            Contents=new HashMap<Resources,Float>();
            Contents.put(r, 5f);
            Contents.put(Resources.Ore, 3f);
        }
        Resources R = Resources.Wood;

        @Override
        public void draw(float X, float Y, GameManager GM)
        {
            Random Ran = new Random();
            switch(R)
            {
                case Wood:
                    GM.strokeWeight(GM.GetZoom()/3);
                    GM.stroke(GM.color(0, 200,5));
                    Ran.setSeed(Rnd.nextInt());
                    GM.point((Pos.x-x+Ran.nextFloat())*GM.GetZoom(),(Pos.y-y+Ran.nextFloat())*GM.GetZoom());
                    GM.point((Pos.x-x+Ran.nextFloat())*GM.GetZoom(),(Pos.y-y+Ran.nextFloat())*GM.GetZoom());
                    GM.point((Pos.x-x+Ran.nextFloat())*GM.GetZoom(),(Pos.y-y+Ran.nextFloat())*GM.GetZoom());
                    break;
                case Stone:
                    GM.strokeWeight(GM.GetZoom()/1.5f);
                    GM.stroke(GM.color(0, 200,5));
                    Ran.setSeed(Rnd.nextInt());
                    GM.point((Pos.x-x+Ran.nextFloat())*GM.GetZoom(),(Pos.y-y+Ran.nextFloat())*GM.GetZoom());
                    break;
                case Iron:
                    break;
                case Ore:
                    break;
                case coal:
                    break;
                case steel:
                    break;
                default:
                    break;

            }
            DrawContents(x,y);
        }
    }

    public class Extractor extends Building
    {

    }

 */
}