package game;

import space.Position;
import java.util.*;

public abstract class Usable extends AbstractTile {

  protected boolean isActivable;
  protected int range;

  /**
    * Le constructeur de la classe
    *@param type le type de l'objet
    *@param pos la position dans la grille
    *@param isActivable si l'objet est activable ou non
    *@param range la portée de l'objet
    */
  public Usable(String type, Position pos, boolean isActivable, int range) {
    super(type,pos, false);
    this.isActivable = isActivable;
    this.range = range;
  }

  /**
    * Retourne la valeur de l'attribut range
    *@return la valeur de l'attribut range
    */
  public int getRange() {
    return this.range;
  }

  /**
    * Méthode abstraite permettant d'effectuer une action
    *@param board une instance de RealGame
    */
  public abstract void action(RealGame board);

  /**
    * Retourne si l'objet est activable
    *@return si l'objet est activable
    */
  public boolean isActivable() {
    return this.isActivable;
  }
}
