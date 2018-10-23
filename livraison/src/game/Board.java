package game;

import java.util.*;

public class Board {
  private Tile[][] grid;
  private ArrayList<Player> players = new ArrayList<>();;

  public Board(int width, int height){
    this.grid = generateGrid(width,height);
  }

  public Board(){
    this.grid = generateGrid(0,0);
  }

  public Tile[][] generateGrid(int width, int height) {
    Tile[][] grid = new Tile[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (i == 0 && j == 0) {
          Map<Stuff,Integer> stuff = new HashMap<Stuff,Integer>();
          Stuff  st = new Winchester();
          stuff.put(st,10);
          Player p1 = new Player(new Position(0,0), 100, false, stuff);
          this.players.add(p1);
          continue;
        }
        Position pos = new Position(i, j);
        grid[i][j] = new EmptyTile(pos);
      }
    }
    return grid;
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
