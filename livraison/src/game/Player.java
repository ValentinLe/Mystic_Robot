package game;

import java.util.*;

public class Player extends AbstractTile {

  // vie maximale d'un joueur
  public final static int MAX_ENERGY = 20;

  private String name;
  private int energy;
  private boolean hasShield;
  private Map<Equipement, Integer> equipement;
  private Game board;

  public Player(String name, Game board, Position position, int energy, boolean hasShield, Map<Equipement,Integer> equipement) {
    super(position, true);
    this.name = name;
    this.energy = energy;
    this.hasShield = hasShield;
    this.equipement = equipement;
    this.board = board;
  }

  public void setHasShield(boolean newState) {
    this.hasShield = newState;
  }

  public boolean playerUse(Equipement item, Direction direction) {
    if(equipement.get(item)>0){
      this.board.switchPlayer();
      if(item.use(this.position,direction,this.board)){
        equipement.put(item,equipement.get(item)-1);
        return true;
      }
      equipement.put(item,equipement.get(item)-1);
    }
    return false;
  }

  public boolean move(Direction direction) {
    Player player = this.board.getNextPlayer();
    Position positionPlayer = this.getPosition();
    Position new_pos = new Position(
            direction.getX() + positionPlayer.getX(),
            direction.getY() + positionPlayer.getY()
    );
    if (this.board.canMove(new_pos)){
      Tile[][] grid = this.board.getGrid();
      // place le joueur qui joue à la fin de la file
      this.board.switchPlayer();

      Tile tileTarget = grid[new_pos.getY()][new_pos.getX()];

      Tile new_tile = new EmptyTile(positionPlayer);
      // positionnement de la nouvelle empty_tile dans le board
      this.board.setTile(new_tile);
      // changement de l'emplacement du joueur
      player.setPosition(new_pos);

      // positionnement du joueur dans le board
      this.board.setTile(player);

      // active le terrain sur lequelle le joueurva se déplacer
      this.board.activate(tileTarget);
      return true;
    }
    return false;
  }

  // add + de l'énergie
  public void addEnergy(int energy){
    this.energy += energy;
    if (this.energy > MAX_ENERGY) {
      this.energy = MAX_ENERGY;
    }
  }

  // applique les dommages au joueur selon si il a un bouclier
  public void applyDamage(int damage) {
    if (this.hasShield) {
      // retire le bouclier
      this.hasShield = false;
    } else {
      this.energy -= damage;
      // evite une energy negative
      if (this.energy < 0) {
        this.energy = 0;
      }
    }
  }

  public int getEnergy(){
    return this.energy;
  }

  @Override
  public boolean getIsObstacle() {
    return true;
  }

  public String getName() {
    return this.name;
  }

  public Map<Equipement,Integer> getEquipement() {
    return this.equipement;
  }

  /**
   * Retourne les stats du joueur sous forme de String
   * @return les stats du joueur
   */
  public String getStringStats() {
    return "Player " + this.name + " " + this.position + " : energy=" + this.energy + "/" + MAX_ENERGY + " shield=" + this.hasShield;
  }

  @Override
  public String toString() {
    return "" + this.name.charAt(0);
  }

}
