
package gui;

import space.Position;
import javax.swing.*;
import java.awt.*;
import game.*;
import observer.*;

public class ViewGrid extends JPanel implements ModelListener {
  
  private Game game;
  private int tileSize;
  
  public ViewGrid(Game game) {
    this.game = game;
    game.addModelListener(this);
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
        } else if (tile instanceof Wall) {
          g.setColor(Color.BLACK);
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

  @Override
  public void somethingHasChanged(Object source) {
    this.repaint();
  }
}