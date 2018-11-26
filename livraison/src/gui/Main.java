
package gui;

import game.*;
import parser.*;
import java.util.*;


public class Main {
  public static void main(String[] args) {
    Parser parser = new ParserCrochet("texture","config");
    RobotFactory factory = new RobotFactory(parser.executeConfig(), 20);
    GridGenerator gridGenerator = new GridGeneratorWithProbability(0.15, 0.15, 0.15);
    IA ia = new IARandom();
    List<Player> listPlayers = factory.createPlayerList();
    RealGame game = new RealGame(10,10,listPlayers , gridGenerator, ia);

    new GUI("Mystic Robot (proxy of first player)", new ProxyGame(game, game.getNextPlayer()));
    new GUI("Mystic Robot (RealGame)", game);
    game.loopIa(500);
  }
}
