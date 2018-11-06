
package game;

public class ProxyBoard implements Board {

  private Board board;
  private Player player;

  public ProxyBoard(Board board, Player player) {
    this.board = board;
    this.player = player;
  }

  @Override
  public Tile getTileAt(Position position) {
    // tile du proxy selon le joueur
    return null;
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
}
