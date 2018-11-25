
package game;

import java.util.*;
import space.*;
import observer.*;

public interface Game extends ListenableModel {

  public Tile getTileAt(Position position);

  public void restart();

  public boolean canMove(Position position);

  public void setTile(Tile tile);

  public void activate(Tile tile);

  public void switchPlayer();

  public Tile[][] getGrid();

  public Player getNextPlayer();

  public List<Player> getListPlayers();

  public int getWidth();

  public int getHeight();

  public List<Tile> getTileInDirection(Position position, Direction direction, int range);

  public void skipTurn();

  public boolean isInIndex(Position position);

  public Map<Equipement, Integer> getEquipementOfPlayer();
}
