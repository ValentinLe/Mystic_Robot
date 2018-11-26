
package gui;

import game.*;
import parser.*;
import java.util.*;
import java.security.InvalidParameterException;

public class Main {
  public static void main(String[] args) {

    if (args.length != 2) {
      throw new InvalidParameterException("Need 2 parameters (number of robots, delai between players action). You gave " + args.length + " parameters.");
    }
    int nbRobots;
    int delai;

    try {
        nbRobots = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
        throw new InvalidParameterException("the first parameter must be an integer, you gave : " + args[0]);
    }

    try {
        delai = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
        throw new InvalidParameterException("the second parameter must be an integer, you gave : " + args[1]);
    }

    Parser parser = new ParserCrochet("ressources/configs/texture","ressources/configs/config");
    RobotFactory factory = new RobotFactory(parser.executeConfig(), nbRobots);
    GridGenerator gridGenerator = new GridGeneratorWithProbability(0.15, 0.15, 0.15);
    IA ia = new IARandom();
    List<Player> listPlayers = factory.createPlayerList();
    RealGame game = new RealGame(10,10,listPlayers , gridGenerator, ia);

    new GUI("Mystic Robot (proxy of first player)", new ProxyGame(game, game.getNextPlayer()), parser);
    new GUI("Mystic Robot (RealGame)", game, parser);
    game.loopIa(delai);
  }
}
