package game;

import java.util.*;

public class RobotFactory {

  private Player tank;
  private Player sniper;
  private Player rocketMan;

  private Map<Stuff,Integer> tankStuff = new HashMap<Stuff,Integer>();
  private Map<Stuff,Integer> sniperStuff = new HashMap<Stuff,Integer>();
  private Map<Stuff,Integer> rocketManStuff = new HashMap<Stuff,Integer>();

  public RobotFactory() {
    this.tankStuff.put(new Winchester(),4);
    this.tankStuff.put(new Mine(),2);
    this.tankStuff.put(new Bomb(5),2);
    this.sniperStuff.put(new Winchester(),20);
    this.rocketManStuff.put(new Winchester(),8);
    this.rocketManStuff.put(new Mine(),4);
    this.rocketManStuff.put(new Bomb(5),4);

    this.tank = new Player("Tank",null,30,false,this.tankStuff);
    this.sniper = new Player("Sniper",null,5,false,this.sniperStuff);
    this.rocketMan = new Player("Rocket Man",null,5,false,this.rocketManStuff);
  }

  public Player createTank(Position position) {
    Player newTank = new Player("Tank",position,30,false,this.tankStuff);
    return newTank;
  }

  public Player createSniper(Position position) {
    Player newSniper = new Player("Sniper",position,5,false,this.sniperStuff);
    return newSniper;
  }

  public Player createRocketMan(Position position) {
    Player newRocketMan = new Player("Rocket Man",position,5,false,this.rocketManStuff);
    return newRocketMan;
  }

  public ArrayList<Player> getRobotList() {
    ArrayList<Player> robotList = new ArrayList<Player>();
    robotList.add(tank);
    robotList.add(sniper);
    robotList.add(rocketMan);

    return robotList;
  }
}
