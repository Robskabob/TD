package TD.Objects;

public enum Resources {
    Stone(10.0F),
    Wood(4.0F),
    Ore(12.0F),
    coal(6.0F),
    Iron(20.0F),
    steel(50.0F);

    public final float Weight;

    Resources(float w) {
        Weight = w;
    }
}
