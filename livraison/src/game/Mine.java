package game;

public class Mine extends Explosif {

  private int damage;
  private Player owner;

  public Mine(String type, int damage, int range, Player owner) {
    super(type, damage, range, owner);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public Tile toTile(Position position) {
    return new ExplosifPlate(position, true, this.range, this.damage, owner);
  }

}
