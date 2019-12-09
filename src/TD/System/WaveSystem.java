package TD.System;

import TD.Main.GameManager;
import TD.Objects.Unit;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class WaveSystem extends System {

    public int WaveCount;

    public List<Wave> Waves = new ArrayList<>();

    public class Wave
    {
        public int Time = 0;
        public List<Group> Groups = new ArrayList<>();
        public class Group
        {
            public Unit U;
            public int Count;
            public int Spacing;
            public int TimeOffset;
            public int Start;
            public int Finish;
        }

        public void Update()
        {
            GameManager GM = GameManager.GM;
            Time ++;
            for(int i = 0; i < Groups.size(); i++)
            {
                Group G = Groups.get(i);
                if(Time > G.TimeOffset)
                {
                    if((Time - G.TimeOffset)%G.Spacing==0)
                    {
                        GM.Entity.Add(new Unit(G.U,G.U.path));
                        G.Count--;
                    }
                }
            }
        }
    }

    public WaveSystem(PApplet gm) {
        super(gm);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {

    }
}
