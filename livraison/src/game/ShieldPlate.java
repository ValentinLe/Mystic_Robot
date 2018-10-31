
package game;

import java.util.List;

public class ShieldPlate extends Usable {
  
  public ShieldPlate(Position pos, boolean isActivable, int range) {
    super(pos, isActivable, range);
  }
  
  @Override
  public void action(List<Player> players) {
    for (Player player : players) {
      player.setHasShield(true);
    }
  }
  
  @Override
  public String toString() {
    return "@";
  }
}
