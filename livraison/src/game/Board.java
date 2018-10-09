package game;

public class Board {
  private Tile[][] grid;

  public Board(int x, int y){
    this.grid = generateGrid(x,y);
  }

  public Board(){
    this.grid = generateGrid(0,0);
  }
}
