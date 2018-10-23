package game;

public class Shield extends DefensiveStuff {

  public Shield(){
    super(1);
  }

  @Override
  public int getDurability() {
    return this.durability;
  }
}
