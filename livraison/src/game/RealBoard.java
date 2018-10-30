package game;

import java.util.*;

public class RealBoard implements Board {
  private Tile[][] grid;
  private List<Player> players = new ArrayList<>();;
  private int width;
  private int height;

  public RealBoard(int width, int height, int nbPlayer, RobotFactory factory){
    this.width = width;
    this.height = height;
    initGrid(nbPlayer, 20);
  }

  public void generateGrid() {
    this.grid = new Tile[this.height][this.width];
    for (int j = 0; j < this.height; j++) {
      for (int i = 0; i < this.width; i++) {
        this.setTile(new EmptyTile(new Position(i,j)));
      }
    }
  }

  public void initGrid(int nbPlayer, int nbEnergy) {
    this.generateGrid();
    Random r = new Random();
    Player testPb = null;

    // placement des energies
    int xPlate;
    int yPlate;
    for (int j = 0; j < nbEnergy; j++) {
      xPlate = r.nextInt(width);
      yPlate = r.nextInt(height);
      Tile energyPlate = new EnergyPlate(new Position(xPlate, yPlate), true, 0, 5);
      this.setTile(energyPlate);
    }
    
    // placement des explosifs
    int xBomb;
    int yBomb;
    for (int k = 0; k < nbEnergy; k++) {
      xBomb = r.nextInt(width);
      yBomb = r.nextInt(height);
      Tile bomb = new ExplosifPlate(new Position(xBomb, yBomb), true, 1, new Bomb(r.nextInt(10)+1,2), testPb);
      this.setTile(bomb);
    }
    // placement des joueurs
    int xPlayer;
    int yPlayer;
    for (int i = 0; i < nbPlayer; i++) {
      xPlayer = r.nextInt(width);
      yPlayer = r.nextInt(height);
      Position positionPlayer = new Position(xPlayer, yPlayer);
      while (!this.canMove(positionPlayer)) {
        xPlayer = r.nextInt(width);
        yPlayer = r.nextInt(height);
        positionPlayer.setX(xPlayer);
        positionPlayer.setY(yPlayer);
      }
      Player newPlayer = new Player("" + i, positionPlayer, 10, false, new HashMap<>());
      this.initPlayer(newPlayer);
    }
    testPb = this.players.get(0);
  }
  
  /**
   * Ajoute un joueur dans la grille et dans la liste des joueurs
   * @param player le joueur à ajouter
   */
  public void initPlayer(Player player) {
    if (this.canMove(player.getPosition())) {
      this.players.add(player);
      setTile(player);
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

  public void move(Player player, Direction direction) {
    Position positionPlayer = player.getPosition();
    Position new_pos = new Position(
            direction.getX() + positionPlayer.getX(),
            direction.getY() + positionPlayer.getY()
    );
    if (this.canMove(new_pos)){
      Tile tileTarget = this.grid[new_pos.getY()][new_pos.getX()];

      Tile new_tile = new EmptyTile(positionPlayer);
      // positionnement de la nouvelle empty_tile dans le board
      this.setTile(new_tile);
      // changement de l'emplacement du joueur
      player.setPosition(new_pos);

      // positionnement du joueur dans le board
      this.setTile(player);

      // active le terrain sur lequelle le joueurva se déplacer
      this.activate(tileTarget);
    }
  }
  
  /**
   * Active la case avec la liste des joueurs dans le voisinage de la case si 
   * c'est une instance d'usable
   * @param tile la case à éventuellement activer
   */
  public void activate(Tile tile) {
    if (tile instanceof Usable){
      Usable plate = ((Usable)tile);
      Position positionPlate = plate.getPosition();
      List<Player> players = this.getPlayersAround(positionPlate, plate.getRange());
      plate.action(players);
    }
  }

  public void setTile(Tile tile) {
    Position posTile = tile.getPosition();
    this.grid[posTile.getY()][posTile.getX()] = tile;
  }

  public Tile[][] getGrid() {
    return this.grid;
  }

  public List<Player> getPlayerList() {
    return this.players;
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
    String res = "";
    for (int j = 0; j < this.grid.length; j++) {
      for (int i = 0; i < this.grid[0].length; i++) {
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
