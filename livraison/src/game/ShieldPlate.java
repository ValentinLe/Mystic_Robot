
package game;

import java.util.List;

public class ShieldPlate extends Usable {

  public ShieldPlate(Position pos, boolean isActivable, int range) {
    super("ShieldPlate",pos, isActivable, range);
  }

  /**
   * met tous les bouclier des joueurs à true
   * @param players liste des joueurs impliqués
   */
  @Override
  public void action(RealGame board) {
    ((Player)board.getTileAt(this.position)).setHasShield(true);
  }

  @Override
  public String toString() {
    return "*";
  }
}
