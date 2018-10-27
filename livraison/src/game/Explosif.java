package game;

public abstract class Explosif extends Weapon {

  public Explosif(int damage) {
    super(damage, 1);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public void applyDamage(Board b, Position explosifPosition, Position direction) {
    int xExplosif = explosifPosition.getX();
    int yExplosif = explosifPosition.getY();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        if (b.getGrid()[xExplosif+i][yExplosif+j] instanceof Player) {
          ((Player)b.getGrid()[xExplosif+i][yExplosif+j]).addEnergy(-this.damage);
        }
      }
    }
  }
}
