package TD.System;

import TD.Main.Engine;
import TD.Main.GameManager;
import TD.Main.GameMode;
import TD.Objects.Block;
import processing.core.PApplet;

import java.util.ArrayList;

//remove switch to Map Data

public class DepMapSystem extends GameSystem {
    public int Width;
    public int Height;
    public int[][] Map;

    public Block[] BlockMap = new Block[]{};

    public DepMapSystem(Engine e, GameMode g) {
        super(e, g);
    }

    public void setup() {
        Width = 100;
        Height = 100;
        Map = new int[Width][Height];
        for(int i = 0; i < Width; i++)
        {
            for(int j = 0; j < Width; j++)
            {
                Map[i][j] = 2;
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PApplet PA) {

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