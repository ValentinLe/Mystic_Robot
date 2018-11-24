package game;

import space.*;
import java.util.*;
import observer.*;

public class RealGame extends AbstractListenableModel implements Game {
  private Tile[][] grid;
  private GridGenerator gridGenerator;
  // utilisation d'une file plutot qu'une liste
  private LinkedList<Player> players = new LinkedList<Player>();;
  private int width;
  private int height;

  public RealGame(int width, int height, ArrayList<Player> playerList, GridGenerator gridGenerator){
    this.width = width;
    this.height = height;
    this.gridGenerator = gridGenerator;
    this.players = new LinkedList<>(playerList);
    this.addGameToAllPlayer(playerList);
    this.grid = gridGenerator.generateGrid(width, height, playerList);
  }

  public void addGameToAllPlayer(List<Player> listPlayers) {
      for (Player player : listPlayers) {
          player.setGame(this);
      }
  }

  // verifie si une case à la position pos de la grille est un obstacle
  public boolean isObstacleTile(Position pos){
    return this.grid[pos.getY()][pos.getX()].getIsObstacle();
  }

  // verifie si une case à la position pos de la grille est un player
  public boolean isPlayerOnPosition(Position pos){
    for(Player p : this.players){
      if (p.getPosition().getX()==pos.getX() && p.getPosition().getY()==pos.getY()){
        return true;
      }
    }
    return false;
  }

  // savoir si un player peut bouger
  public boolean canMove(Position position){
    if (this.isInIndex(position)) {
      return !(this.isObstacleTile(position));
    }
    return false;
  }

  /**
   * Active la case avec la liste des joueurs dans le voisinage de la case si
   * c'est une instance d'usable
   * @param tile la case à éventuellement activer
   */
  public void activate(Tile tile) {
    if (tile instanceof Usable){
      ((Usable)tile).action(this);
    }
  }

  /**
   * Passe le tour du joueur courant
   */
  public void skipTurn() {
    this.switchPlayer();
  }

  /**
   * Utilise un item à l'emplacement i de l'ensemble des équipements du player
   * actuel
   * @param item l'équipement à utiliser
   * @param direction la direction dans laquelle poser l'item
   * @return true si l'item à été utilisé
   */
  public boolean playerUseItem(Equipement item, Direction direction){
    return this.getNextPlayer().playerUse(item,direction);
  }


  /**
   * place le joueur en tete de file à la fin de celle-ci
   */
  public void switchPlayer() {
    this.deadPlayer();
    Player headPlayer = this.players.poll();
    this.players.add(headPlayer);
    this.fireChange();
    //this.bombCounter();
  }

  public void deadPlayer() {
    Position position = new Position(-1,-1);
    for (int i = 0; i < this.players.size(); i++) {
      if (this.players.get(i).getEnergy() == 0) {
        position = this.players.get(i).getPosition();
        this.players.remove(i);
        this.setTile(new EmptyTile(position));
      }
    }
  }

  /**
   * Teste si la liste des joueurs contient un ou plusieurs joueurs
   *@return true si la liste des joueurs ne contient qu'un seul joueur ou moins
   */
  public boolean isOver() {
    if (!(this.players.size() > 1)) {
      return true;
    }
    return false;
  }

  /**
   * Test si la position est dans la grille
   * @param position la position à tester
   * @return true si la position est dans la grille
   */
  public boolean isInIndex(Position position) {
    int x = position.getX();
    int y = position.getY();
    return this.isInIndex(x, y);
  }

  /**
   * Test si la coordonnée (x,y) est dans la grille
   * @param x la coordonnée en absisce
   * @param y la coordonnée en ordonnée
   * @return true si (x,y) est dans la grille
   */
  public boolean isInIndex(int x, int y) {
    return 0 <= x && x < this.width && 0 <= y && y < this.height;
  }

  /**
   * Récupère la liste des voisins d'un position selon une portée
   * @param position la position centrale
   * @param range la porté du voisinage 0 <= range
   * @return la liste de case dans le voisinage de la position
   */
  public List<Tile> consvois(Position position, int range) {
    List<Tile> listConsvois = new ArrayList<>();
    int posX = position.getX();
    int posY = position.getY();
    for (int j = posY-range; j < (posY + range + 1); j++) {
      for (int i = posX-range; i < (posX + range + 1); i++) {
        if (this.isInIndex(i, j)) {
          listConsvois.add(this.grid[j][i]);
        }
      }
    }
    return listConsvois;
  }

  /*
  public void bombCounter() {
    for (int i = 0; i < this.grid.length; i++) {
      for (Tile tile : this.grid[i]) {
        if (tile instanceof ExplosifPlate) {
          Explosif explosif = ((ExplosifPlate)tile).getType();
          if (explosif instanceof Bomb) {
            ((Bomb)explosif).setCounter(((Bomb)explosif).getCounter()-1);
            this.activate(tile);
          }
        }
      }
    }
  }*/

  public void setTile(Tile tile) {
    Position posTile = tile.getPosition();
    this.grid[posTile.getY()][posTile.getX()] = tile;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  public Tile[][] getGrid() {
    return this.grid;
  }

  @Override
  public List<Player> getListPlayers() {
    return this.players;
  }

  /**
  * récupère le premier joueur de la file
  * @return le prochain joueur qui doit jouer
  */
  public Player getNextPlayer() {
    return this.players.peek();
  }

  /**
   * Retourne une liste de joueurs présent dans une liste de cases
   * @param listTiles la liste de cases
   * @return la liste des joueurs présents
   */
  public List<Player> getPlayersInList(List<Tile> listTiles) {
    List<Player> listPlayer = new ArrayList<>();
    for (Tile tile : listTiles) {
      if (tile instanceof Player) {
        listPlayer.add(((Player) tile));
      }
    }
    return listPlayer;
  }

  /**
   * Récupère la liste des joueurs présents autour d'une position selon une porté
   * @param position la position centrale
   * @param range la porté du voisinage
   * @return la liste des joueurs autour de la position
   */
  public List<Player> getPlayersAround(Position position, int range) {
    List<Tile> voisins = this.consvois(position, range);
    return this.getPlayersInList(voisins);
  }

  public Map<Equipement, Integer> getPlayerEquipement(){
    return this.getNextPlayer().getEquipement();
  }

  /**
   * Recupére les cases depuis une position dans une direction selon une portée
   * @param position la position de départ
   * @param direction la direction dans laquelle aller chercher les cases
   * @param range la porté (le nombre de cases à avoir)
   * @return la liste des cases voulues
   */
  public List<Tile> getTileInDirection(Position position, Direction direction, int range) {
    List<Tile> listTile = new ArrayList<>();
    int dirX = direction.getX();
    int dirY = direction.getY();
    Position tempPos = position.getCopy();
    for (int k = 1; k < (range + 1); k++) {
      tempPos.setX(position.getX() + k * dirX);
      tempPos.setY(position.getY() + k * dirY);
      if (this.isInIndex(tempPos)) {
        listTile.add(this.getTileAt(tempPos));
      }
    }
    return listTile;
  }

  /**

   */
  public Player getPlayerInDirection(Position position, Direction direction, int range){
    Position new_pos = position.getCopy();
    for(int i = 0; i<range+1;i++){
      new_pos.addX(direction.getX());
      new_pos.addY(direction.getY());
      if (!this.isInIndex(new_pos)){
        return null;
      }
      if (this.isObstacleTile(new_pos)){
        if (this.isPlayerOnPosition(new_pos)){
          if (this.getTileAt(new_pos) instanceof Player){
            return ((Player)this.getTileAt(new_pos));
          }
        }
        return null;
      }
    }
    return null;
  }

  /**
   * Getter de la case présente à la position
   * @param position la position dont on veut la case
   * @return la case ciblée
   */
  @Override
  public Tile getTileAt(Position position) {
    return this.grid[position.getY()][position.getX()];
  }

  @Override
  public String toString() {
    String res = "  ";
    // numero superieurs
    for (int k = 0; k < this.width; k++) {
      res += " " + k + " ";
    }
    res += "\n";
    for (int j = 0; j < this.height; j++) {
      res += j + " "; // numeros coté
      for (int i = 0; i < this.width; i++) {
        Tile tile = this.getTileAt(new Position(i, j));
        res += " " + tile + " ";
      }
      res += "\n";
    }
    res += "\n";
    // liste les stats des joueurs (energy, shield, etc.)
    for (Player player : this.players) {
      res += player.getStringStats() + "\n";
    }
    return res;
  }
}
