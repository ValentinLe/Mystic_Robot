package game;

public abstract class Explosif extends Weapon {

  public Explosif(int damage) {
    super(damage, 1);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public boolean use(Position position,Direction direction,Board board){
    Position newPosition = new Position(position.getX()+direction.getX(),position.getY()+direction.getY());
    ExplosifPlate newExplosifPlate = new ExplosifPlate(newPosition,true,this.range,this,((Player)board.getTileAt(position)));
    ((RealBoard)board).setTile(newExplosifPlate);
    return true;
  }

  public void applyDamage(RealBoard b, Position explosifPosition, Position direction) {
    int xExplosif = explosifPosition.getX();
    int yExplosif = explosifPosition.getY();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        Tile tile = b.getTileAt(new Position(xExplosif + i, yExplosif + j));
        if (tile instanceof Player) {
          ((Player)tile).applyDamage(this.damage);
        }
      }
    }
  }
}
