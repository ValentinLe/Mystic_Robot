package game;

public class Wall extends Terrain {

  public Wall(Position position) {
    super(position,true);
  }

  @Override
  public String toString() {
    return "#";
  }
}
