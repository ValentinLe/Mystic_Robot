package game;

import space.Position;

public class EmptyTile extends AbstractTile {

  /**
    *
    * Constructeur de la classe
    * @param position , de type posiion
    *
    */
  public EmptyTile(Position position) {
    super("Empty", position, false);
  }

  /**
    * Méthode permettant d'afficher les Tile.
    * @return une chaine de caractère.
    */
  @Override
  public String toString() {
    return "-";
  }
}
