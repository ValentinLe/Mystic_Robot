
package game;

import space.Position;
import space.Direction;

public interface Equipement {
    public boolean use(Position position,Direction direction,Game board);

    public String getType();
}
