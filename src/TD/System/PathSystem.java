package TD.System;

import TD.Main.GameManager;
import TD.Util.Vec2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PathSystem extends System {
    public PathSystem(GameManager gm) {
        super(gm);
    }

    public List<Node> Nodes = new ArrayList<Node>();

    public class Node// implements Comparable<Node>
    {
        public Node(int x, int y)
        {
            Pos = new Vec2(x,y);
        }
        public Vec2 Pos;
        public ArrayList<Node> Connections = new ArrayList<>();

        //@Override
        //public int compareTo(Node o) {
        //    if(id>b.id){
        //        return 1;
        //    }else if(id<b.id){
        //        return -1;
        //    }else{
        //        return 0;
        //    }
    }

    public class Path
    {
        public List<Node> path;
        public int index = 0;

        public Path (ArrayList<Node> nodes)
        {
            path = nodes;
        }

        public boolean isEnd()
        {
            return index == path.size();
        }

        public Vec2 getNext(Vec2 c)
        {
            if(c.sqMag()-path.get(index).Pos.sqMag()<1)
            {
                index++;
            }
            return path.get(index).Pos;
        }
    }

    public Path GetPath(Node Start,Node End)//remake
    {
        HashMap<Node,Float> pathed = new HashMap<>();
        ArrayList<Node> Queue = new ArrayList<Node>();

        Node L = null;
        Node N = Start;
        while(N!=End)
        {
            pathed.put(N, GameManager.sq(N.Pos.sub(L.Pos).sqMag())+pathed.get(L));
            for (int i = 0; i < N.Connections.size(); i++) {
                Node O = N.Connections.get(i);
                if (pathed.containsKey(O)) {
                    continue;
                }
                Queue.add(O);
            }
            N = Queue.get(0);
        }
        ArrayList<Node> path = new ArrayList<Node>();

        while (N!=Start)
        {

        }

        return new Path(path);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        for(int i = 0; i < Nodes.size(); i++)
        {
            Node N =Nodes.get(i);
            GM.ellipse(N.Pos.x,N.Pos.y,GM.Render.Zoom,GM.Render.Zoom);
            for(int j = 0; j < N.Connections.size(); j++)
            {
                GM.line(N.Pos.x,N.Pos.y,N.Connections.get(j).Pos.x,N.Connections.get(j).Pos.y);
            }
        }
    }
}
