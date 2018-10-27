package game;

import java.util.*;

public class Player extends AbstractTile {

  private String name;
  private Position position;
  private int energy;
  private boolean haveShield;
  private Map<Equipement, Integer> equipement;

  public Player(String name, Position position, int energy, boolean haveShield, Map<Equipement,Integer> equipement) {
    super(position, true);
    this.name = name;
    this.energy = energy;
    this.haveShield = haveShield;
    this.equipement = equipement;
  }

  /*public void addEnergy(EnergyPlate amount) {
    this.energy.add(amount.getEnergyAmount());
  }*/

  public void use(Equipement item) {

  }

  // add + ou - de l'énergie
  public void addEnergy(int i){
    this.energy+=i;
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

  @Override
  public String toString() {
    return "p";
  }

}
