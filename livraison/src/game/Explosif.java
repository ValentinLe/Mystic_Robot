package game;

public abstract class Explosif extends Weapon {

  public Explosif(int damage) {
    super(damage);
  }

  public int getDamage() {
    return this.damage;
  }
}
