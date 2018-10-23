
package game;

public abstract class DefensiveEquipement implements Equipement {

  protected int durability;

  public DefensiveEquipement(int durability) {
    this.durability = durability;
  }

  public int getDurability() {
    return this.durability;
  }
}
