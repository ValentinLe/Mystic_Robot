
package gui;

import javax.swing.*;
import java.awt.*;
import game.*;

public class ViewGrid extends JPanel {
  
  private Game game;
  private int tileSize;
  
  public ViewGrid(Game game) {
    this.game = game;
    this.tileSize = 50;
    this.setPreferredSize(new Dimension(this.game.getWidth()*this.tileSize, this.game.getHeight()*this.tileSize));
  }
  
  @Override
  public void paintComponent(Graphics g) {
    int width = this.game.getWidth();
    int height = this.game.getHeight();
    
    for (int j = 0; j<height; j++) {
      for (int i = 0; i<width; i++) {
        Tile tile = this.game.getTileAt(new Position(i,j));
        if (tile instanceof Player) {
          g.setColor(Color.YELLOW);
        } else if (tile instanceof EnergyPlate) {
          g.setColor(Color.GREEN);
        } else if (tile instanceof ExplosifPlate) {
          g.setColor(Color.RED);
        } else if (tile instanceof ShieldPlate) {
          g.setColor(Color.BLUE);
        } else {
          g.setColor(Color.GRAY);
        }
        g.fillRect(i*this.tileSize, j*this.tileSize, this.tileSize, this.tileSize);
      }
    }
  }
}