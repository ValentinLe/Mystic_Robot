package game;

public class Mine extends Explosif {

  private int damage;

  public Mine() {
    super(4);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public String toString(){
    return "Mine";
  }
}
