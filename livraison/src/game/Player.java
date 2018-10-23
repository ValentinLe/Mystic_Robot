package game;

import java.util.*;

public class Player extends AbstractTile {

  private Position position;
  private int energy;
  private boolean haveShield;
  private Map<Stuff, Integer> stuff;

  public Player(Position position, int energy, boolean haveShield, Map<Stuff,Integer> stuff) {
    super(position, true);
    this.energy = energy;
    this.haveShield = haveShield;
    this.stuff = stuff;
  }

  /*public void addEnergy(EnergyPlate amount) {
    this.energy.add(amount.getEnergyAmount());
  }*/

  public void use(Stuff item) {

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

  @Override
  public String toString() {
    return "p";
  }

}
