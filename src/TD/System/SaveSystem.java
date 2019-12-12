package TD.System;

import TD.Main.GameManager;
import TD.Objects.Block;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class SaveSystem extends System {

    @Override
    public void setup() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PApplet PA) {

    }

    public int GetInt(byte[] buff, Integer index)
    {
        return buff[index++] | (buff[index++] << 8) | (buff[index++] << 16) | (buff[index++] << 24);
    }

    public byte GetByte(byte[] buff, Integer index)
    {
        return buff[index++];
    }

    public void ReadMap(GameManager GM,byte[] buff)
    {
        Integer step = 0;
        //4int BlockMap offset
        int BlockMapOffset = GetInt(buff,step);
        //4int Map offset
        int MapOffset = GetInt(buff,step);
        //4int Nodes offset
        int NodesOffset = GetInt(buff,step);
        //4int Units offset?
        int UnitsOffset = GetInt(buff,step);
        //4int Waves offset
        int WavesOffset = GetInt(buff,step);

        int BlockMapLength = GetInt(buff,step);
        ArrayList<Block> BlockMap = new ArrayList<>(BlockMapLength);
        for(int i = 0; i < BlockMapLength; i++) {
            Block B = new Block(GetInt(buff, step),GetByte(buff,step), PathSystem.Terrain.values()[GetByte(buff,step)]);
        }
       // GM.Map.BlockMap = BlockMap;

        int Length = GetInt(buff,step);
        int Width = GetInt(buff,step);
        for(int i = 0; i < Length; i++)
        {
            for(int j = 0; j < Width; j++)
            {

            }
        }
        //1byte map

        //4int Length Nodes
        int NodeLength = GetInt(buff,step);
        for(int i = 0; i < NodeLength; i++) {
            int X = GetInt(buff, step);
            int Y = GetInt(buff, step);
            int ConnectionLength = GetByte(buff,step);
            for (int j = 0; j < ConnectionLength; j++) {
                int NodeId = GetInt(buff, step);
            }
        }
        //4int Units?

        //4int Wave Length
    }

    public byte[] SaveMap(GameManager GM)
    {
        //int byte
        ////4int BlockMap offset
        //int BlockMapOffset = bff2[18] | (bff2[19] << 8) | (bff2[20] << 16) | (bff2[21] << 24);
        ////4int Map offset
        //int MapOffset = bff2[18] | (bff2[19] << 8) | (bff2[20] << 16) | (bff2[21] << 24);
        ////4int Nodes offset
        //int NodesOffset = bff2[18] | (bff2[19] << 8) | (bff2[20] << 16) | (bff2[21] << 24);
        ////4int Units offset?
        //int UnitsOffset = bff2[18] | (bff2[19] << 8) | (bff2[20] << 16) | (bff2[21] << 24);
        ////4int Waves offset
        //int WavesOffset = bff2[18] | (bff2[19] << 8) | (bff2[20] << 16) | (bff2[21] << 24);

        //4int Length BlockMap
            //4int color
            //1byte Height
            //1byte Terrain

        //4int Length Map
        //4int Width
            //1byte map

        //4int Length Nodes
            //4Float X
            //4Float Y
            //1byte Connection Length
                //14int Node id

        //4int Units?

        //4int Wave Length


        return null;
    }

    public static void SaveFile(String Name,String Path,byte[] data)
    {

    }

    public static void SaveMap(GameManager GM,String Name)
    {
        /*
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

        json.setInt("Height",GM.Map.Map.length);
        json.setInt("Width",GM.Map.Map[0].length);
        json.setJSONArray("BlockMap",blockMap);
        JSONArray map = new JSONArray();
        for(int i = 0; i < GM.Map.Map.length; i++) {
            for(int j = 0; j < GM.Map.Map[0].length; j++) {
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
        */
    }
}
