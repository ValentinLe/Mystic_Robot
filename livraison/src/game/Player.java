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

  // savoir si un player peut bouger
  public Boolean canMove(Board board, Position deplacement){
    if (!(board.isObstacleTile(pos))){
      if (!(board.isPlayerOnPos(pos))){
        return true;
      }
    }
    return false;
  }

  public void move(Board board, Position deplacement) {
    Position new_pos = new Position(deplacement.getX()+this.pos.getX(),deplacement.getY()+this.pos.getY());
    if (this.canMove(board,deplacement)){
      this.pos=new_pos;
    }
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
