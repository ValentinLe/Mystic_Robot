package game;

import java.util.*;

public class Player extends AbstractTile {

  public final static int MAX_ENERGY = 20;

  private String name;
  private int energy;
  private boolean hasShield;
  private Map<Equipement, Integer> equipement;

  public Player(String name, Position position, int energy, boolean hasShield, Map<Equipement,Integer> equipement) {
    super(position, true);
    this.name = name;
    this.energy = energy;
    this.hasShield = hasShield;
    this.equipement = equipement;
  }

  public void use(Equipement item) {

  }

  // add + de l'énergie
  public void addEnergy(int energy){
    this.energy += energy;
    if (this.energy > MAX_ENERGY) {
      this.energy = MAX_ENERGY;
    }
  }

  // applique les dommages au joueur selon si il a un bouclier
  public void applyDamage(int damage) {
    if (this.hasShield) {
      this.hasShield = false;
    } else {
      this.energy -= damage;
      if (this.energy < 0) {
        this.energy = 0;
      }
    }
  }

  public void move(Position deplacement) {
    int posX = deplacement.getX() + this.position.getX();
    int posY = deplacement.getY() + this.position.getY();
    this.position.setX(posX);
    this.position.setY(posY);
  }

  public int getEnergy(){
    return this.energy;
  }

  @Override
  public boolean getIsObstacle() {
    return true;
  }

  public String getName() {
    return this.name;
  }

  public Map<Equipement,Integer> getEquipement() {
    return this.equipement;
  }

  public String getStringStats() {
    return "Player " + this.name + " " + this.position + " : energy=" + this.energy + "/" + MAX_ENERGY + " shield=" + this.hasShield;
  }

  @Override
  public String toString() {
    return "p";
  }

}
