package TD.System;

import TD.Main.GameManager;
import TD.Objects.Block;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class SaveSystem extends System {
    public SaveSystem(PApplet pa) {
        super(pa);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {

    }

    public static void SaveMap(GameManager GM,String Name)
    {
        JSONObject json = new JSONObject();

        JSONArray blockMap = new JSONArray();
        for(int i = 0; i < GM.Map.BlockMap.size(); i++) {
            JSONObject block = new JSONObject();
            Block B = GM.Map.BlockMap.get(i);
            block.setInt("Color",B.Color);
            block.setInt("Height",B.height);
            block.setInt("Terrain",B.T.ordinal());
            blockMap.setJSONObject(i,block);
        }
        json.setJSONArray("BlockMap",blockMap);

        JSONArray map = new JSONArray();
        for(int i = 0; i < GM.Map.Map.length; i++) {
            for(int j = 0; j < GM.Map.Map[i].length; j++) {
                map.setInt(i+(j*GM.Map.Map.length),GM.Map.Map[i][j]);
            }
        }
        json.setJSONArray("Map",map);//make 2d

        JSONArray nodes = new JSONArray();
        for(int i = 0; i < GM.Pather.Nodes.size(); i++) {
            JSONObject node = new JSONObject();
            PathSystem.Node N = GM.Pather.Nodes.get(i);
            node.setFloat("X",N.Pos.x);
            node.setFloat("Y",N.Pos.y);
            //node.setInt("Height",N.Height);
            //node.setInt("Terrain",N.T.ordinal());//these can be derived from map
            JSONArray connections = new JSONArray();
            for(int j = 0; j < N.Connections.size(); j++) {
                connections.setInt(j,GM.Pather.Nodes.indexOf(N.Connections.get(j)));
            }
            node.setJSONArray("Connections",connections);
            nodes.setJSONObject(i,node);
        }

        json.setJSONArray("Nodes",nodes);

        json.setJSONArray("Waves",null);

        GM.saveJSONObject(json, "data/Maps/"+Name+".Map.json");
    }
}
