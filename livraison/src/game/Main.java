package game;

import java.util.*;
import java.io.*;

public class Main{

  public static void main(String[] args) {
    RobotFactory factory = new RobotFactory();
    RealBoard b = new RealBoard(10,10,2,factory);
    Scanner sc= new Scanner(System.in);
    Scanner sc2= new Scanner(System.in);
    Scanner sc3= new Scanner(System.in);

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
    boolean action = false;

    while(b.getPlayerList().size() != 1 || !saisie) {
      // le print qui permet de stabiliser l'affichage
      System.out.println("\033[H\033[2J\n");
      System.out.println(b);
      System.out.println("Action: up(z), down(s), left(q), right(d), don't use the function use(u), quit(quit)");
      saisie = false;
      action = false;
      String nextAction = sc.nextLine();
      if (nextAction.equals("z")) {
        saisie = b.move(Direction.UP);
      } else if (nextAction.equals("q")) {
        saisie = b.move(Direction.LEFT);
      } else if (nextAction.equals("s")) {
        saisie = b.move(Direction.DOWN);
      } else if (nextAction.equals("d")) {
        saisie = b.move(Direction.RIGHT);
      } else if (nextAction.equals("u")){
        System.out.println("no jamy, why did you do that !!!!");
        int cpt = 0;
        Map<Equipement,Integer> stuff = b.getPlayerEquipement();
        ArrayList<Equipement> equipementListe = new ArrayList<>();
        System.out.println("list of stuf : \n");
        for (Equipement e : stuff.keySet()){
          System.out.println(cpt + " : " + e + " = " + stuff.get(e));
          equipementListe.add(e);
        }

        System.out.println("choose an item to use : \n");
        int nextAction2 = Integer.parseInt(sc2.nextLine());
        if (nextAction2>=0 && nextAction2<stuff.keySet().size()){
          Direction direction = null;
          System.out.println("direction (z,q,s,d)");
          String nextAction3 = sc3.nextLine();
          if (nextAction3.equals("z")) {
            direction = Direction.UP;
          } else if (nextAction3.equals("q")) {
            direction = Direction.LEFT;
          } else if (nextAction3.equals("s")) {
            direction = Direction.DOWN;
          } else if (nextAction3.equals("d")) {
            direction = Direction.RIGHT;
          }
          System.out.println("\n");
          action = b.playerUseItem(equipementListe.get(nextAction2),direction);
          System.out.println("use : "+action);
        }
      } else if (nextAction.equals("quit")) {
        return;
      } else {
        saisie = false;
        System.out.println("mauvaise entrée");
      }
    }
  }
}
