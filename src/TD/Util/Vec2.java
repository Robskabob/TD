package TD.Util;

import TD.Main.GameManager;
import javafx.util.Pair;

public class Vec2
{
    public float x;
    public float y;

    public Vec2(float X, float Y)
    {
        x = X;
        y = Y;
    }

    public Vec2(Vec2 val)
    {
        x = val.x;
        y = val.y;
    }

    public Vec2 divide(float val)
    {
        return new Vec2(x/val,y/val);
    }

    public void Divide(float val)
    {
        x /= val;
        y /= val;
    }

    public Vec2 add(Vec2 val)
    {
        return new Vec2(x+val.x,y+val.y);
    }

    public void Add(Vec2 val)
    {
        x += val.x;
        y += val.y;
    }

    public float sqMag() {
        return x*x+y*y;
    }

    public Vec2 sub(Vec2 v) {
        return new Vec2(x-v.x,y-v.y);
    }

    public float Dist(Vec2 v)
    {
        return GameManager.sq(sub(v).sqMag());
    }

    public void Set(float X, float Y)
    {
        x = X;
        y = Y;
    }

    public boolean equals(Pair<Integer,Integer> I)
    {
        return x == I.getKey() && y == I.getValue();
    }
}
