package game;

import space.Position;

public class Bomb extends Explosif {

  private int counter;
  private Player owner;

  public Bomb(int damage, int range, int counter, Player owner) {
    super("Bomb", damage, range, owner);
    this.counter = counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }

  public int getCounter() {
    return this.counter;
  }

  @Override
  public Tile toTile(Position position) {
    return new ExplosifPlate(this.type,position, true, this.range, damage, this.counter, this.owner);
  }
}
