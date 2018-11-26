package game;

import space.*;
import java.util.*;
import observer.*;

/**
 * classe representant le jeu en vue globale
 *
 */
public class RealGame extends AbstractListenableModel implements Game {
  private Tile[][] grid;
  private GridGenerator gridGenerator;
  // utilisation d'une file plutot qu'une liste
  private LinkedList<Player> players;
  private int width;
  private int height;
  private IA ia;
  private int nbPlayerInTurn;

  /**
   * creer une instance de jeu
   * @param width la largeur de la grille du jeu
   * @param height la hauteur de la grille du jeu
   * @param playerList la liste des joueurs a placer
   * @param gridGenerator un generateur de grille
   * @param ia l'ia a utiliser pour les joeurs
   */
  public RealGame(int width, int height, List<Player> playerList, GridGenerator gridGenerator,IA ia){
    this.width = width;
    this.height = height;
    this.gridGenerator = gridGenerator;
    this.players = new LinkedList<>(playerList);
    this.addGameToAllPlayer(playerList);
    this.grid = gridGenerator.generateGrid(width, height, playerList);
    this.ia=ia;
    this.nbPlayerInTurn = playerList.size();
  }

  /**
   * Execute l'IA pour le player courant puis change le joueurs
   */
  public void iaExecute(){
    this.ia.execute(this.getNextPlayer());
  }

  /**
    * fait tourner l'ia
    */
  public void loopIa() {
    while (!this.isOver()) {
      this.iaExecute();
      try {
        Thread.sleep(100);
      } catch(InterruptedException e) {

      }
    }
  }

  /**
   * ajoute le Game a tous les joueurs de la liste
   * @param listPlayers la liste de players aux quelle ajouter la Game
   */
  public void addGameToAllPlayer(List<Player> listPlayers) {
      for (Player player : listPlayers) {
          player.setGame(this);
      }
  }

  /**
   * verifie si une case à la position pos de la grille est un obstacle
   * @param pos la position du test
   * @return true si la Tile est un obstacle
   */
  public boolean isObstacleTile(Position pos){
    return this.grid[pos.getY()][pos.getX()].getIsObstacle();
  }

  /**
   * verifie si une case à la position pos de la grille est un player
   * @param pos la position du test
   * @return true si il y a un joueur sur la case
   */
  public boolean isPlayerOnPosition(Position pos){
    for(Player p : this.players){
      if (p.getPosition().equals(pos)) {
        return true;
      }
    }
    return false;
  }

  /**
   * test si un joueur peut se déplacer sur la nouvelle position
   * @param position la position du test
   * @return true si le peux se déplacer à la nouvelle position
   */
  @Override
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
  @Override
  public void activate(Tile tile) {
    if (tile instanceof Usable){
      ((Usable)tile).action(this);
    }
  }

  /**
   * Passe le tour du joueur courant
   */
  @Override
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
  @Override
  public void switchPlayer() {
    this.getNextPlayer().skipTurn();
    this.deadPlayer();
    Player headPlayer = this.players.poll();
    this.players.add(headPlayer);
    this.ChangeNbPlayerInTurn();
    this.bombCounter();
    this.fireChange();
  }

  /**
    * decremente la variable de tour et si elle est inferieur à 0 on la met a 0
    */
  private void ChangeNbPlayerInTurn() {
    this.nbPlayerInTurn -= 1;
    if (this.nbPlayerInTurn < 0) {
      this.nbPlayerInTurn = 0;
    }
  }

  /**
   * Supprime l'ensemble des player dont l'énergie est égal à 0, de la list des players
   * et ajoute ceci à la liste des deadPlayers
   */
  private void deadPlayer() {
    Position position = new Position(-1,-1);
    for (int i = 0; i < this.players.size(); i++) {
      if (this.players.get(i).getEnergy() == 0) {
        position = this.players.get(i).getPosition();
        this.players.remove(i);
        this.setTile(new EmptyTile(position));
        this.ChangeNbPlayerInTurn();
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
  @Override
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
   * @param range la porté du voisinage qui dooit etre superieur a 0
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

  /**
    * Méthode qui décrémente le compteur des bombes et fait appel à la méthode action si le compteur est égal à 0
    */
  public void bombCounter() {
    for (int i = 0; i < this.grid.length; i++) {
      for (Tile tile : this.grid[i]) {
        if (tile instanceof ExplosifPlate) {
          if (this.nbPlayerInTurn == 0) {
            ((ExplosifPlate)tile).updateCounter();
          }
          if (((ExplosifPlate)tile).getCounter() == 0) {
            ((ExplosifPlate)tile).action(this);
            this.setTile(new EmptyTile(tile.getPosition().getCopy()));
          }
        }
      }
    }
    if (this.nbPlayerInTurn == 0) {
      this.nbPlayerInTurn = this.players.size();
    }
  }

  /**
   * remplace une Tile par la tile passé en paramêtre
   * @param tile la Tile par laquelle on veut remplacer
   */
  @Override
  public void setTile(Tile tile) {
    Position posTile = tile.getPosition();
    this.grid[posTile.getY()][posTile.getX()] = tile;
  }

  /**
    * Méthode qui modifie l'attribut nbPlayerInTurn
    *@param nbPlayerInTurn le nombre de joueurs
    */
  public void setnbPlayerInTurn(int nbPlayerInTurn) {
    this.nbPlayerInTurn = nbPlayerInTurn;
  }

  /**
   * Retourne la largeur de la map
   * @return int
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Retourne la hauteur de la map
   * @return int
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Retourne la map de jeu
   * @return Tile[][]
   */
  @Override
  public Tile[][] getGrid() {
    return this.grid;
  }

  /**
   * Retourne la liste des joueurs sur la map
   * @return la liste des joueurs
   */
  @Override
  public List<Player> getListPlayers() {
    return this.players;
  }

  /**
   * Retourne l'équipement du joueur courrant
   * @return le map d'équipement du joueur
   */
  @Override
  public Map<Equipement, Integer> getEquipementOfPlayer() {
    return this.getNextPlayer().getEquipement();
  }

  /**
  * récupère le premier joueur de la file
  * @return le prochain joueur qui doit jouer
  */
  @Override
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

  /**
   * Recupére les cases depuis une position dans une direction selon une portée
   * @param position la position de départ
   * @param direction la direction dans laquelle aller chercher les cases
   * @param range la porté (le nombre de cases à avoir)
   * @return la liste des cases voulues
   */
  @Override
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
   * retourne le player qui se trouve dans la direction indiqué
   * @param position position à partir de laquelle on cherche
   * @param direction direction dans laquelle chercher
   * @param range jusqu'où cherché dans la direction voulus
   * @return le player trouvé
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

  /**
   * fonction toString permet de retourner la grille de jeu sous forme de chaine
   * de caractères
   * @return String
   */
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
