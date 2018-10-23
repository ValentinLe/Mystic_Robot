package game;

public class EmptyTile extends Terrain {

  public EmptyTile(Position pos) {
    super(pos, false);
  }

  @Override
  public String toString() {
    return "-";
  }
}
