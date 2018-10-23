package game;

public abstract class AbstractTile implements Tile {

  protected Position position;
  protected boolean isObstacle;

  public AbstractTile(Position position, boolean isObstacle) {
    this.position = position;
    this.isObstacle = isObstacle;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public boolean getIsObstacle() {
    return this.isObstacle;
  }

  @Override
  public void setIsObstacle(boolean isObstacle) {
    this.isObstacle = isObstacle;
  }
}
