package game;

import java.util.*;

public class Player implements Tile {

  private Position pos;
  private int energy;
  private boolean haveShield;
  private Map<Stuff, Integer> stuff;

  public Player(Position pos, int energy, boolean haveShield, Map<Stuff,Integer> stuff) {
    this.pos = pos;
    this.energy = energy;
    this.haveShield = haveShield;
    this.stuff = stuff;
  }

  /*public void addEnergy(EnergyPlate amount) {
    this.energy.add(amount.getEnergyAmount());
  }*/

  public void move(Position pos) {

  }

  public void use(Stuff item) {

  }

  @Override
  public Position getPosition() {
    return this.pos;
  }

  @Override
  public String toString() {
    return "p";
  }

}
