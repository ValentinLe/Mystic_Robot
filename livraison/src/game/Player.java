package game;

import java.util.*;

public class Player implements Tile {

  private Position position;
  private int energy;
  private boolean haveShield;
  private Map<Stuff, Integer> stuff;

  public Player(Position pos, int energy, boolean haveShield, Map<Stuff,Integer> stuff) {
    this.position = postion;
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
      return true;
    }
    return false;
  }

  public void move(Board board, Position deplacement) {
    Position new_pos = new Position(deplacement.getX()+this.position.getX(),deplacement.getY()+this.position.getY());
    if (this.canMove(board,deplacement)){
      board.getTile(this.position).setIsObstacle(false);
      this.position=new_pos;
      board.getTile(this.position).setIsObstacle(true);
    }
  }

  public void use(Stuff item) {

  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public boolean isObstacle() {
    return true;
  }

  @Override
  public String toString() {
    return "p";
  }

}
