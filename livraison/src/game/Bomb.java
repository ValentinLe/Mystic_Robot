package game;

import space.Position;

public class Bomb extends Explosif {

  private int counter;


  /**
    * Constructeur de la classe.
    * @param damage , qui est de type int .
    * @param range , qui est de type int.
    * @param counter , qui est de type int.
    * @param owner , qui est de type Player.
    *
    */
  public Bomb(int damage, int range, int counter, Player owner) {
    super("Bomb", damage, range, owner);
    this.counter = counter;
  }


  /**
    * Méthode permettant de mettre un compteur.
    * @param counter , qui est de type int.
    *
    *
    */
  public void setCounter(int counter) {
    this.counter = counter;
  }

  /**
    * Méthode permettant de retourner le compteur
    * @return this.counter()
    *
    *
    */
  public int getCounter() {
    return this.counter;
  }

  /**
    * Méthode permettant de retourner une copie.
    * @return Bomb , qui est un nouveau Bomb avec dommage range counter et owner.
    *
    *
    */
  @Override
  public Equipement getCopy() {
    return new Bomb(damage, range, counter, owner);
  }

  /**
    * Méthode qui transforme en une Tile.
    * @param position , de type Position
    * @return un nouveau ExplosifPlate.
    *
    */
  @Override
  public Tile toTile(Position position) {
    return new ExplosifPlate(this.type,position, true, this.range, damage, this.counter, this.owner);
  }
}
