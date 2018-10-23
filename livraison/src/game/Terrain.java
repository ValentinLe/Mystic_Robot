package game;

public abstract class Terrain extends AbstractTile {

  public Terrain(Position position, boolean isObstacle) {
    super(position, isObstacle);
  }
}
