package game;

public abstract class DefensiveStuff implements Stuff {

  protected int durability;

  public DefensiveStuff(int durability) {
    this.durability = durability;
  }

  public int getDurability() {
    return this.durability;
  }
}
