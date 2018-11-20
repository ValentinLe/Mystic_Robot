package game;

import java.util.*;

public class ExplosifPlate extends Usable {

  private int damage;
  private Player owner;

  public ExplosifPlate(String type, Position position, boolean isActivable, int range, int damage, Player owner) {
    super(type, position, isActivable, range);
    this.damage = damage;
    this.owner = owner;
    this.range = range;
  }

  /**
   * Retire de la vie à tous les joueurs de la liste
   * @param players liste des joueurs
   */
  @Override
  public void action(RealGame board) {
    List<Player> players = board.getPlayersAround(this.position, this.range);
    for (Player player : players) {
      player.applyDamage(this.damage);
    }
  }

  @Override
  public boolean isActivable() {
    return this.isActivable;
  }

  public int getDamage() {
    return this.damage;
  }

  public Player getPlayer() {
    return this.owner;
  }

  @Override
  public String toString() {
    return "x";
  }
}
