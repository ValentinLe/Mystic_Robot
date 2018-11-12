
package game;

public class ProxyGame implements Game {

  private Game board;
  private Player player;

  public ProxyGame(Game board, Player player) {
    this.board = board;
    this.player = player;
  }

  @Override
  public Tile getTileAt(Position position) {
    Tile tile = this.board.getTileAt(position);
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
  public boolean canMove(Position position) {
    return this.board.canMove(position);
  }

  @Override
  public void setTile(Tile tile) {
    this.board.setTile(tile);
  }

  @Override
  public void activate(Tile tile) {
    this.board.activate(tile);
  }

  @Override
  public void switchPlayer() {
    this.board.switchPlayer();
  }

  @Override
  public Tile[][] getGrid() {
    return this.board.getGrid();
  }

  @Override
  public Player getNextPlayer() {
    return this.board.getNextPlayer();
  }
  
  @Override
  public int getWidth() {
    return this.board.getWidth();
  }
  
  @Override
  public int getHeight() {
    return this.board.getHeight();
  }
  
  @Override
  public String toString() {
    String res = "  ";
    int width = this.board.getWidth();
    int height = this.board.getHeight();
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
