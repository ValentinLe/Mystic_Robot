package game;

public abstract class Terrain implements Tile {

  private Position pos;
  private boolean isObstacle;

  public Terrain(Position pos, boolean isObstacle) {
    this.pos = pos;
    this.isObstacle = isObstacle;
  }

  @Override
  public Position getPosition() {
    return this.pos;
  }

}
