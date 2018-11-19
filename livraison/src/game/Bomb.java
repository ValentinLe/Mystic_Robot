package game;

public class Bomb extends Explosif {

  private int counter;
  private Player owner;

  public Bomb(int damage, int range, int counter, Player owner) {
    super(damage, range, owner);
    this.counter = counter;
  }

  public Bomb(int counter, int range, Player owner) {
    this(5, counter, range, owner);
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
  
  @Override
  public Tile toTile(Position position) {
    return  new ExplosifPlate(position, true, this.range, damage, this.owner);
  }

  @Override
  public String toString(){
    return "Bomb";
  }
}
