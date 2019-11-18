package TD.Objects.Entites;

        import TD.Main.GameManager;
        import TD.Objects.Entity;
        import TD.Util.Vec2;

public class Projectile extends Entity
{
    int life = 500;

    public Projectile(Vec2 pos, float dir,float speed)
    {
        Pos = new Vec2(pos);
        Vel = new Vec2(GameManager.sin(dir)*speed,GameManager.cos(dir)*speed);
    }

    @Override
    public void Update(GameManager GM)
    {
        life--;
        if(life<0)
        {
            Dead = true;
        }
    }

    @Override
    public void draw(float x, float y, GameManager GM)
    {
        x=(Pos.x-x)*GM.Render.Zoom;
        y=(Pos.y-y)*GM.Render.Zoom;

        GM.ellipseMode(2);
        GM.fill(10);
        GM.stroke(30);
        GM.ellipse(GM.P.Pos.x*GM.Render.Zoom, GM.P.Pos.y*GM.Render.Zoom, GM.P.Radius*GM.Render.Zoom, GM.P.Radius*GM.Render.Zoom);
    }
}
