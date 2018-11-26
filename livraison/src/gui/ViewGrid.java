
package gui;

import space.Position;
import javax.swing.*;
import java.awt.*;
import game.*;
import observer.*;
import parser.*;
import java.util.*;

/**
 * vue sur la grille du jeu
 *
 */
public class ViewGrid extends JPanel implements ModelListener {

  private Game game;
  private Parser parser;
  private Map<String, Image> textures;
  private int tileSize;

  /**
   * creer une instance de la vue
   * @param game le jeu a representer
   * @param parser le parser qui permettre de recuperer les textures
   */
  public ViewGrid(Game game, Parser parser) {
    this.game = game;
    game.addModelListener(this);
    this.tileSize = 50;
    this.parser = parser;
    this.textures = this.buildTextures(parser);
    this.setPreferredSize(new Dimension(this.game.getWidth()*this.tileSize, this.game.getHeight()*this.tileSize));
  }

  /**
   * creer une map avec (nomElement : image correspondant)
   * @param parser le parser a utiliser
   * @return la map creer
   */
  private Map<String, Image> buildTextures(Parser parser) {
    Map<String, Image> mapTextures = new HashMap<>();
    Map<String, String> mapPathTextures = parser.executeTexture();
    Image image;
    for (String nameTexture : mapPathTextures.keySet()) {
      image = Toolkit.getDefaultToolkit().getImage(mapPathTextures.get(nameTexture));
      mapTextures.put(nameTexture, image);
    }
    return mapTextures;
  }

  /**
   * paint l'image au coordonnee donnee (coordonnees de grille pas du canvas)
   * @param g l'endroit ou le dessiner
   * @param image l'image a dessiner
   * @param x l'abssisce de la case a dessiner
   * @param y l'ordonnee de la case a dessiner
   */
  private void paintImage(Graphics g, Image image, int x, int y) {
    g.drawImage(image,this.tileSize*x,this.tileSize*y,this.tileSize,this.tileSize,this);
  }

  /**
   * paint la grille
   * @param g le support de dessin
   */
  @Override
  public void paintComponent(Graphics g) {
    Player playerPlaying = this.game.getNextPlayer();
    int width = this.game.getWidth();
    int height = this.game.getHeight();
    for (int j = 0; j<height; j++) {
      for (int i = 0; i<width; i++) {
        Tile tile = this.game.getTileAt(new Position(i,j));
        paintImage(g, textures.get("emptyTile"), i, j);
        if (tile instanceof Player) {
          Player player = (Player)tile;
          if (player.getShield()) {
            // si le joueur a un bouclier on dessine le bouclier avant
            paintImage(g, textures.get("shield"), i, j);
          }
          if (player == playerPlaying) {
            // test de référence pas d'utilisation de equals
            // joueur en train de jouer
            paintImage(g, textures.get("robotPlay"), i, j);
          } else {
            // joueur qui ne joue pas
            paintImage(g, textures.get("robot"), i, j);
          }
        } else if (tile instanceof Wall) {
          paintImage(g, textures.get("wall"), i, j);
        } else if (tile instanceof EnergyPlate) {
          paintImage(g, textures.get("energy"), i, j);
        } else if (tile instanceof ExplosifPlate) {
          if (((ExplosifPlate)tile).isBomb()) {
            paintImage(g, textures.get("bomb"), i, j);
          } else {
            paintImage(g, textures.get("mine"), i, j);
          }
        } else if (tile instanceof ShieldPlate) {
          paintImage(g, textures.get("shield"), i, j);
        }
      }
    }
  }

  /**
   * update la grille
   * @param source la source
   */
  @Override
  public void somethingHasChanged(Object source) {
    this.repaint();
  }
}
