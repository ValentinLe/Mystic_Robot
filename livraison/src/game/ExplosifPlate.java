package game;

public class ExplosifPlate extends Usable {

  private Explosif type;
  private Player owner;

  public ExplosifPlate(Position pos, boolean isActivable, Explosif type, Player owner) {
    super(pos, isActivable);
    this.type = type;
    this.owner = owner;
  }

  @Override
  public void action() {

  }

  @Override
  public boolean isActivable() {
    return this.isActivable;
  }

  public int getDamage() {
    return this.type.getDamage();
  }
}
