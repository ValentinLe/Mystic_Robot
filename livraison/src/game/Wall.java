package game;

public class Wall extends AbstractTile {

  public Wall(String type,Position position) {
    super(type,position,true);
  }

  @Override
  public String toString() {
    return "#";
  }
}
