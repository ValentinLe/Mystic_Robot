
package game;

import java.util.List;

/**
 * Interface de generation de grille
 * 
 */
public interface GridGenerator {
  
  /**
   * Permet de generer une grille
   * @param width la largeur de la grille voulu
   * @param height la hauteur de la grille voulu
   * @param listPlayers la liste de joueurs a mettre dans la grille
   * @return la grille creee
   */
  public Tile[][] generateGrid(int width, int height, List<Player> listPlayers);
}
