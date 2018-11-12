
package gui;

import java.awt.Dimension;
import javax.swing.*;
import game.*;

public class GUI extends JFrame {
  
  public GUI(Game game) {
    this.setTitle("Mystic Robot");
    
    ViewGrid view = new ViewGrid(game);
    this.add(view);
    
    pack();
    
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
