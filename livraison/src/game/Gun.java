package game;

public abstract class Gun extends Weapon {

  public Gun(int damage) {
    super(damage);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
}
