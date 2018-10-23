package game;

public class Weapon implements Stuff {

  protected int damage;

  public Weapon(int damage) {
    this.damage = damage;
  }

  public int getDamage() {
    return this.damage;
  }
}
