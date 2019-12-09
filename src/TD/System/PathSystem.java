package TD.System;

import TD.Main.GameManager;
import TD.Objects.Block;
import TD.Objects.Entites.Units.Runner;
import TD.Objects.Entites.Units.Walker;
import TD.Objects.Entites.Units.WaterMan;
import TD.Objects.Interfaces.Pather;
import TD.Objects.Unit;
import TD.Util.Vec2;
import javafx.util.Pair;
import sun.net.httpserver.HttpServerImpl;

import java.util.*;

public class PathSystem extends System {
    public PathSystem(GameManager gm) {
        super(gm);
    }

    public List<Node> Nodes = new ArrayList<Node>();
    public List<Node> Starts = new ArrayList<Node>();
    public List<Node> Ends = new ArrayList<Node>();

    public boolean draw = true;

    public enum Terrain
    {
        Land,
        Water;

        public static boolean GetBit(byte mask,Terrain T) {
            int a = ((mask >> T.ordinal())&1);
            boolean b = a==1;
            return b;
        }

        public static byte SetBit(byte mask,Terrain T,boolean v) {
            return (byte)(v ? mask | (1 << T.ordinal()) : mask & ~(1 << T.ordinal()));
        }
    }

    public class Node// implements Comparable<Node>
    {
        public Node(float x, float y) {
            Pos = new Vec2(x,y);
            PlaceNode();
        }
        public Vec2 Pos;
        public ArrayList<Node> Connections = new ArrayList<>();

        public float number;//testing
        public float number2;//testing

        public boolean Accessible(Node N,Unit U) {
            return (U.Step >= Math.abs(N.Height - Height))&&Terrain.GetBit(U.Terrain,N.T);
        }

        public Terrain T = Terrain.Land;
        public int Height = 2;

        public void PlaceNode() {
            GameManager GM = GameManager.GM;
            Block B = GM.Map.BlockMap.get(GM.Map.Get((int)Pos.x,(int)Pos.y));
            Height = B.Depth;
            T = B.T;
        }

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

        public Vec2 getNext(Vec2 c, Pather p)
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

    public Path GetPath(Node Start,Node End,Unit U)
    {
        HashMap<Node,Float> pathed = new HashMap<>();
        //ArrayList<Node> Queue = new ArrayList<Node>();
        PriorityQueue<Pair<Pair<Node,Float>,Float>> Queue = new PriorityQueue<>((entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue()));
        Node L = Start;
        Node N = Start;

        pathed.put(Start, 0f);
        while(N!=End)//loop until reached end
        {
            if(N == null) {//check if node is null
                if(Queue.isEmpty())//if queue empty break if not grab next element
                {
                    break;
                }
                N = Queue.poll().getKey().getKey();
            }
            for (int i = 0; i < N.Connections.size(); i++) {
                Node O = N.Connections.get(i);
                if (pathed.containsKey(O) || !N.Accessible(O,U)) {
                    continue;
                }
                O.number2 = O.Pos.Dist(End.Pos)+pathed.get(N);
                Queue.add(new Pair<>(new Pair<>(O,pathed.get(N)),O.Pos.Dist(End.Pos)+pathed.get(N)));
            }
            L = N;
            Pair<Pair<Node,Float>,Float> p1 = Queue.poll();
            if(p1==null) {
                N = null;
                continue;
            }
            Pair<Node,Float> p = p1.getKey();
            N = p.getKey();
            N.number = N.Pos.Dist(L.Pos) + p.getValue();//testing only
            pathed.put(N, N.Pos.Dist(L.Pos) + p.getValue());
        }

        if(N==null)
        {
            N=End;
        }

        ArrayList<Node> path = new ArrayList<Node>();
        while (N != Start)//loop until Start reached
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

    public Node Sel;
    public Node las;

    public Node getSel() {
        return Sel;
    }

    public void setSel(Node sel) {
        las = Sel;
        Sel = sel;
    }

    public void CreateConnection(Node N1, Node N2)
    {
        if (!N2.Connections.contains(N1)) {
            N1.Connections.add(N2);
            N2.Connections.add(N1);
            return;
        }
    }
    public void CreateNode(Vec2 V)
    {
        Nodes.add(new Node(V.x, V.y));
    }
    public void CreateNode(float x, float y)
    {
        Nodes.add(new Node(x, y));
    }
    public void CreateNode()
    {
        CreateNode(GameManager.GM.MXF, GameManager.GM.MYF);
    }
    public void RemoveConnection(Node N1, Node N2)
    {
        if (N2.Connections.contains(N1)) {
            N1.Connections.remove(N2);
            N2.Connections.remove(N1);
            return;
        }
    }
    public void RemoveNode(Node N)
    {
        for(int i = 0; i < N.Connections.size(); i++)
        {
            N.Connections.get(i).Connections.remove(N);
        }
        Nodes.remove(N);
    }
    public void HidePath(){draw = !draw;}
    public void TestPath(){
        if(GM.frameCount%15==0)
        {
            if(las!=null&&Sel!=null&&Sel!=las) {
                double r = Math.random();
                if (r < .3) {
                    GameManager.GM.Entity.Add(new Runner(las, Sel));
                } else if(r<.6) {
                    GameManager.GM.Entity.Add(new Walker(las, Sel));
                }
                else {
                    GameManager.GM.Entity.Add(new WaterMan(las, Sel));
                }
            }
        }
    }

    public Boolean IsNearNode(Node N,Vec2 V){return true;}
    public Node GetNodeNearMouse(float MaxDist)
    {
        MaxDist *= MaxDist;
        Node N = null;

        for(int i = 0; i < Nodes.size(); i++)
        {
            Node I = Nodes.get(i);
            float f = I.Pos.sub(new Vec2(GameManager.GM.MX, GameManager.GM.MY)).sqMag();
            if (f < MaxDist)
            {
                MaxDist = f;
                N = I;
            }
        }

        return N;
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
                GM.Entity.Add(new Unit(las,Sel));
        }

        if(GM.GetKey('p')&&GM.frameCount%10==0)
        {
            draw = !draw;
        }

        if(draw)
        {
            //EditPath();
            for(int i = 0; i < Nodes.size(); i++)
            {
                Node N = Nodes.get(i);
                if (Sel == N) {
                    GM.fill(100, 100, 200);
                } else if (las == N) {
                    GM.fill(100, 200, 100);
                } else if (N.T == Terrain.Land) {
                    GM.fill(50, 250, 50);
                } else if (N.T == Terrain.Water) {
                    GM.fill(10, 10, 250);
                } else {
                    GM.fill(100, 100, 100);
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
                GM.fill(100,0,0);
                GM.text(N.number2, (N.Pos.x - GM.P.Pos.x) * GM.Render.Zoom + GM.width / 2, (N.Pos.y - GM.P.Pos.y + 1) * GM.Render.Zoom + GM.height / 2);
            }
        }
    }
}
