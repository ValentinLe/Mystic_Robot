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
    initGrid(nbPlayer);
  }

  public void generateGrid() {
    this.grid = new Tile[this.height][this.width];
    for (int j = 0; j < this.height; j++) {
      for (int i = 0; i < this.width; i++) {
        this.setTile(new EmptyTile(new Position(i,j)));
      }
    }
  }

  public void initGrid(int nbPlayer) {
    this.generateGrid();
    Random r = new Random();
    int xPlayer;
    int yPlayer;
    int choseRobot;
    for (int i = 0; i < nbPlayer; i++) {
      xPlayer = r.nextInt(width);
      yPlayer = r.nextInt(height);
      choseRobot = r.nextInt(this.factory.getRobotList().size());
      Player newPlayer = this.factory.getRobotList().get(choseRobot);
      if (newPlayer.getName().equals("Tank")) {
        newPlayer = this.factory.createTank(new Position(xPlayer,yPlayer));
      } else if (newPlayer.getName().equals("Sniper")) {
        newPlayer = this.factory.createSniper(new Position(xPlayer,yPlayer));
      } else {
        newPlayer = this.factory.createRocketMan(new Position(xPlayer,yPlayer));
      }
      this.initPlayer(newPlayer);
    }
  }

  public void initPlayer(Player player) {
    if (this.canMove(player.getPosition())) {
      setTile(player);
      //this.grid[player.getPosition().getX()][player.getPosition().getY()] = player;
    } else {
      throw new RuntimeException("Cette case n'est pas vide");
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
    return 0 <= x && x < this.width && 0 <= y && y < this.height;
  }

  public List<Tile> consvois(Position position, int size) {
    List<Tile> listConsvois = new ArrayList<>();
    int posX = position.getX();
    int posY = position.getY();
    for (int j = posY-size; j < (posY + size + 1); j++) {
      for (int i = posX-size; i < (posX + size + 1); i++) {
        if (this.isInIndex(new Position(i, j))) {
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
    return res;
  }
}
