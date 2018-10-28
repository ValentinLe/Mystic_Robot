package game;

public class ExplosifPlate extends Usable {

  private Explosif type;
  private Player owner;

  public ExplosifPlate(Position position, boolean isActivable, Explosif type, Player owner) {
    super(position, isActivable);
    this.type = type;
    this.owner = owner;
  }

  @Override
  public void action(Player player) {

  }

  @Override
  public boolean isActivable() {
    return this.isActivable;
  }

  public int getDamage() {
    return this.type.getDamage();
  }
}
