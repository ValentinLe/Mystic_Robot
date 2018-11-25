package game;

import space.Position;

public interface Tile {

  /**
    * Retourne la position de l'objet dans la grille
    *@return la position de l'objet
    */
  public Position getPosition();

  /**
    * Retourne si l'objet est un obstacle
    *@return si l'objet est un obstacle
    */
  public boolean getIsObstacle();

  /**
    * Modifie l'attribut isObstacle
    *@param isObstacle un booléen
    */
  public void setIsObstacle(boolean isObstacle);

  /**
    * Retourne la représentation de l'objet sous forme de String
    *@return la représentation de l'objet
    */
  public String getType();
}
