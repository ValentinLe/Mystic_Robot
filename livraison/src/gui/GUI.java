
package gui;

import javax.swing.*;
import game.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import observer.*;
import space.Direction;
import parser.*;

/**
  * Fenetre principale de l'application contenant la grille du jeu et 2
  * table sur les joueurs et leurs equipements
  */
public class GUI extends JFrame implements ModelListener {

  private Game game;

  /**
    * Construit la fenetre
    * @param title le tire de la fenetre
    * @param game le jeu que la fenetre doit representer
    * @param parser le parser pour recuperer les textures des elements pour la grille
    */
  public GUI(String title, Game game, Parser parser) {
    super(title);
    this.game = game;

    ViewGrid view = new ViewGrid(game, parser);
    JTable tableP = new JTable(new ListPlayersToTableModelAdapter(game));
    tableP.setFocusable(false);
    JScrollPane tablePlayers = new ViewJTable(tableP);

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
    gc.gridx = 1;
    cp.add(zoneTable, gc);
    this.pack();

    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  /**
    * repaint la fenetre
    * @param source la source
    */
  @Override
  public void somethingHasChanged(Object source) {
    this.repaint();
  }
}
