package game;

public class Mine extends Explosif {

  public Mine(int damage) {
    super(damage);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }
}
