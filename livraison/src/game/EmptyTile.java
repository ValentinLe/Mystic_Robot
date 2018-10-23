package game;

public class EmptyTile extends Terrain {

  public EmptyTile(Position position) {
    super(position, false);
  }

  @Override
  public String toString() {
    return "-";
  }
}
