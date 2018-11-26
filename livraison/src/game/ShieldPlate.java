
package game;

import space.Position;
import java.util.List;

public class ShieldPlate extends Usable {

  /**
    * Constructeur de la classe.
    * @param pos , qui est de type Position
    * @param isActivable , qui est de type boolean.
    * @param range , qui est de type int
    */
  public ShieldPlate(Position pos, boolean isActivable, int range) {
    super("ShieldPlate",pos, isActivable, range);
  }

  /**
    * Constructeur de la classe
    * @param pos , de type Position.
    *
    *
    */
  public ShieldPlate(Position pos) {
    this(pos, true, 0);
  }

  /**
   * met le bouclier sur le joueur present sur la shieldPlate
   * @param game le jeu dans laquelle se trouve la shieldPlate
   */
  @Override
  public void action(RealGame game) {
    ((Player)game.getTileAt(this.position)).setHasShield(true);
  }

/**
  * Méthode permettant de renvoyer une chaine de caractère.
  * @return la representation string d'une shieldPlate
  */
  @Override
  public String toString() {
    return "*";
  }
}
