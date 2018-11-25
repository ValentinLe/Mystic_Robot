
package game;

import java.util.List;
import space.*;
import observer.*;

public class ProxyGame implements Game {

  private Game game;
  private Player player;

  public ProxyGame(Game game, Player player) {
    this.game = game;
    this.player = player;
  }

  @Override
  public void addModelListener(ModelListener l) {
    this.game.addModelListener(l);
  }

  @Override
  public void removeModelListener(ModelListener l) {
    this.game.addModelListener(l);
  }

  @Override
  public Tile getTileAt(Position position) {
    Tile tile = this.game.getTileAt(position);
    if (tile instanceof ExplosifPlate) {
      Player playerProprio = ((ExplosifPlate)tile).getPlayer();
      if (playerProprio == this.player) {
        return tile;
      } else {
        return new EmptyTile(position);
      }
    } else {
      return tile;
    }
  }

  @Override
  public boolean isInIndex(Position position){
    return this.game.isInIndex(position);
  }

  @Override
  public boolean canMove(Position position) {
    return this.game.canMove(position);
  }

  @Override
  public List<Tile> getTileInDirection(Position position, Direction direction, int range){
    return getTileInDirection(position,direction,range);
  }

  @Override
  public void skipTurn(){
    this.game.skipTurn();
  }

  @Override
  public void setTile(Tile tile) {
    this.game.setTile(tile);
  }

  @Override
  public void activate(Tile tile) {
    this.game.activate(tile);
  }

  @Override
  public void switchPlayer() {
    this.game.switchPlayer();
  }

  @Override
  public Tile[][] getGrid() {
    return this.game.getGrid();
  }

  @Override
  public Player getNextPlayer() {
    return this.game.getNextPlayer();
  }

  @Override
  public int getWidth() {
    return this.game.getWidth();
  }

  @Override
  public int getHeight() {
    return this.game.getHeight();
  }

  @Override
  public List<Player> getListPlayers() {
    return this.game.getListPlayers();
  }

  @Override
  public String toString() {
    String res = "  ";
    int width = this.game.getWidth();
    int height = this.game.getHeight();
    // numero superieurs
    for (int k = 0; k < width; k++) {
      res += " " + k + " ";
    }
    res += "\n";
    for (int j = 0; j < height; j++) {
      res += j + " "; // numeros coté
      for (int i = 0; i < width; i++) {
        Tile tile = this.getTileAt(new Position(i, j));
        res += " " + tile + " ";
      }
      res += "\n";
    }
    return res;
  }
}
