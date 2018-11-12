package game;

import java.util.*;

public class ExplosifPlate extends Usable {

  private Explosif type;
  private Player owner;

  public ExplosifPlate(Position position, boolean isActivable, int range, Explosif type, Player owner) {
    super(position, isActivable, range);
    this.type = type;
    this.owner = owner;
    this.range = range;
  }

  /**
   * Retire de la vie à tous les joueurs de la liste
   * @param players liste des joueurs
   */
  @Override
  public void action(List<Player> players) {
    for (Player player : players) {
      player.applyDamage(this.type.getDamage());
    }
  }

  @Override
  public boolean isActivable() {
    return this.isActivable;
  }

  public int getDamage() {
    return this.type.getDamage();
  }
  
  public Player getPlayer() {
    return this.owner;
  }

  @Override
  public String toString() {
    return "x";
  }
}
