package game;

public class Wall extends AbstractTile {

  public Wall(Position position) {
    super(position,true);
  }

  @Override
  public String toString() {
    return "#";
  }
}
