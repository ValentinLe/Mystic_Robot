package game;

import java.util.*;

public abstract class Usable extends AbstractTile {

  protected boolean isActivable;
  protected int range;

  public Usable(Position pos, boolean isActivable, int range) {
    super(pos, false);
    this.isActivable = isActivable;
    this.range = range;
  }

  public int getRange() {
    return this.range;
  }
  
  public abstract void action(List<Player> player);

  public abstract boolean isActivable();
}
