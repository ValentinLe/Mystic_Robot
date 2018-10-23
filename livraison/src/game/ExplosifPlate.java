package game;

public class ExplosifPlate extends Usable {

  private Explosif type;

  public ExplosifPlate(Position pos, boolean isActivable, Explosif type) {
    super(pos, isActivable);
    this.type = type;
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
