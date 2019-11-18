package TD.Objects;

import TD.Main.GameManager;

public class Block
{
    private GameManager gameManager;
    public int Color;
    public int Depth;

    public Block(GameManager gameManager, int r, int g, int b, int D)
    {
        this.gameManager = gameManager;
        Color = gameManager.color(r,g,b);
        Depth = D;
    }
}
