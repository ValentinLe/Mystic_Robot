package game;

import space.Position;
import observer.*;

public abstract class AbstractTile extends AbstractListenableModel implements Tile {

  protected Position position;
  protected boolean isObstacle;
  protected String type;

  public AbstractTile(String type, Position position, boolean isObstacle) {
    this.position = position;
    this.isObstacle = isObstacle;
    this.type = type;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @Override
  public boolean getIsObstacle() {
    return this.isObstacle;
  }

  @Override
  public void setIsObstacle(boolean isObstacle) {
    this.isObstacle = isObstacle;
  }

  @Override
  public String getType(){
    return this.type;
  }
}
