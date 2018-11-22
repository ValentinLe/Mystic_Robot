
package game;

import java.util.List;

public interface GridGenerator {
    
    public Tile[][] generateGrid(int width, int height, List<Player> listPlayers);
}
