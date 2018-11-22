
package game;

import java.util.*;

/**
 * Classe abstraite de la generation de grille avec une version du placement des
 * joueurs
 */
public abstract class AbstractGridGenerator implements GridGenerator {
  
  /**
   * Place les joueurs dans la grille sur une case qui n'est pas un obstacle
   * @param grid la grille
   * @param width la largeur de la grille
   * @param height la hauteur de la grille
   * @param listPlayers la liste des joueurs
   */
  public void placePlayers(Tile[][] grid, int width, int height, List<Player> listPlayers) {
    Random rand = new Random();
    boolean isPlace = false;
    for (Player player : listPlayers) {
      while (!isPlace) {
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        Tile tile = grid[y][x];
        if (!tile.getIsObstacle()) {
          Position position = new Position(x, y);
          grid[y][x] = player;
          player.setPosition(position);
          isPlace = true;
        }
      }
      isPlace = false;
    } 
  }
}
