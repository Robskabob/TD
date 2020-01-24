package TD.Objects.Data;

import TD.Objects.Block;

public class Map
{
    public int Width;
    public int Height;
    private int[][] data;

    public Block[] BlockMap;

    public Map(int width, int height, Block[] blockMap) {
        Width = width;
        Height = height;
        BlockMap = blockMap;
        data = new int[Width][Height];
    }

    public void Default() {
        Width = 100;
        Height = 100;
        data = new int[Width][Height];
        for(int i = 0; i < Width; i++)
        {
            for(int j = 0; j < Width; j++)
            {
                data[i][j] = 2;
            }
        }
    }

    public int Get(int X, int Y) {
        return X >= 0 && X <= Width - 1 && Y >= 0 && Y <= Height - 1 ? data[X][Y] : 0;
    }
    public Block GetBlock(int X, int Y) {
    return BlockMap[X >= 0 && X <= Width - 1 && Y >= 0 && Y <= Height - 1 ? data[X][Y] : 0];
}

    public void Set(int X, int Y, int ID) {
        if (X >= 0 && X <= Width && Y >= 0 && Y <= Height) {
            data[X][Y] = ID;
        }
    }
}
