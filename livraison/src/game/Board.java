package game;

import java.util.*;

public class Board {
  private Tile[][] grid;
  private ArrayList<Player> players = new ArrayList<>();;

  public Board(int width, int height){
    generateGrid(width,height);
  }

  public void generateGrid(int width, int height) {
    this.grid = new Tile[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        this.setTile(new EmptyTile(new Position(i,j)));
      }
    }
  }

  public void setTile(Tile tile) {
    this.grid[tile.getPosition().getX()][tile.getPosition().getY()] = tile;
  }

  public void setPlayerPositions(Player player) {
    if (this.grid[player.getPosition().getX()][player.getPosition().getY()] instanceof EmptyTile) {
      setTile(player);
      //this.grid[player.getPosition().getX()][player.getPosition().getY()] = player;
    } else {
      throw new RuntimeException("Cette case n'est pas vide");
    }
  }

  @Override
  public String toString() {
    String res = "";
    for (int i = 0; i < this.grid[0].length; i++) {
      for (int j = 0; j < this.grid.length; j++) {
        res += " " + this.grid[i][j] + " ";
      }
      res += "\n";
    }
    return res;
  }
}
