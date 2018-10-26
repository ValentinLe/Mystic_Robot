package game;

import java.util.*;

public class Main{

  public static void main(String[] args) {
    RobotFactory factory = new RobotFactory();
    Board b = new Board(20,20,2,factory);
    System.out.println(b);
    Map<Stuff,Integer> stuff = new HashMap<Stuff,Integer>();
    Stuff winchester = new Winchester();
    stuff.put(winchester,1);
  }
}
