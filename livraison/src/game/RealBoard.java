package game;

import java.util.*;

public class RealBoard implements Board {
  private Tile[][] grid;
  private ArrayList<Player> players = new ArrayList<>();;
  private RobotFactory factory;
  private int width;
  private int height;

  public RealBoard(int width, int height, int nbPlayer, RobotFactory factory){
    this.factory = factory;
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

    int xPlate;
    int yPlate;
    for (int j = 0; j < nbEnergy; j++) {
      xPlate = r.nextInt(width);
      yPlate = r.nextInt(height);
      Tile energyPlate = new EnergyPlate(new Position(xPlate, yPlate), true, 0, 5);
      this.setTile(energyPlate);
    }

    int xBomb;
    int yBomb;
    for (int k = 0; k < nbEnergy; k++) {
      xBomb = r.nextInt(width);
      yBomb = r.nextInt(height);
      Tile bomb = new ExplosifPlate(new Position(xBomb, yBomb), true, 1, new Bomb(r.nextInt(10)+1,2), testPb);
      this.setTile(bomb);
    }

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
    return !(this.isObstacleTile(position));
  }

  public void move(Player player, Position deplacement) {
    Position new_pos = new Position(deplacement.getX()+player.getPosition().getX(),deplacement.getY()+player.getPosition().getY());
    if (this.canMove(new_pos)){
      Tile tileTarget = this.grid[new_pos.getY()][new_pos.getX()];
      Position positionPlayer = player.getPosition();

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

  public ArrayList<Player> getPlayerList() {
    return this.players;
  }

  public boolean isInIndex(Position position) {
    int x = position.getX();
    int y = position.getY();
    return this.isInIndex(x, y);
  }

  public boolean isInIndex(int x, int y) {
    return 0 <= x && x < this.width && 0 <= y && y < this.height;
  }

  public List<Tile> consvois(Position position, int size) {
    List<Tile> listConsvois = new ArrayList<>();
    int posX = position.getX();
    int posY = position.getY();
    for (int j = posY-size; j < (posY + size + 1); j++) {
      for (int i = posX-size; i < (posX + size + 1); i++) {
        if (this.isInIndex(i, j)) {
          listConsvois.add(this.grid[j][i]);
        }
      }
    }
    return listConsvois;
  }

  public List<Player> getPlayersInList(List<Tile> listTiles) {
    List<Player> listPlayer = new ArrayList<>();
    for (Tile tile : listTiles) {
      if (tile instanceof Player) {
        listPlayer.add(((Player) tile));
      }
    }
    return listPlayer;
  }

  public List<Player> getPlayersAround(Position position, int range) {
    List<Tile> voisins = this.consvois(position, range);
    return this.getPlayersInList(voisins);
  }

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
    for (Player player : this.players) {
      res += player.getStringStats() + "\n";
    }
    return res;
  }
}
