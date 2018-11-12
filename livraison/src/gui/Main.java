
package gui;

import game.*;

public class Main {
  public static void main(String[] args) {
    RobotFactory factory = new RobotFactory();
    RealGame game = new RealGame(10,10,2,factory.getRobotList());
    ProxyGame proxy = new ProxyGame(game, game.getNextPlayer());
   
    System.out.println(game);
    System.out.println(proxy);
    new GUI(game);
  }
}
