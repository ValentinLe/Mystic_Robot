
package game;

public abstract class DefensiveEquipement implements Equipement {

  protected int durability;

  public DefensiveEquipement(int durability) {
    this.durability = durability;
  }

  public boolean use(Position position,Direction direction,Board board){
    return false;
  }

  public int getDurability() {
    return this.durability;
  }
}
