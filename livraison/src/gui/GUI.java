
package gui;

import javax.swing.*;
import game.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import observer.*;
import space.Direction;
import parser.*;

public class GUI extends JFrame implements ModelListener {

  private Game game;

  public GUI(Game game) {
    super("Mystic Robot");
    this.game = game;
    /*
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
        Player currentPlayer = game.getNextPlayer();
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          currentPlayer.move(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          currentPlayer.move(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          currentPlayer.move(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          currentPlayer.move(Direction.RIGHT);
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });
    */

    ViewGrid view = new ViewGrid(game, new ParserCrochet("texture", "config"));
    JTable tableP = new JTable(new ListPlayersToTableModelAdapter(game));
    tableP.setFocusable(false);
    JScrollPane tablePlayers = new ViewJTable(tableP);

    JButton bRestart = new JButton("Restart");
    bRestart.setFocusable(false);
    bRestart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        game.restart();
      }
    });

    JTable tableE = new JTable(new EquipementMapToTableModelAdapter(game));
    tableE.setFocusable(false);
    JScrollPane tableEquipement = new ViewJTable(tableE);

    JPanel zoneTable = new JPanel();
    zoneTable.setLayout(new GridBagLayout());
    GridBagConstraints gcz = new GridBagConstraints();
    zoneTable.add(tablePlayers, gcz);
    gcz.gridy = 1;
    zoneTable.add(tableEquipement, gcz);

    Container cp = this.getContentPane();
    cp.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    cp.add(view, gc);
    gc.gridy = 1;
    cp.add(bRestart, gc);
    gc.gridy = 0;
    gc.gridx = 1;
    cp.add(zoneTable, gc);
    this.pack();

    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void somethingHasChanged(Object source) {
    this.repaint();
  }
}
