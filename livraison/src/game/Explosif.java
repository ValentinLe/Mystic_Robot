package game;

import space.Position;
import space.Direction;
import java.util.*;

public abstract class Explosif extends Weapon {

  public Explosif(String type, int damage, int range, Player owner) {
    super(type, damage, range, owner);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public boolean use(Position position,Direction direction,Game board){
    Position newPosition = new Position(position.getX()+direction.getX(),position.getY()+direction.getY());
    ((RealGame)board).setTile(this.toTile(newPosition));
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
