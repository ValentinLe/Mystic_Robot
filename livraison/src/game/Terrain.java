package game;

public abstract class Terrain implements Tile {

  private Position pos;
  private boolean isObstacle;

  @Override
  public abstract Position getPosition() {
    return this.pos;
  }

}
