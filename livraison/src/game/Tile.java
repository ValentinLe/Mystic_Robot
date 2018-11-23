package game;

import space.Position;

public interface Tile {

  public Position getPosition();

  public boolean getIsObstacle();

  public void setIsObstacle(boolean isObstacle);

  public String getType();
}
