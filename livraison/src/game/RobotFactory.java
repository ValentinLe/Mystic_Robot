package game;

import java.util.*;

public class RobotFactory {

  private Map<String,Map<String,String>> weaponConfig;
  private Map<String,Map<String,String>> robotConfig;

  private Map<String,Weapon> weaponList = new HashMap<String,Weapon>();
  private ArrayList<Player> robotList = new ArrayList<Player>();
  private ArrayList<Player> playerList = new ArrayList<Player>();

  /**
    * Constructeur de la classe qui fait appel aux méthodes createStuffs, createRobots et initPlayerList
    * @param config la configuration de la partie (les armes et les robots)
    * @param playerNumber le nombre de joueurs pour la partie
    */
  public RobotFactory(ArrayList<Map<String,Map<String,String>>> config, int playerNumber) {
    this.weaponConfig = config.get(0);
    this.robotConfig = config.get(1);

    this.createStuffs();
    this.createRobots();
    this.initPlayerList(playerNumber);
  }

  /**
    * Fonction qui génère une liste d'équipements à partir d'une config et qui l'ajoute à une variable globale
    */
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

  /**
    * Fonction qui génère une liste de robots à partir d'une config et qui l'ajoute à une variable globale
    */
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

  /**
    * Fonction qui génère autant de joueurs que voulus
    *@param playerNumber le nombre de joueurs (robots) pour la partie
    */
  public void initPlayerList(int playerNumber) {
    Random r = new Random();
    for (int i = 0; i < playerNumber; i++) {
      int choice = r.nextInt(this.robotList.size());
      Player robot = this.robotList.get(choice);
      this.playerList.add(new Player(robot.getType(), null, null, robot.getEnergy(), false, new HashMap(robot.getEquipement())));
    }
  }

  public ArrayList<Player> getPlayerList() {
    return this.playerList;
  }
}
