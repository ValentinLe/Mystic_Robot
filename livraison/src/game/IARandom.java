package game;

import space.*;
import java.util.*;

public class IARandom implements IA{

  private Equipement weapon;

  public void execute(Player player){
    boolean continuer = true;
    Direction testPlayer = null;
    // si un joueur se trouve sur la même ligne ou colonne
      Direction dir=this.sameLine(player);
      if(dir!=null){
        continuer=false;
        // si un joueur est dans la range lui tiré dessus
        player.playerUse(this.weapon,dir);
      }

    if(continuer){
      ArrayList<Direction> listDirection = new ArrayList<Direction>();
      listDirection.add(Direction.UP);
      listDirection.add(Direction.DOWN);
      listDirection.add(Direction.LEFT);
      listDirection.add(Direction.RIGHT);

      // random pour choisir l'action
      Random rnd = new Random();
      int ran = rnd.nextInt(3);
      System.out.println("ia random choix : "+ ran );
      switch (ran) {
        case 0:
          // move
          ArrayList<Direction> dirList = new ArrayList<>();
          dirList=null;
          boolean tmp = false;
          for (Direction d : listDirection){
            Position positionPlayer = player.getPosition();
            Position new_pos = new Position(
            d.getX() + positionPlayer.getX(),
            d.getY() + positionPlayer.getY()
            );
            if (player.getGame().isInIndex(new_pos) && !(player.getGame().getTileAt(new_pos) instanceof ExplosifPlate) && player.getGame().canMove(player.getPosition())){
              dirList.add(d);
            }
          }
          if(dirList==null){
            dirList=listDirection;
          }

          while(!tmp){
            int ran2 = rnd.nextInt(dirList.size());
            Position positionPlayer = player.getPosition();
            Position new_pos = new Position(
            dirList.get(ran2).getX() + positionPlayer.getX(),
            dirList.get(ran2).getY() + positionPlayer.getY()
            );
            if (player.getGame().isInIndex(new_pos)){
              tmp = player.move(dirList.get(ran2));
            }
          }
          break;
        case 1:
          // placer un explosif
          Equipement item = null;
          for (Equipement w : player.getEquipement().keySet()) {
            if (w instanceof Explosif && player.getEquipement().get(w)>0) {
              item = w;
              break;
            }
          }
          if (item != null){
            for ( Direction d : listDirection){
              Position positionPlayer = player.getPosition();
              Position new_pos = new Position(
                      d.getX() + positionPlayer.getX(),
                      d.getY() + positionPlayer.getY()
              );
              if(player.getGame().isInIndex(new_pos) && player.getGame().getTileAt(new_pos) instanceof EmptyTile){
                player.playerUse(item,d);
                break;
              }
            }
          }
          break;
        case 2:
          // passer son tour
          player.getGame().skipTurn();
          break;
      }
    }
  }

  public Direction sameLine(Player player) {
    int weaponRange = 0;
    List<Tile> listTile = new ArrayList<Tile>();
    for (Equipement w : player.getEquipement().keySet()) {
      if (w instanceof Gun && player.getEquipement().get(w)>0) {
        weaponRange = ((Gun)w).getRange();
        this.weapon=w;
      }
    }

    ArrayList<Direction> listDirection = new ArrayList<Direction>();
    listDirection.add(Direction.UP);
    listDirection.add(Direction.DOWN);
    listDirection.add(Direction.LEFT);
    listDirection.add(Direction.RIGHT);

    for(Direction direction : listDirection) {
      listTile = player.getGame().getTileInDirection(player.getPosition(), direction, weaponRange);
      for(Tile tile : listTile) {
        if (tile instanceof Player) {
          return direction;
        }
      }
    }
    this.weapon= null;
    return null;
  }

}
