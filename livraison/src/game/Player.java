package game;

import space.Position;
import space.Direction;
import java.util.*;

/**
 * represente un combattant
 *
 */
public class Player extends AbstractTile {

  // vie maximale du joueur
  public final int MAX_ENERGY;

  private int energy;
  private boolean hasShield;
  private Map<Equipement, Integer> equipement;
  private Game game;

  /**
   * Creer un joueur à une position
   * @param type le nom du joueur
   * @param game sa vision (proxy ou non)
   * @param position la position du joueur
   * @param energy l'energy du joueur
   * @param hasShield si le joueur possède un bouclier
   * @param equipement les equipement du joueur
   */
  public Player(String type, Game game, Position position, int energy, boolean hasShield, Map<Equipement,Integer> equipement) {
    super(type, position, true);
    this.energy = energy;
    MAX_ENERGY = energy;
    this.hasShield = hasShield;
    this.equipement = this.buildCopyEquipement(equipement);
    this.game = game;
  }

  /**
   * Reinitialise la vie joueur et le bouclier
   */
  public void initPlayer() {
    this.energy = MAX_ENERGY;
    this.hasShield = false;
    this.fireChange();
  }

  /**
   * Creer une copy de tous les equipements
   * @param equipement les equipements
   * @return une copy des equipements
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

  /**
   * setter sur le bouclier du joueur
   * @param newState la nouvelle valeur du bouclier
   */
  public void setHasShield(boolean newState) {
    this.hasShield = newState;
  }

  /**
   * utilise un equipement dans une direction donnees
   * @param item l'equipement a utiliser
   * @param direction la direction dans laquelle l'utiliser
   * @return true si l'objet a bien ete utilise
   */
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

  /**
   * deplace le joueur dans une direction donnee
   * @param direction la direction dans laquelle le joueur se deplace
   * @return true si le joueur a ete deplace
   */
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

  /**
   * ajoute de l'energy au joueur
   * @param energy le nombre d'energy a ajouter
   */
  public void addEnergy(int energy){
    this.energy += energy;
    if (this.energy > MAX_ENERGY) {
      this.energy = MAX_ENERGY;
    }
    this.fireChange();
  }

  /**
   * applique un nombre de domage au joueur en consommant le bouclier si il
   * en a un
   * @param damage le nombre de domage a infliger
   */
  public void applyDamage(int damage) {
    this.applyDamage(damage, true);
  }

  /**
   * tour passer var simplement actualiser les listeners
   */
  public void skipTurn() {
    this.fireChange();
  }

  /**
   * applique les dommages au joueur si l'utilisation du bouclier est actif et
   * applique les domages si le joueur n'a pas de bouclier et le retire sinon
   * @param damage le nombre de domage a aplliquer
   * @param useShield utilisation du bouclier ou non
   */
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

  /**
   * getter du nombre d'energy du joueur
   * @return le nombre d'energy du joueur
   */
  public int getEnergy(){
    return this.energy;
  }

  /**
   * getter sur le bouclier du joueur
   * @return true si le joueur possede un bouclier
   */
  public boolean getShield() {
    return this.hasShield;
  }

  /**
   * getter sur la vie maximale du joueur
   * @return la vie maximale du joueur
   */
  public int getMaxEnergy() {
    return this.MAX_ENERGY;
  }

  /**
   * test si le joueur n'a plus de vie
   * @return true si le joueur est mort
   */
  public boolean isDead() {
    return this.energy <= 0;
  }

  /**
   * getter si le joueur est un obstacle
   * @return true car le joueur est un obstacle
   */
  @Override
  public boolean getIsObstacle() {
    return true;
  }

  /**
   * getter du jeu du joueur (proxy ou non)
   * @return le jeu du joueur
   */
  public Game getGame(){
    return this.game;
  }

  /**
   * getter sur l'equipement du joeur
   * @return l'equipement du joueur
   */
  public Map<Equipement,Integer> getEquipement() {
    return this.equipement;
  }

  /**
   * setter sur le jeu du joueur
   * @param game le nonveau jeu a lui associer
   */
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

  /**
   * representation en String du joueur
   * @return la premiere lettre du joueur (pour version console)
   */
  @Override
  public String toString() {
    return "" + this.type.charAt(0);
  }

  /**
   * test si 2 joueurs sont egaux
   * @param o l'autre objet pour le test
   * @return true si c'est les memes joueurs (il auront la meme position)
   */
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
