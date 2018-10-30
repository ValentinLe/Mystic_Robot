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
    
    boolean saisie = true;
    Player player = null;

    while(b.getPlayerList().size() != 1 || !saisie) {
      // le print qui permet de stabiliser l'affichage
      System.out.println("\033[H\033[2J\n");
      player = b.getNextPlayer();
      System.out.println(b);
      System.out.println("Action: up(z), down(s), left(q), right(d), quit(quit)");
      saisie = false;
      String nextAction = sc.nextLine();
      if (nextAction.equals("z")) {
        saisie = b.move(player, Direction.UP);
      } else if (nextAction.equals("q")) {
        saisie = b.move(player, Direction.LEFT);
      } else if (nextAction.equals("s")) {
        saisie = b.move(player, Direction.DOWN);
      } else if (nextAction.equals("d")) {
        saisie = b.move(player, Direction.RIGHT);
      } else if (nextAction.equals("quit")) {
        return;
      } else {
        saisie = false;
        System.out.println("mauvaise entrée");
      }
      
      System.out.println(player.getPosition());
    }
  }
}
