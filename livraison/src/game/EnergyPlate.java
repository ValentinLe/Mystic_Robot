package game;

import java.util.*;

public class EnergyPlate extends Usable {

  private int energyAmount;

  public EnergyPlate(Position pos, boolean isActivable, int range, int energyAmount) {
    super(pos, isActivable, range);
    this.energyAmount = energyAmount;
  }

  /**
   * Ajoute de l'energy aux joueurs de la liste
   * @param players liste des joueurs
   */
  @Override
  public void action(RealGame board) {
    ((Player)board.getTileAt(this.position)).addEnergy(this.energyAmount);
  }

  @Override
  public boolean isActivable() {
    return this.isActivable;
  }

  @Override
  public String toString() {
    return "+";
  }
}
