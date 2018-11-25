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

/**
  * Méthode permettant de connaître la posiion
  * @return this.position
  *
  *
  */

  @Override
  public Position getPosition() {
    return this.position;
  }

  /**
    * Méthode permettant de mettre à jour la position.
    * @param posiion , qui est de type Position.
    *
    *
    */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
    *Méthode permettant de savoir si un objet est un obstacle.
    * @return this.isObstacle
    *
    *
    */
  @Override
  public boolean getIsObstacle() {
    return this.isObstacle;
  }

  /**
    * Méthode permettant de dire si un objet est un obstacle.
    * @param isObstacle , de type boolean.
    *
    *
    */
  @Override
  public void setIsObstacle(boolean isObstacle) {
    this.isObstacle = isObstacle;
  }

  /**
    * Méthode permettant de retourner le type.
    * @return this.type 
    *
    *
    */
  @Override
  public String getType(){
    return this.type;
  }
}
