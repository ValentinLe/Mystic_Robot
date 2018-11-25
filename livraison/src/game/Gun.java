package game;

public class Gun extends Weapon {

  /**
    * Constructeur de la classe
    * @param type , qui est de type String.
    * @param damage , qui est de type int.
    * @param owner , qui est de type Player.
    *
    */
  public Gun(String type, int damage, int range, Player owner) {
    super(type, damage, range, owner);
  }

  /**
    * Méthode permettant de retourner les dommages.
    * @return this.damage
    *
    *
    */
  @Override
  public int getDamage() {
    return this.damage;
  }

  /**
    * Méthode permettant de retourner la portée de l'arme.
    * @return this.range 
    *
    *
    */
  @Override
  public int getRange() {
    return this.range;
  }
}
