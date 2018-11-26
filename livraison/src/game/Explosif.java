package game;

import space.Position;
import space.Direction;
import java.util.*;

public abstract class Explosif extends Weapon {

  /**
    * Constructeur de la classe
    * @param type , de type String.
    * @param damage , qui est de type int.
    * @param range , qui est de type int.
    * @param owner , qui est de type Player.
    */
  public Explosif(String type, int damage, int range, Player owner) {
    super(type, damage, range, owner);
  }

  /**
    * Méthode renvoyant les dommages.
    *@return this.damage
    *
    *
    */
  @Override
  public int getDamage() {
    return this.damage;
  }

  /**
    * Méthode permettant de utiliser un equipement.
    * @param position , qui est de type Position.
    * @param direction , qui est de type Direction.
    * @param game , qui est de type Game.
    * @return true car l'explosif a bien ete utilise
    */
  @Override
  public boolean use(Position position,Direction direction,Game game){
    Position newPosition = new Position(position.getX()+direction.getX(),position.getY()+direction.getY());
    game.setTile(this.toTile(newPosition));
    return true;
  }

  /**
    * Méthode permettant d'appliquer les dommages.
    * @param board , qui est de type RealGame.
    * @param explosifPosition , qui est de type Position.
    * @param direction , qui est de type Position.
    */
  public void applyDamage(RealGame board, Position explosifPosition, Position direction) {
    List<Player> players = board.getPlayersAround(explosifPosition, this.range);
    for (Player player : players) {
      System.out.println(this + " " + this.damage);
      player.applyDamage(this.damage);
    }
  }

  /**
    * Méthode permettant de mettre en Tile avec une certaine position
    * @param position la position que devra avoir la case
    * @return la case construite avec la position
    */
  public abstract Tile toTile(Position position);

}
