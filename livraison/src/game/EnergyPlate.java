package game;

import space.Position;
import java.util.*;

public class EnergyPlate extends Usable {

  private int energyAmount;

  /**
    * Constructeur de la classe
    * @param pos , de type Position
    * @param isActivable , de type boolean
    * @param range , de type int.
    * @param energyAmount , de type int.
    */
  public EnergyPlate(Position pos, boolean isActivable, int range, int energyAmount) {
    super("EnergyPlate",pos, isActivable, range);
    this.energyAmount = energyAmount;
  }

  /**
    * Constructeur de la classe.
    * @param pos , qui est de type Position.
    *
    *
    */
  public EnergyPlate(Position pos) {
    this(pos, true, 0, 5);
  }

  /**
   * Ajoute de l'energy au joueur present sur la position
   * de l'EnergyPlate
   * @param game le jeu dans lequel se trouve l'energyPlate
   */
  @Override
  public void action(RealGame game) {
    ((Player)game.getTileAt(this.position)).addEnergy(this.energyAmount);
  }

  /**
    * Méthode permettant de voir si une tile est activable.
    * @return this.isActivable
    *
    */
  @Override
  public boolean isActivable() {
    return this.isActivable;
  }

  /**
    * Méthode d'affichage
    *@return une chaine de caractère.
    *
    *
    */
  @Override
  public String toString() {
    return "+";
  }
}
