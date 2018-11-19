package game;

import java.util.*;

public abstract class Explosif extends Weapon {

  public Explosif(int damage, int range, Player owner) {
    super(damage, range, owner);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public boolean use(Position position,Direction direction,Game board){
    Position newPosition = new Position(position.getX()+direction.getX(),position.getY()+direction.getY());
    ExplosifPlate newExplosifPlate = new ExplosifPlate(newPosition,true,this.range,this.damage,((Player)board.getTileAt(position)));
    ((RealGame)board).setTile(newExplosifPlate);
    return true;
  }

  public void applyDamage(RealGame board, Position explosifPosition, Position direction) {
    List<Player> players = board.getPlayersAround(explosifPosition, this.range);
    for (Player player : players) {
      System.out.println(this + " " + this.damage);
      player.applyDamage(this.damage);
    }
  }
  
  public abstract Tile toTile(Position position);
}
