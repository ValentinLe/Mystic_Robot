
package gui;

import game.*;
import parser.*;
import java.util.*;


public class Main {
  public static void main(String[] args) {
    Parser parser = new ParserCrochet("texture","config");
    RobotFactory factory = new RobotFactory(parser.executeConfig(), 10);
    GridGenerator gridGenerator = new GridGeneratorWithProbability(0.15, 0.05, 0.05);
    IA ia = new IARandom();
    List<Player> listPlayers = factory.createPlayerList();
    RealGame game = new RealGame(10,10,listPlayers , gridGenerator, ia);

    new GUI(game, factory);
    /*
    for (Player pl : listPlayer) {
      new GUI(new ProxyGame(game, pl));
    }*/
    game.loopIa();
  }
}
