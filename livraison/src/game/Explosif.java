package game;

public abstract class Explosif extends Weapon {

  public Explosif(int damage) {
    super(damage, 1);
  }

  public int getDamage() {
    return this.damage;
  }
}
