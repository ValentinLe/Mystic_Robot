package game;

import space.Position;
import java.util.*;

public abstract class Usable extends AbstractTile {

  protected boolean isActivable;
  protected int range;

  public Usable(String type, Position pos, boolean isActivable, int range) {
    super(type,pos, false);
    this.isActivable = isActivable;
    this.range = range;
  }

  public int getRange() {
    return this.range;
  }

  public abstract void action(RealGame board);

  public boolean isActivable() {
    return this.isActivable;
  }
}
