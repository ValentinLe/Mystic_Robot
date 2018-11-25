package game;

import space.Position;

public class Wall extends AbstractTile {


  /**
    *  Autre constructeur de la classe
    * @param type , qui est une String représentant le type.
    * @param position, qui est de type Position.
    */
  public Wall(String type,Position position) {
    super(type,position,true);
  }

  /**
    * Constructeur de la classe Wall
    * @param position , qui est de type Position
    *
    */
  public Wall(Position position) {
    this("#", position);
  }


  /**
    * Redéfinition de la méthode toString pour retourner le type.
    * @return this.type 
    *
    */
  @Override
  public String toString() {
    return this.type;
  }
}
