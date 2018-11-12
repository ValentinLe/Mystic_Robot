
package game;

public interface Game {

  public Tile getTileAt(Position position);

  public boolean canMove(Position position);

  public void setTile(Tile tile);

  public void activate(Tile tile);

  public void switchPlayer();

  public Tile[][] getGrid();

  public Player getNextPlayer();
  
  public int getWidth();

  public int getHeight();
}
