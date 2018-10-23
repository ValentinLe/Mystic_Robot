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

  public void initPlayer(Player player) {
    if (player.canMove(this, player.getPosition())) {
      setTile(player);
      //this.grid[player.getPosition().getX()][player.getPosition().getY()] = player;
    } else {
      throw new RuntimeException("Cette case n'est pas vide");
    }
  }

  // verifie si une case à la position pos de la grille est un obstacle
  public Boolean isObstacleTile(Position pos){
    return this.grid[pos.getX()][pos.getY()].getIsObstacle();
  }

  // verifie si une case à la position pos de la grille est un player
  public Boolean isPlayerOnPos(Position pos){
    for(Player p : this.players){
      if (p.getPosition().getX()==pos.getX() && p.getPosition().getY()==pos.getY()){
        return true;
      }
    }
    return false;
  }

  public Tile getTile(Position pos){
    return this.grid[pos.getX()][pos.getY()];
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
