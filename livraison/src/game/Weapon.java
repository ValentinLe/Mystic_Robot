package game;

public class Weapon implements Equipement {

  protected int damage;
  protected int range;
  protected Player owner;
  protected String type;

  public Weapon(String type, int damage, int range, Player owner) {
    this.damage = damage;
    this.range = range;
    this.owner = owner;
    this.type = type;
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
  public boolean use(Position position,Direction direction,Game board){
    Player player = ((RealGame)board).getPlayerInDirection(position,direction,this.range);
    if (player!=null){
      player.applyDamage(this.damage);
      return true;
    }
    return false;
  }

  public String getType(){
    return this.type;
  }

  @Override
  public String toString(){
    return this.type;
  }
}
