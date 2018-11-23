
package game;

import java.util.List;
import space.Position;
import observer.*;

public interface Game extends ListenableModel {

  public Tile getTileAt(Position position);

  public boolean canMove(Position position);

  public void setTile(Tile tile);

  public void activate(Tile tile);

  public void switchPlayer();

  public Tile[][] getGrid();

  public Player getNextPlayer();
  
  public List<Player> getListPlayers();
  
  public int getWidth();

  public int getHeight();
}
