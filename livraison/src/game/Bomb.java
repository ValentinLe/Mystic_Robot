package game;

public class Bomb extends Explosif {

  private int damage;
  private int counter;

  public Bomb(int damage, int counter) {
    super(damage);
    this.counter = counter;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  public int getCounter() {
    return this.counter;
  }
}