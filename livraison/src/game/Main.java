package game;

import java.util.*;

public class Main{

  public static void main(String[] args) {
    Board b = new Board(10,10);
    System.out.println(b);
    Map<Stuff,Integer> stuff = new HashMap<Stuff,Integer>();
    Stuff winchester = new Winchester();
    stuff.put(winchester,1);
    Player p1 = new Player(new Position(5,5), 100, false, stuff);
    b.setPlayerPositions(p1);
    System.out.println(b);
  }
}
