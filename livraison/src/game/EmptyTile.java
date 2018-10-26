package game;

public class EmptyTile extends AbstractTile {

  public EmptyTile(Position position) {
    super(position, false);
  }

  @Override
  public String toString() {
    return "-";
  }
}
