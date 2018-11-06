package game;

public class Weapon implements Equipement {

  protected int damage;
  protected int range;

  public Weapon(int damage, int range) {
    this.damage = damage;
    this.range = range;
  }

  public int getDamage() {
    return this.damage;
  }

  public int getRange() {
    return this.range;
  }

  public boolean use(Position position,Direction direction,Board board){
    Player player = ((RealBoard)board).getPlayerInDirection(position,direction,this.range);
    if (player!=null){
      player.applyDamage(this.damage);
      return true;
    }
    return false;

  }
}
