package game;

public class Shield extends DefensiveEquipement {

  public Shield(){
    super(1);
  }

  @Override
  public int getDurability() {
    return this.durability;
  }
}
