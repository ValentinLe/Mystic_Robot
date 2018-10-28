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
      // nouvelle position pour éviter les problèmes de référrence
      Position p = new Position(player.getPosition().getX(),player.getPosition().getY());
      // création d'une nouvelle empty_tile pour remplacer l'emplacement du joueur
      Tile new_tile = new EmptyTile(p);
      // positionnement de la nouvelle empty_tile dans le board
      this.setTile(new_tile);
      // changement de l'emplacement du joueur
      player.setPosition(new_pos);

      // active le terrain sur lequelle le joueurva se déplacer
      this.activate(player, new_pos);


      // positionnement du joueur dans le board
      this.setTile(player);
    }
  }

  public void activate(Player player, Position positionTerrain){
    Tile tile = this.grid[positionTerrain.getY()][positionTerrain.getX()];
    if (tile instanceof Usable){
      ((Usable)tile).action(player);
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
