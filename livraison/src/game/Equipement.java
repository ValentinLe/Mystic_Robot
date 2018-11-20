
package game;

public interface Equipement {
    public boolean use(Position position,Direction direction,Game board);

    public String getType();
}
