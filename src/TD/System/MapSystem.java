package TD.System;

import TD.Main.GameManager;
import TD.Objects.Block;

import java.util.ArrayList;

public class MapSystem extends System {
    public int Width;
    public int Height;
    public int[][] Map;

    public ArrayList<Block> BlockMap = new ArrayList<>();

    public MapSystem(GameManager gm) {
        super(gm);
    }

    public void setup() {
        Width = 100;
        Height = 100;
        Map = new int[Width][Height];
    }

    @Override
    public void draw() {

    }

    public int Get(int X, int Y) {
        return X >= 0 && X <= Width - 1 && Y >= 0 && Y <= Height - 1 ? Map[X][Y] : 0;
    }

    public void Set(int X, int Y, int ID) {
        if (X >= 0 && X <= Width && Y >= 0 && Y <= Height) {
            Map[X][Y] = ID;
        }

    }
}