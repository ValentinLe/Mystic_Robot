package game;

import java.util.*;

public class RobotFactory {

  private Player tank;
  private Player sniper;
  private Player rocketMan;

  private Map<Equipement,Integer> tankStuff = new HashMap<Equipement,Integer>();
  private Map<Equipement,Integer> sniperStuff = new HashMap<Equipement,Integer>();
  private Map<Equipement,Integer> rocketManStuff = new HashMap<Equipement,Integer>();

  public RobotFactory() {
    this.tankStuff.put(new Shotgun(),6);
    this.tankStuff.put(new Mine(),2);
    this.tankStuff.put(new Bomb(5),2);
    this.sniperStuff.put(new Winchester(),20);
    this.rocketManStuff.put(new Winchester(),8);
    this.rocketManStuff.put(new Mine(),4);
    this.rocketManStuff.put(new Bomb(5),4);

    this.tank = new Player("Tank",null,null,20,false,this.tankStuff);
    this.sniper = new Player("Sniper",null,null,10,false,this.sniperStuff);
    this.rocketMan = new Player("RocketMan",null,null,10,false,this.rocketManStuff);
  }

  public ArrayList<Player> getRobotList() {
    ArrayList<Player> robotList = new ArrayList<Player>();
    robotList.add(tank);
    robotList.add(sniper);
    robotList.add(rocketMan);

    return robotList;
  }
}
