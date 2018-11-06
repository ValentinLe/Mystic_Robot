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

  /**
   * Utilisation de l'arme
   * @param position la position de départ
   * @param direction la direction dans laquelle l'arme est utilisé
   * @param board les fonctions contenue dans le board
   * @return true si l'arme à été utilisée avec succé
   */
  public boolean use(Position position,Direction direction,Board board){
    Player player = ((RealBoard)board).getPlayerInDirection(position,direction,this.range);
    if (player!=null){
      player.applyDamage(this.damage);
      return true;
    }
    return false;
  }
}
