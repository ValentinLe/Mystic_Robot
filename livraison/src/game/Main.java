package game;

import space.Direction;
import java.util.*;
import java.io.*;
import parser.*;

public class Main{

  public static void main(String[] args) {
    Parser parser = new ParserCrochet("texture","config");
    ArrayList<Map<String,Map<String,String>>> config = parser.executeConfig();

    RobotFactory factory = new RobotFactory(config);

    Scanner sc= new Scanner(System.in);
    Scanner sc2= new Scanner(System.in);
    Scanner sc3= new Scanner(System.in);

    boolean saisie = true;
    boolean action = false;

    Player currentPlayer;

    GridGenerator gridGenerator = new GridGeneratorWithProbability(0.15, 0.05, 0.05);

    RealGame b = new RealGame(10,10,factory.getRobotList(), gridGenerator);
    while(!(b.isOver()) || !saisie) {
      // le print qui permet de stabiliser l'affichage
      System.out.println("\033[H\033[2J\n");
      System.out.println(b);
      System.out.println("Action: up(z), down(s), left(q), right(d), skip(p), use(u), quit(quit)");
      currentPlayer = b.getNextPlayer();
      saisie = false;
      action = false;
      String nextAction = sc.nextLine();
      if (nextAction.equals("z")) {
        saisie = currentPlayer.move(Direction.UP);
      } else if (nextAction.equals("q")) {
        saisie = currentPlayer.move(Direction.LEFT);
      } else if (nextAction.equals("s")) {
        saisie = currentPlayer.move(Direction.DOWN);
      } else if (nextAction.equals("d")) {
        saisie = currentPlayer.move(Direction.RIGHT);
      } else if (nextAction.equals("p")) {
        b.skipTurn();
      } else if (nextAction.equals("u")){
        int cpt = 0;
        Map<Equipement,Integer> stuff = b.getPlayerEquipement();
        ArrayList<Equipement> equipementListe = new ArrayList<>();
        System.out.println("list of stuf : \n");
        for (Equipement e : stuff.keySet()){
          String res = cpt++ + " : " + e + " = " + stuff.get(e);
          if (e instanceof Weapon) {
            res +=" range = "+ ((Weapon)e).getRange();
          }
          System.out.println(res);
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
    System.out.println("Bravo, le joueur "+b.getListPlayers().get(0)+" a gagné !");
  }
}
