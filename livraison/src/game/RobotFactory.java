package game;

import java.util.*;

public class RobotFactory {

  private Map<String,Map<String,String>> config;

  private Player tank;
  private Player sniper;
  private Player rocketMan;

  private Map<Equipement,Integer> tankStuff = new HashMap<Equipement,Integer>();
  private Map<Equipement,Integer> sniperStuff = new HashMap<Equipement,Integer>();
  private Map<Equipement,Integer> rocketManStuff = new HashMap<Equipement,Integer>();

  public RobotFactory(Map<String,Map<String,String>> config) {
    this.config = config;

    this.tank = new Player("Tank",null,null,20,false,this.tankStuff);
    this.sniper = new Player("Sniper",null,null,10,false,this.sniperStuff);
    this.rocketMan = new Player("RocketMan",null,null,10,false,this.rocketManStuff);

    this.createStuffs();
  }

  public void createStuffs() {
    Map<String,Weapon> weaponList = new HashMap<String,Weapon>();
    int damage;
    int range;
    for (String elementName : this.config.keySet()) {
      Map<String,String> weapon = this.config.get(elementName);
      if (weapon.get("type").equals("gun")) {
        damage = Integer.parseInt(weapon.get("damage"));
        range = Integer.parseInt(weapon.get("range"));
        Gun gun = new Gun(elementName,damage,range,null);
        weaponList.put(elementName,gun);
      } else if (weapon.get("type").equals("mine")) {
        damage = Integer.parseInt(weapon.get("damage"));
        range = Integer.parseInt(weapon.get("range"));
         Mine mine = new Mine(elementName,damage,range,null);
         weaponList.put(elementName,mine);
      } else if (weapon.get("type").equals("bomb")) {
        damage = Integer.parseInt(weapon.get("damage"));
        range = Integer.parseInt(weapon.get("range"));
        Bomb bomb = new Bomb(elementName,damage,range,Integer.parseInt(weapon.get("timer")),null);
        weaponList.put(elementName,bomb);
      }
    }

    this.tankStuff.put(weaponList.get("shotgun"),8);
    this.tankStuff.put(weaponList.get("mine"),2);

    this.sniperStuff.put(weaponList.get("winchester"),10);
    this.sniperStuff.put(weaponList.get("mine"),2);

    this.rocketManStuff.put(weaponList.get("winchester"),5);
    this.rocketManStuff.put(weaponList.get("mine"),5);
    this.rocketManStuff.put(weaponList.get("bomb"),5);
  }

  public ArrayList<Player> getRobotList() {
    ArrayList<Player> robotList = new ArrayList<Player>();
    robotList.add(tank);
    robotList.add(sniper);
    robotList.add(rocketMan);

    return robotList;
  }
}
