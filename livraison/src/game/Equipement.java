
package game;

import space.Position;
import space.Direction;

public interface Equipement {
    public boolean use(Position position,Direction direction,Game board);

    public String getType();

    public Player getOwner();

    public void setOwner(Player player);

    public Equipement getCopy();
}
