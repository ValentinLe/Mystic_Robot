package game;

import space.Position;
import java.util.*;

public class EnergyPlate extends Usable {

  private int energyAmount;

  public EnergyPlate(Position pos, boolean isActivable, int range, int energyAmount) {
    super("EnergyPlate",pos, isActivable, range);
    this.energyAmount = energyAmount;
  }
  
  public EnergyPlate(Position pos) {
    this(pos, true, 0, 5);
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
