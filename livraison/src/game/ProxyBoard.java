
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
}
