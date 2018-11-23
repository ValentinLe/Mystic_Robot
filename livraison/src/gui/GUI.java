
package gui;

import javax.swing.*;
import game.*;
import java.awt.*;
import java.awt.event.*;
import observer.*;
import space.Direction;

public class GUI extends JFrame implements ModelListener {
  
  private Game game;
  
  public GUI(Game game) {
    super("Mystic Robot");
    this.game = game;
    
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
        System.out.println("g " + game);
      }

      @Override
      public void keyReleased(KeyEvent e) {
        
      }
    });
    
    ViewGrid view = new ViewGrid(game);
    JTable table = new JTable(new ListPlayersToTableModelAdapter(game.getListPlayers()));
    table.setFocusable(false);
    JScrollPane tablePlayers = new ViewListPlayers(table);
    
    Container cp = this.getContentPane();
    cp.setLayout(new BorderLayout());
    cp.add(view, BorderLayout.WEST);
    cp.add(tablePlayers, BorderLayout.EAST);
    this.pack();
    
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void somethingHasChanged(Object source) {
    this.repaint();
  }
}
