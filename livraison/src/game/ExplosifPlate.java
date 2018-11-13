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
  public void action(RealGame board) {
    this.type.applyDamage(board,this.position,new Position(-1,-1));
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

  public Explosif getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return "x";
  }
}
