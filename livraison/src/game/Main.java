package game;

import java.util.*;
import java.io.*;

public class Main{

  public static void main(String[] args) {
    RobotFactory factory = new RobotFactory();
    RealBoard b = new RealBoard(20,20,1,factory);
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
    b.setTile(new EnergyPlate(new Position(2,3), true, 5));
    b.setTile(new EnergyPlate(new Position(3,4), true, 5));
    b.setTile(new EnergyPlate(new Position(7,10), true, 5));
    b.setTile(new EnergyPlate(new Position(8,2), true, 5));

    b.setTile(new ExplosifPlate(new Position(8,5), true, new Bomb(4,2), playerRobot));
    b.setTile(new ExplosifPlate(new Position(5,5), true, new Bomb(5,2), playerRobot));
    b.setTile(new ExplosifPlate(new Position(15,5), true, new Bomb(2,2), playerRobot));
    b.setTile(new ExplosifPlate(new Position(8,10), true, new Bomb(8,2), playerRobot));

    b.initPlayer(playerRobot);

    while(b.getPlayerList().size() != 1) {
      System.out.println(b);
      System.out.println(playerRobot.getStringStats());
      System.out.println("Action: up(z), down(s), left(q), right(d), quit(quit)");
      String nextAction = sc.nextLine();
      if (nextAction.equals("z")) {
        b.move(playerRobot, new Position(0,-1));
      } else if (nextAction.equals("q")) {
        b.move(playerRobot, new Position(-1,0));
      } else if (nextAction.equals("s")) {
        b.move(playerRobot, new Position(0,1));
      } else if (nextAction.equals("d")) {
        b.move(playerRobot, new Position(1,0));
      } else if (nextAction.equals("quit")) {
        break;
      } else {
        System.out.println("mauvaise entrée");
      }
      System.out.println(playerRobot.getPosition());
    }
  }
}
