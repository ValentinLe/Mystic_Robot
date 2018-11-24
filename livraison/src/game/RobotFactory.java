package game;

import java.util.*;

public class RobotFactory {

  private Map<String,Map<String,String>> weaponConfig;
  private Map<String,Map<String,String>> robotConfig;

  private Map<String,Weapon> weaponList = new HashMap<String,Weapon>();
  private ArrayList<Player> robotList = new ArrayList<Player>();

  public RobotFactory(ArrayList<Map<String,Map<String,String>>> config) {
    this.weaponConfig = config.get(0);
    this.robotConfig = config.get(1);

    this.createStuffs();
    this.createRobots();
  }

  public void createStuffs() {
    Map<String,Weapon> weaponList = new HashMap<String,Weapon>();
    int damage;
    int range;
    for (String elementName : this.weaponConfig.keySet()) {
      Map<String,String> weapon = this.weaponConfig.get(elementName);
      if (weapon.get("type").equals("gun")) {
        damage = Integer.parseInt(weapon.get("damage"));
        range = Integer.parseInt(weapon.get("range"));
        Gun gun = new Gun(elementName,damage,range,null);
        weaponList.put(elementName,gun);
      } else if (weapon.get("type").equals("mine")) {
        damage = Integer.parseInt(weapon.get("damage"));
        range = Integer.parseInt(weapon.get("range"));
         Mine mine = new Mine(damage,range,null);
         weaponList.put(elementName,mine);
      } else if (weapon.get("type").equals("bomb")) {
        damage = Integer.parseInt(weapon.get("damage"));
        range = Integer.parseInt(weapon.get("range"));
        Bomb bomb = new Bomb(damage,range,Integer.parseInt(weapon.get("timer")),null);
        weaponList.put(elementName,bomb);
      }
    }
    this.weaponList = weaponList;
  }

  public void createRobots() {
    int life = 0;
    int ammo;
    Map<Equipement,Integer> stuff;
    for (String elementName : this.robotConfig.keySet()) {
      Map<String,String> elements = this.robotConfig.get(elementName);
      stuff = new HashMap<Equipement,Integer>();
      for (String weaponName : elements.keySet()) {
        if (weaponName.equals("life")) {
          life = Integer.parseInt(elements.get("life"));
        } else {
          ammo = Integer.parseInt(elements.get(weaponName));
          stuff.put(this.weaponList.get(weaponName),ammo);
        }
      }
      Player robot = new Player(elementName,null,null,life,false,stuff);
      this.robotList.add(robot);
    }
  }

  public ArrayList<Player> getRobotList() {
    return this.robotList;
  }
}
