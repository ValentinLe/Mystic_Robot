package game;

import java.util.*;

public abstract class Explosif extends Weapon {

  public Explosif(int damage) {
    super(damage, 1);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public boolean use(Position position,Direction direction,Game board){
    Position newPosition = new Position(position.getX()+direction.getX(),position.getY()+direction.getY());
    ExplosifPlate newExplosifPlate = new ExplosifPlate(newPosition,true,this.range,this,((Player)board.getTileAt(position)));
    ((RealGame)board).setTile(newExplosifPlate);
    return true;
  }

  public void applyDamage(RealGame board, Position explosifPosition, Position direction) {
    Position positionPlate = this.getPosition();
    List<Player> players = this.board.getPlayersAround(positionPlate, this.range);
    for (Player player : players) {
      player.applyDamage(this.damage);
    }
    return true;
  }
}
