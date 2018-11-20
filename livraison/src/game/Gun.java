package game;

public abstract class Gun extends Weapon {

  public Gun(String type, int damage, int range, Player owner) {
    super(type, damage, range, owner);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public int getRange() {
    return this.range;
  }
}
