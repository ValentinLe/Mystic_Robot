package game;

import space.Position;

public class Mine extends Explosif {

  private Player owner;

  public Mine(int damage, int range, Player owner) {
    super("Mine", damage, range, owner);
  }

  @Override
  public Tile toTile(Position position) {
    return new ExplosifPlate(this.type,position, true, this.range, this.damage, owner);
  }

}
