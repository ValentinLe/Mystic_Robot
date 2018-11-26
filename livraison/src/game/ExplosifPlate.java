package game;

import space.Position;
import java.util.*;

public class ExplosifPlate extends Usable {

  private int damage;
  private Player owner;
  private int counter;
  private boolean isBomb;

  /**
    * Le constructeur principal de la classe
    *@param type le type d'explosif
    *@param position la position de l'objet dans la grille
    *@param isActivable si l'objet est activable ou non
    *@param range la portée de l'objet
    *@param damage les dégats de l'objet
    *@param counter le compteur avant explosion de l'objet
    *@param isBomb true si c'est une explosifPlate avec detonation
    *@param owner le joueur propriétaire de l'objet
    */
  public ExplosifPlate(String type, Position position, boolean isActivable, int range, int damage, int counter, boolean isBomb, Player owner) {
    super(type, position, isActivable, range);
    this.damage = damage;
    this.owner = owner;
    this.range = range;
    this.counter = counter;
    this.isBomb = isBomb;
  }

  /**
    * Un constructeur faisant appel au constructeur principal de la classe
    *@param type le type d'explosif
    *@param position la position de l'objet dans la grille
    *@param isActivable si l'objet est activable ou non
    *@param range la portée de l'objet
    *@param damage les dégats de l'objet
    *@param counter le conteur avant explosion de l'explosifPlate
    *@param owner le joueur propriétaire de l'objet
    */
  public ExplosifPlate(String type, Position position, boolean isActivable, int range, int damage, int counter, Player owner) {
    this(type, position, isActivable, range, damage, counter, true, owner);
  }

  /**
    * Un constructeur faisant appel au constructeur principal de la classe
    *@param type le type d'explosif
    *@param position la position de l'objet dans la grille
    *@param isActivable si l'objet est activable ou non
    *@param range la portée de l'objet
    *@param damage les dégats de l'objet
    *@param owner le joueur propriétaire de l'objet
    */
  public ExplosifPlate(String type, Position position, boolean isActivable, int range, int damage, Player owner) {
    this(type, position, isActivable, range, damage, -1, false, owner);
  }

  /**
   * Retire de la vie à tous les joueurs de la liste
   * @param players liste des joueurs
   */
  @Override
  public void action(RealGame board) {
    List<Player> players = board.getPlayersAround(this.position, this.range);
    for (Player player : players) {
      player.applyDamage(this.damage);
    }
  }

  /**
    * Méthode qui décrémente l'attribut counter de 1, si counter est supérieur ou égal à 0
    */
  public void updateCounter() {
    if (this.counter > 0) {
      this.counter -= 1;
    }
  }

  /**
    * Test si l'explosif plate est une bombe
    * @return true si le conteur est coherent (mis à -1 si ce n'est pas le cas)
    */
  public boolean isBomb() {
    return this.isBomb;
  }

  /**
    * Retourne si l'objet est activable
    *@return l'attribut isActivable
    */
  @Override
  public boolean isActivable() {
    return this.isActivable;
  }

  /**
    * Retourne les dégats de l'objet
    *@return l'attribut damage
    */
  public int getDamage() {
    return this.damage;
  }

  /**
    * Retourne le compteur de l'objet
    *@return l'attribut counter
    */
  public int getCounter() {
    return this.counter;
  }

  /**
    * Retourne le propriétaire de l'objet
    *@return l'attribut owner
    */
  public Player getPlayer() {
    return this.owner;
  }

  /**
    * Retourne une représentation de l'objet sous forme de String
    *@return la représentation de l'objet
    */
  @Override
  public String toString() {
    return "x";
  }
}
