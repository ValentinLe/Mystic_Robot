
package gui;

import game.*;
import parser.*;

public class Main {
  public static void main(String[] args) {
    Parser parser = new ParserCrochet("texture","config");
    RobotFactory factory = new RobotFactory(parser.executeConfig());
    RealGame game = new RealGame(10,10,2,factory.getRobotList());
    ProxyGame proxy = new ProxyGame(game, game.getNextPlayer());

    System.out.println(game);
    System.out.println(proxy);
    new GUI(game);
    new GUI(proxy);
  }
}
