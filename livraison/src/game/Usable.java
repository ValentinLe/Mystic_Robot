package game;

public abstract class Usable extends AbstractTile {

  protected boolean isActivable;

  public Usable(Position pos, boolean isActivable) {
    super(pos, false);
    this.isActivable = isActivable;
  }

  public abstract void action();

  public abstract boolean isActivable();
}
