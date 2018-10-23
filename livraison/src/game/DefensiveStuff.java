package game;

public class DefensiveStuff implements Stuff {

  protected int durability;

  public Weapon(int durability) {
    this.durability = durability;
  }

  public int getDurability() {
    return this.durability;
  }
}
