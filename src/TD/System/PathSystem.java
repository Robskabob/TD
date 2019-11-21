package TD.System;

import TD.Main.GameManager;
import TD.Objects.Unit;
import TD.Util.Vec2;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

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
        //ArrayList<Node> Queue = new ArrayList<Node>();
        PriorityQueue<Pair<Node,Float>> Queue = new PriorityQueue<>();


        Node L = null;
        Node N = Start;
        while(N!=End)
        {
            pathed.put(N, N.Pos.Dist(L.Pos)+pathed.get(L));
            for (int i = 0; i < N.Connections.size(); i++) {
                Node O = N.Connections.get(i);
                if (pathed.containsKey(O)) {
                    continue;
                }
                Queue.add(new Pair<>(O,O.Pos.Dist(End.Pos)));
            }
            N = Queue.poll().getKey();
        }
        ArrayList<Node> path = new ArrayList<Node>();
        while (N != Start)
        {
            float Min = Float.MAX_VALUE;
            Node m = null;

            for(int i = 0; i < N.Connections.size(); i++)
            {
                if(pathed.get(N.Connections.get(i)) > Min)
                {
                    Min = pathed.get(N.Connections.get(i));
                    m = N.Connections.get(i);
                }
            }
            path.add(N);
            N = m;
        }

        return new Path(path);
    }

    private Node Sel;
    public Node las;

    public Node getSel() {
        return Sel;
    }

    public void setSel(Node sel) {
        las = Sel;
        Sel = sel;
    }

    public void EditPath()
    {
        if(Sel == null)
        {
            if(GM.mousePressed&&GM.mouseButton==GameManager.LEFT)
            {
                for(int i = 0; i < Nodes.size(); i++)
                {
                    Node N = Nodes.get(i);
                    if(GM.GetKey('g')&&N.Pos.sub(new Vec2(GM.MX,GM.MY)).sqMag()<1)
                    {
                        if(Sel!=null)
                        las = Sel;
                        Sel = N;
                        return;
                    }
                    if (N.Pos.sub(new Vec2(GM.MX, GM.MY)).sqMag() < 1) {
                        if (GM.keyCode == GameManager.SHIFT) {
                            N.Connections.add(las);
                            las.Connections.add(N);
                            return;
                        }
                        if (GM.keyCode == GameManager.CONTROL&&las.Connections.contains(N)) {
                            N.Connections.remove(las);
                            las.Connections.remove(N);
                            return;
                        }
                        if(Sel!=null)
                            las = Sel;
                            Sel = N;
                    }


                }
                if(GM.GetKey('n')) {
                    if(Sel!=null)
                    las = Sel;
                    Sel = new Node(GM.MX, GM.MY);
                    Nodes.add(Sel);
                    return;
                }
            }
        }
        else
        {
            //for(int i = 0; i < Nodes.size(); i++) {
            //    Node N = Nodes.get(i);
            //    if (N.Pos.sub(new Vec2(GM.MX, GM.MY)).sqMag() < 1) {
            //        if (GM.keyCode == GameManager.SHIFT) {
            //            N.Connections.add(Sel);
            //            Sel.Connections.add(N);
            //        }
            //        if (GM.keyCode == GameManager.CONTROL&&Sel.Connections.contains(N)) {
            //            N.Connections.remove(Sel);
            //            Sel.Connections.remove(N);
            //        }
            //    }
            //}
            Sel.Pos.Set(GM.MX,GM.MY);
            if(GM.mousePressed&&GM.mouseButton==GameManager.RIGHT) {
                if(Sel!=null)
                las = Sel;
                Sel=null;
            }
        }
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        EditPath();
        for(int i = 0; i < Nodes.size(); i++)
        {
            Node N = Nodes.get(i);
            if(Sel==N)
            {
                GM.fill(100,100,200);
            }
            else if(las==N)
            {
                GM.fill(100,200,100);
            }
            else
            {
                GM.fill(150,100,100);
            }
            if(GM.GetKey('u'))
            {
                GM.Entity.Entities.add(new Unit());
            }
            GM.ellipse((N.Pos.x-GM.P.Pos.x)*GM.Render.Zoom+GM.width/2,(N.Pos.y-GM.P.Pos.y)*GM.Render.Zoom+GM.height/2,GM.Render.Zoom/3,GM.Render.Zoom/3);
            for(int j = 0; j < N.Connections.size(); j++)
            {
                GM.line((N.Pos.x-GM.P.Pos.x)*GM.Render.Zoom+GM.width/2,(N.Pos.y-GM.P.Pos.y)*GM.Render.Zoom+GM.height/2,(N.Connections.get(j).Pos.x-GM.P.Pos.x)*GM.Render.Zoom+GM.width/2,(N.Connections.get(j).Pos.y-GM.P.Pos.y)*GM.Render.Zoom+GM.height/2);
            }
        }
    }
}
