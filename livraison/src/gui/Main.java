
package gui;

import game.*;
import parser.*;
import java.util.*;


public class Main {
  public static void main(String[] args) {
    Parser parser = new ParserCrochet("texture","config");
    RobotFactory factory = new RobotFactory(parser.executeConfig(), 3);
    GridGenerator gridGenerator = new GridGeneratorWithProbability(0.15, 0.05, 0.05);
    IA ia = new IARandom();
    RealGame game = new RealGame(10,10,factory.getPlayerList(), gridGenerator, ia);

    new GUI(game);
    for (Player pl : game.getListPlayers()) {
      new GUI(new ProxyGame(game, pl));
    }
    Main.loopIa(game);
  }

  public static void loopIa(RealGame game) {
    while (!game.isOver()) {
      game.iaExecute();
      try {
        Thread.sleep(1000);
      } catch(InterruptedException e) {

      }
    }
  }
}
