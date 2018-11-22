
package game;

import java.util.*;

public class GridGeneratorWithProbability implements GridGenerator {
    
  private final double probaWall;
  private final double probaEnergy;
  private final double probaShield; 
  private Random random;

  public GridGeneratorWithProbability(double probaWall, double probaEnergy, double probaShield) {
    this.probaWall = probaWall;
    this.probaEnergy = probaEnergy;
    this.probaShield = probaShield;
    this.random = new Random();
  }

  public boolean isProba(double probability) {
    double value = this.random.nextDouble();
    return probability > value;
  }
  
  public Tile generateTile(int x, int y) {
    Position position = new Position(x, y);
    if (this.isProba(this.probaWall)) {
      return new Wall(position);
    } else if (this.isProba(this.probaEnergy)) {
      return new EnergyPlate(position);
    } else if (this.isProba(this.probaShield)) {
      return new ShieldPlate(position);
    } else {
      return new EmptyTile(position);
    }
  }
  
  public void placePlayers(Tile[][] grid, int width, int height, List<Player> listPlayers) {
    boolean isPlace = false;
    for (Player player : listPlayers) {
      while (!isPlace) {
        int x = this.random.nextInt(width);
        int y = this.random.nextInt(height);
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

  @Override
  public Tile[][] generateGrid(int width, int height, List<Player> listPlayers) {
    Tile[][] grid = new Tile[height][width];
    for (int j = 0; j<height; j++) {
      for (int i = 0; i<width; i++) {
        grid[j][i] = this.generateTile(i, j);
      }
    }
    this.placePlayers(grid, width, height, listPlayers);
    return grid;
  }
    
}
