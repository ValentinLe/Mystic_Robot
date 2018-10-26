package game;

import java.util.*;

public class Player extends AbstractTile {

  private String name;
  private Position position;
  private int energy;
  private boolean haveShield;
  private Map<Stuff, Integer> stuff;

  public Player(String name, Position position, int energy, boolean haveShield, Map<Stuff,Integer> stuff) {
    super(position, true);
    this.name = name;
    this.energy = energy;
    this.haveShield = haveShield;
    this.stuff = stuff;
  }

  /*public void addEnergy(EnergyPlate amount) {
    this.energy.add(amount.getEnergyAmount());
  }*/

  public void use(Stuff item) {

  }

  public void setPosition(Position posiiton) {
    this.position = position;
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

  @Override
  public String toString() {
    return "p";
  }

}
