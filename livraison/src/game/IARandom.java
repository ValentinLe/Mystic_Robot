package game;

import space.*;
import java.util.*;

public class IARandom implements IA{

  private Equipement weapon;

  /**
   * Fais jouer un robot avec comme priorité d'attaquer un autre robot si celui
   * ci est à ça portée sinon il choisit une action aléatoirement
   * @param player la position de départ
   */

  public void execute(Player player){
    boolean continuer = true;
    Random rnd = new Random();
    Direction testPlayer = null;
    Direction dir=this.sameLine(player);
    if(dir!=null){
      continuer=false;

      boolean t = player.playerUse(this.weapon,dir);
    }

    if(continuer){
      ArrayList<Direction> listDirection = new ArrayList<Direction>();
      listDirection.add(Direction.UP);
      listDirection.add(Direction.DOWN);
      listDirection.add(Direction.LEFT);
      listDirection.add(Direction.RIGHT);

      int ran = rnd.nextInt(3);
      switch (ran) {
        case 0:
          // le joueur tente de bouger
          ArrayList<Direction> dirList = new ArrayList<>();
          boolean tmp = false;
          // regarde les position possible où bouger
          for (Direction d : listDirection){
            Position positionPlayer = player.getPosition();
            Position new_pos = new Position(
            d.getX() + positionPlayer.getX(),
            d.getY() + positionPlayer.getY()
            );
            if (player.getGame().isInIndex(new_pos) && player.getGame().canMove(new_pos)){
              boolean acceptMove = false;
              Tile tile = player.getGame().getTileAt(new_pos);
              if (tile instanceof ExplosifPlate) {
                if (!((ExplosifPlate)tile).isPlayerOwner(player) || rnd.nextDouble() < 0.5) {
                  // accepte de se deplacer sur la mine avec 1 chance sur 2
                  // pour eviter qu'ils se bloquent tout seul avec leur mine
                  acceptMove = true;
                }
              } else {
                // deplacement accepte
                acceptMove = true;
              }
              if (acceptMove) {
                dirList.add(d);
              }
            }
          }
          // si il a des cases où se déplacer il en choisit une parmit cella la
          if(!(dirList.isEmpty())){
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
          }

        case 1:
          // placement d'un Explosif
          Equipement item = null;
          // verifie si il a un explosif a placer et le récupère
          for (Equipement w : player.getEquipement().keySet()) {
            if (w instanceof Explosif && player.getEquipement().get(w)>0) {
              item = w;
              break;
            }
          }
          // si il a trouver un explosif a placer
          if (item != null){
            for (Direction d : listDirection){
              Position positionPlayer = player.getPosition();
              Position new_pos = new Position(
                      d.getX() + positionPlayer.getX(),
                      d.getY() + positionPlayer.getY()
              );
              // il le place à la premiere place où il peu
              if(player.getGame().isInIndex(new_pos) && player.getGame().getTileAt(new_pos) instanceof EmptyTile){
                player.playerUse(item,d);
                break;
              }
            }
          }
        case 2:
          // soit le random choisit ce cas soit les 2 cas précédent n'ont rien donnée
          // et le player passe son tour
          player.getGame().skipTurn();
          break;
      }
    }
  }

  /**
   * cherche dans quelle direction il y a un autre robot
   * @param player le robot à partir du quelle on récupère la position
   * @return la direction qu'il trouve où il y a un autre robot sinon null
   */

  public Direction sameLine(Player player) {
    int weaponRange = 0;
    List<Tile> listTile = new ArrayList<Tile>();
    // regarde et récupère si il y a une arme disponnible
    // et récupère la range
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
    // pour toutes les directions il regarde si un player est présent dans la direction
    for(Direction direction : listDirection) {
      listTile = player.getGame().getTileInDirection(player.getPosition(), direction, weaponRange);
      for(Tile tile : listTile) {
        if (tile instanceof Player) {
          // si il trouve un joueur dans une direction il retourne la direction
          return direction;
        }
      }
    }
    this.weapon= null;
    return null;
  }

}
