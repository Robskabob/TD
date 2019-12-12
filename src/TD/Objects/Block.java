package TD.Objects;

import TD.Main.GameManager;
import TD.System.PathSystem;

public class Block
{
    public int Color;
    public int height;
    public PathSystem.Terrain T;

    public Block(GameManager gameManager, int r, int g, int b, int D, PathSystem.Terrain t)
    {
        T = t;
        Color = gameManager.color(r,g,b);
        height = D;
    }

    public Block(int C, int D, PathSystem.Terrain t)
    {
        T = t;
        Color = C;
        height = D;
    }
}
