package game;

import space.Position;

public class Mine extends Explosif {

  private Player owner;

  /**
    * Constructeur de la classe.
    * @param damage , qui est de type int .
    * @param range , qui est de type int.
    * @param counter , qui est à -1 pour dire qu'il n'y a pas de counter.
    * @param owner , qui est de type Player.
    *
    */
  public Mine(int damage, int range, Player owner) {
    super("Mine", damage, range, owner);
  }

  /**
   * Methode renvoyant une copie
   * @return Equipement copié
   */
  public Equipement getCopy() {
    return new Mine(this.damage, this.range, this.owner);
  }

  /**
    * Méthode qui transforme en une Tile.
    * @param position , de type Position
    * @return un nouveau ExplosifPlate.
    *
    */
  @Override
  public Tile toTile(Position position) {
    return new ExplosifPlate(this.type, position, true, this.range, this.damage, this.owner);
  }

}
