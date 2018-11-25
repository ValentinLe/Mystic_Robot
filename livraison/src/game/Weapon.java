package game;

import space.Position;
import space.Direction;

public class Weapon implements Equipement {

  protected int damage;
  protected int range;
  protected Player owner;
  protected String type;


/**
  * Constructeur de la classe
  * @param type , de type String.
  * @param damage , qui est un int .
  * @param range , qui est un int .
  * @param owner , qui est un joueur.
  */

  public Weapon(String type, int damage, int range, Player owner) {
    this.damage = damage;
    this.range = range;
    this.owner = owner;
    this.type = type;
  }


  /**
    * Fonction qui permet de retourner les dommages
    * @return this.damage
    *
    */
  public int getDamage() {
    return this.damage;
  }


  /**
    * Fonction qui permet de donner la portée.
    * @return this.range
    *
    */
  public int getRange() {
    return this.range;
  }

  /**
   * Utilisation de l'arme
   * @param position la position de départ
   * @param direction la direction dans laquelle l'arme est utilisé
   * @param board les fonctions contenue dans le board
   * @return true si l'arme à été utilisée avec succé
   */
  public boolean use(Position position,Direction direction,Game board){
    Player player = ((RealGame)board).getPlayerInDirection(position,direction,this.range);
    if (player!=null){
      player.applyDamage(this.damage);
      return true;
    }
    return false;
  }


  /**
    * Fonction permettant de retourner le type.
    * @return this.type
    *
    */
  public String getType(){
    return this.type;
  }

  /**
   * modifie le proprietaire de l'arme
   */
  @Override
  public void setOwner(Player owner) {
    this.owner = owner;
  }

  /**
   * récupère le proprietaire de l'arme
   * @return le Player proprietaire
   */
  @Override
  public Player getOwner() {
    return this.owner;
  }

  /**
   * créé une copie de l'objet arme
   * @return Equipement la copie de l'arme
   */
  @Override
  public Equipement getCopy() {
    return new Weapon(this.type,this.damage, this.range, this.owner);
  }

  /**
    * Fonction toString qui permet d'afficher textuellement le type.
    * @return this.type
    *
    */
  @Override
  public String toString(){
    return this.type;
  }
}
