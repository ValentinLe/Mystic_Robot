package game;

import java.util.*;
import java.io.*;

public class Main{

  public static void main(String[] args) {
    RobotFactory factory = new RobotFactory();
    RealBoard b = new RealBoard(10,10,8,factory);
    Scanner sc= new Scanner(System.in);
    /*
    System.out.println("Chose a class: tank(t), sniper(s), rocketman(r)");
    Player playerRobot = null;
    String playerClass = sc.nextLine();
    if (playerClass.equals("t")) {
      playerRobot = factory.createTank(new Position(0,0));
      b.initPlayer(playerRobot);
    } else if (playerClass.equals("s")) {
      playerRobot = factory.createSniper(new Position(0,0));
      b.initPlayer(playerRobot);
    } else if (playerClass.equals("r")) {
      playerRobot = factory.createRocketMan(new Position(0,0));
      b.initPlayer(playerRobot);
    }*/

    Player playerRobot = factory.createTank(new Position(0,0));

    b.initPlayer(playerRobot);

    while(b.getPlayerList().size() != 1) {
      // le print qui permet de stabiliser l'affichage
      System.out.println("\033[H\033[2J\n");
      System.out.println(b);
      
      System.out.println("up    " + b.getTileInDirection(playerRobot.getPosition(), Direction.UP, 3));
      System.out.println("down  " + b.getTileInDirection(playerRobot.getPosition(), Direction.DOWN, 3));
      System.out.println("right " + b.getTileInDirection(playerRobot.getPosition(), Direction.RIGHT, 3));
      System.out.println("left  " + b.getTileInDirection(playerRobot.getPosition(), Direction.LEFT, 3));
      
      System.out.println("Action: up(z), down(s), left(q), right(d), quit(quit)");
      String nextAction = sc.nextLine();
      if (nextAction.equals("z")) {
        b.move(playerRobot, Direction.UP);
      } else if (nextAction.equals("q")) {
        b.move(playerRobot, Direction.LEFT);
      } else if (nextAction.equals("s")) {
        b.move(playerRobot, Direction.DOWN);
      } else if (nextAction.equals("d")) {
        b.move(playerRobot, Direction.RIGHT);
      } else if (nextAction.equals("quit")) {
        break;
      } else {
        System.out.println("mauvaise entrée");
      }
      System.out.println(playerRobot.getPosition());
    }
  }
}
