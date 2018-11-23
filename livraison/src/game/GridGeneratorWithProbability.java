
package game;

import space.Position;
import java.util.*;

/**
 * Genere une grille avec des probabilites que chaque element soit present
 * sur une case donnee
 */
public class GridGeneratorWithProbability extends AbstractGridGenerator {
    
  private final double probaWall;
  private final double probaEnergy;
  private final double probaShield;

  /**
   * Creee une instance de generateur de grille
   * @param probaWall probabilite qu'un mur soit place
   * @param probaEnergy probabilite qu'une case d'energie soit place
   * @param probaShield probabilite qu'une case de bouclier soit place
   */
  public GridGeneratorWithProbability(double probaWall, double probaEnergy, double probaShield) {
    this.probaWall = probaWall;
    this.probaEnergy = probaEnergy;
    this.probaShield = probaShield;
  }

  /**
   * Test si la probabilite est respectee en generant une valeur et test si
   * la probabilite est inferieur a la valeur
   * @param probability la probabilite a tester
   * @return true si la probabilite est respectee
   */
  private boolean isProba(double probability) {
    Random rand = new Random();
    double value = rand.nextDouble();
    return probability > value;
  }
  
  /**
   * Renvoi une case selon si les probabilites des cases et renvoi une case vide
   * si aucune des probabilites n'est respectees
   * @param x la coordonnee en abscisse
   * @param y la coordonne en ordonnee
   * @return la case creee
   */
  public Tile generateTile(int x, int y) {
    // position de la case
    Position position = new Position(x, y);
    if (this.isProba(this.probaWall)) {
      return new Wall(position);
    } else if (this.isProba(this.probaEnergy)) {
      return new EnergyPlate(position);
    } else if (this.isProba(this.probaShield)) {
      return new ShieldPlate(position);
    } else {
      // aucune des case avec la proba respectee
      return new EmptyTile(position);
    }
  }

  /**
   * Permet de generer une grille
   * @param width la largeur de la grille voulu
   * @param height la hauteur de la grille voulu
   * @param listPlayers la liste de joueurs a mettre dans la grille
   * @return la grille creee
   */
  @Override
  public Tile[][] generateGrid(int width, int height, List<Player> listPlayers) {
    Tile[][] grid = new Tile[height][width];
    for (int j = 0; j<height; j++) {
      for (int i = 0; i<width; i++) {
        grid[j][i] = this.generateTile(i, j);
      }
    }
    // placement des joueurs
    this.placePlayers(grid, width, height, listPlayers);
    return grid;
  }
    
}
