package game;

public class Bomb extends Explosif {

  private int counter;

  public Bomb(int damage, int counter) {
    super(damage);
    this.counter = counter;
  }

  public Bomb(int counter) {
    this(5, counter);
  }

  public void setCounter(int counter) {
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
