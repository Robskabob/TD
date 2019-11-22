package TD.System;

import TD.Main.GameManager;
import TD.Objects.Interfaces.Pather;
import TD.Objects.Unit;
import TD.Util.Vec2;
import javafx.util.Pair;

import java.util.*;

public class PathSystem extends System {
    public PathSystem(GameManager gm) {
        super(gm);
    }

    public List<Node> Nodes = new ArrayList<Node>();

    public boolean draw = true;

    public class Node// implements Comparable<Node>
    {
        public Node(int x, int y)
        {
            Pos = new Vec2(x,y);
        }
        public Vec2 Pos;
        public ArrayList<Node> Connections = new ArrayList<>();

        public float number;
        public float number2;

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
        public ArrayList<Node> path;

        public Path (ArrayList<Node> nodes)
        {
            path = nodes;
        }

        public boolean isEnd(Pather p)
        {
            return p.GetPathIndex() > path.size()-1;
        }

        public Vec2 getNext(Vec2 c,Pather p)
        {
            if(p.GetPathIndex()>path.size()-1)
            {
                //index = 0;
                return c;
            }
            if(c.Dist(path.get(p.GetPathIndex()).Pos)<2)
            {
                p.SetPathIndex(p.GetPathIndex()+1);
                if(p.GetPathIndex()>path.size()-1)
                {
                    return c;
                }
            }
            return path.get(p.GetPathIndex()).Pos;
        }
    }

    public Path GetPath(Node Start,Node End)
    {
        HashMap<Node,Float> pathed = new HashMap<>();
        //ArrayList<Node> Queue = new ArrayList<Node>();
        PriorityQueue<Pair<Pair<Node,Float>,Float>> Queue = new PriorityQueue<>((entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue()));
        Node L = Start;
        Node N = Start;

        pathed.put(Start, 0f);
        while(N!=End)
        {
            for (int i = 0; i < N.Connections.size(); i++) {
                Node O = N.Connections.get(i);
                if (pathed.containsKey(O)) {
                    continue;
                }
                O.number2 = O.Pos.Dist(End.Pos)+pathed.get(N);
                Queue.add(new Pair<>(new Pair<>(O,pathed.get(N)),O.Pos.Dist(End.Pos)+pathed.get(N)));
            }
            if(N!=null) {
                L = N;
                Pair<Node,Float> p = Queue.poll().getKey();
                N = p.getKey();
                N.number = N.Pos.Dist(L.Pos) + p.getValue();//testing only
                pathed.put(N, N.Pos.Dist(L.Pos) + p.getValue());
            }
            else
            {
                if(Queue.isEmpty())
                {
                    break;
                }
                N = Queue.poll().getKey().getKey();
            }
        }

        if(N==null)
        {
            N=End;
        }

        ArrayList<Node> path = new ArrayList<Node>();
        while (N != Start)
        {
            float Min = Float.MAX_VALUE;
            Node m = null;
            if(N==null)
            {
                break;
            }
            for(int i = 0; i < N.Connections.size(); i++)
            {
                if(pathed.containsKey(N.Connections.get(i)))
                if(pathed.get(N.Connections.get(i)) < Min)
                {
                    Min = pathed.get(N.Connections.get(i));
                    m = N.Connections.get(i);
                }
            }
            path.add(N);
            if(m==null)
            {
                break;
            }
            N = m;
        }
        path.add(Start);
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
                    if(GameManager.GM.GetKey('g')&&N.Pos.sub(new Vec2(GameManager.GM.MX,GameManager.GM.MY)).sqMag()<1)
                    {
                        if(Sel!=null)
                        las = Sel;
                        Sel = N;
                        return;
                    }
                    if (N.Pos.sub(new Vec2(GameManager.GM.MX, GameManager.GM.MY)).sqMag() < 1) {
                        if (GM.keyCode == GameManager.SHIFT&&!las.Connections.contains(N)) {
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
                if(GameManager.GM.GetKey('n')) {
                    if(Sel!=null)
                    las = Sel;
                    Sel = new Node(GameManager.GM.MX, GameManager.GM.MY);
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
            if(GameManager.GM.GetKey('m')) {
                Sel.Pos.Set(GameManager.GM.MX, GameManager.GM.MY);
            }
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
        GameManager GM = GameManager.GM;
        if(GM.GetKey('u')&&GM.frameCount%5==0)
        {
            if(las!=null&&Sel!=null)
                GM.Entity.Entities.add(new Unit(GetPath(las,Sel)));
        }

        if(GM.GetKey('p')&&GM.frameCount%10==0)
        {
            draw = !draw;
        }

        if(draw)
        {
            EditPath();
            for(int i = 0; i < Nodes.size(); i++)
            {
                Node N = Nodes.get(i);
                if (Sel == N) {
                    GM.fill(100, 100, 200);
                } else if (las == N) {
                    GM.fill(100, 200, 100);
                } else {
                    GM.fill(150, 100, 100);
                }
                GM.ellipse((N.Pos.x - GM.P.Pos.x) * GM.Render.Zoom + GM.width / 2, (N.Pos.y - GM.P.Pos.y) * GM.Render.Zoom + GM.height / 2, GM.Render.Zoom / 3, GM.Render.Zoom / 3);
                for (int j = 0; j < N.Connections.size(); j++) {
                    GM.line((N.Pos.x - GM.P.Pos.x) * GM.Render.Zoom + GM.width / 2, (N.Pos.y - GM.P.Pos.y) * GM.Render.Zoom + GM.height / 2, (N.Connections.get(j).Pos.x - GM.P.Pos.x) * GM.Render.Zoom + GM.width / 2, (N.Connections.get(j).Pos.y - GM.P.Pos.y) * GM.Render.Zoom + GM.height / 2);
                }
                if (GM.GetKey('c')) {
                    N.number = 0;
                    N.number2 = 0;
                }
                GM.fill(0);
                GM.textSize(GM.Render.Zoom);
                GM.text(N.number, (N.Pos.x - GM.P.Pos.x) * GM.Render.Zoom + GM.width / 2, (N.Pos.y - GM.P.Pos.y) * GM.Render.Zoom + GM.height / 2);
                GM.text(N.number2, (N.Pos.x - GM.P.Pos.x) * GM.Render.Zoom + GM.width / 2, (N.Pos.y - GM.P.Pos.y + 1) * GM.Render.Zoom + GM.height / 2);
            }
        }
    }
}
