package game;

public class EnergyPlate extends Usable {

  private int energyAmount;

  public EnergyPlate(Position pos, boolean isActivable, int energyAmount) {
    super(pos, isActivable);
    this.energyAmount = energyAmount;
  }

  @Override
  public void action(Player player) {
    player.addEnergy(this.energyAmount);
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
