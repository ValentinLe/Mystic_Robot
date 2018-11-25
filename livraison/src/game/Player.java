package game;

import space.Position;
import space.Direction;
import java.util.*;

public class Player extends AbstractTile {

  // vie maximale du joueur
  public final int MAX_ENERGY;

  private int energy;
  private boolean hasShield;
  private Map<Equipement, Integer> equipement;
  private Game game;

  public Player(String type, Game game, Position position, int energy, boolean hasShield, Map<Equipement,Integer> equipement) {
    super(type, position, true);
    this.energy = energy;
    MAX_ENERGY = energy;
    this.hasShield = hasShield;
    this.equipement = this.buildCopyEquipement(equipement);
    this.game = game;
  }

  public void initPlayer() {
    this.energy = MAX_ENERGY;
    this.hasShield = false;
    this.fireChange();
  }

  /**
    * assigne this en proprietaire de tous les equipements
    */
  public Map<Equipement, Integer> buildCopyEquipement(Map<Equipement, Integer> equipement) {
    Map<Equipement, Integer> copyEquipement = new HashMap<>();
    for (Equipement e : equipement.keySet()) {
      Equipement equi = e.getCopy();
      equi.setOwner(this);
      copyEquipement.put(equi, new Integer(equipement.get(e)));
    }
    return copyEquipement;
  }

  public void setHasShield(boolean newState) {
    this.hasShield = newState;
  }

  public boolean playerUse(Equipement item, Direction direction) {
    if(equipement.get(item)>0){
      this.game.switchPlayer();
      if(item.use(this.position,direction,this.game)){
        equipement.put(item,equipement.get(item)-1);
        return true;
      }
      equipement.put(item,equipement.get(item)-1);
    }
    return false;
  }

  public boolean move(Direction direction) {
    Player player = this.game.getNextPlayer();
    Position positionPlayer = this.getPosition();
    Position new_pos = new Position(
            direction.getX() + positionPlayer.getX(),
            direction.getY() + positionPlayer.getY()
    );
    if (this.game.canMove(new_pos)){
      Tile[][] grid = this.game.getGrid();
      // place le joueur qui joue à la fin de la file

      Tile tileTarget = grid[new_pos.getY()][new_pos.getX()];

      Tile new_tile = new EmptyTile(positionPlayer);
      // positionnement de la nouvelle empty_tile dans le board
      this.game.setTile(new_tile);
      // changement de l'emplacement du joueur
      player.setPosition(new_pos);

      // positionnement du joueur dans le board
      this.game.setTile(player);

      // active le terrain sur lequelle le joueurva se déplacer
      this.game.activate(tileTarget);
      this.applyDamage(1, false); // degats sans utiliser le bouclier
      // rien mettre apres le switch et le fireChange pour le gui
      this.game.switchPlayer();
      this.fireChange();
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
    this.fireChange();
  }

  public void applyDamage(int damage) {
    this.applyDamage(damage, true);
  }

  public void skipTurn() {
    this.fireChange();
  }

  // applique les dommages au joueur selon si il a un bouclier
  public void applyDamage(int damage, boolean useShield) {
    if (useShield && this.hasShield) {
      // retire le bouclier
      this.hasShield = false;
    } else {
      this.energy -= damage;
      // evite une energy negative
      if (this.energy < 0) {
        this.energy = 0;
      }
    }
    this.fireChange();
  }

  public int getEnergy(){
    return this.energy;
  }

  public boolean getShield() {
    return this.hasShield;
  }

  public int getMaxEnergy() {
    return this.MAX_ENERGY;
  }

  public boolean isDead() {
    return this.energy <= 0;
  }

  @Override
  public boolean getIsObstacle() {
    return true;
  }

  public Game getGame(){
    return this.game;
  }

  public Map<Equipement,Integer> getEquipement() {
    return this.equipement;
  }

  public void setGame(Game game) {
      this.game = game;
  }

  /**
   * Retourne les stats du joueur sous forme de String
   * @return les stats du joueur
   */
  public String getStringStats() {
    return "Player " + this.type + " " + this.position + " : energy=" + this.energy + "/" + MAX_ENERGY + " shield=" + this.hasShield;
  }

  @Override
  public String toString() {
    return "" + this.type.charAt(0);
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) {
      return true;
    } else {
      if (o instanceof Player) {
        return this.position.equals(((Player)o).getPosition());
      } else {
        return false;
      }
    }
  }

}
