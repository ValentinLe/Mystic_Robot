
package gui;

import game.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import observer.ModelListener;

/**
 * adapter de la liste player a une table model
 * 
 */
public class ListPlayersToTableModelAdapter extends AbstractTableModel implements ModelListener {

  private final static int NB_CHAMPS = 4;

  // On peut changer l'ordre des colonnes en permuttant les valeurs 0, 1 et 2 ci-dessous :
  private final static int TOUR = 0;
  private final static int NOM = 1;
  private final static int ENERGY = 2;
  private final static int SHIELD = 3;

  private final static String[] COL_NAME;

  static {
    COL_NAME = new String[NB_CHAMPS];
    COL_NAME[TOUR] = "Tour";
    COL_NAME[NOM] = "Name";
    COL_NAME[ENERGY] = "Energy";
    COL_NAME[SHIELD] = "Shield";
  }

  private Game game;
  private List<Player> listPlayers;

  /**
   * creer une instance de l'adapter
   * @param game 
   */
  public ListPlayersToTableModelAdapter(Game game) {
    this.game = game;
    this.listPlayers = new ArrayList<>(game.getListPlayers());
    for (Player player : this.listPlayers) {
      // ecoute tous les joueurs
      player.addModelListener(this);
    }
  }

  /**
   * recupere le nombre de lignes
   * @return le nombre de lignes
   */
  @Override
  public int getRowCount() {
    return listPlayers.size();
  }

  /**
   * recupere le nombre de colonnes
   * @return le nombre de colonnes
   */
  @Override
  public int getColumnCount() {
    return NB_CHAMPS;
  }

  /**
   * recupere la valeur en (rowIndex,columnIndex) de la table
   * @param rowIndex la ligne
   * @param columnIndex la colonne
   * @return la valeur de la cellule
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Player player = this.listPlayers.get(rowIndex);
    switch(columnIndex) {
      case TOUR:
        if (player == this.game.getNextPlayer()) {
          // si c'est le joueur en train de jouer
          return "----->";
        } else if (player.isDead()) {
          return "Dead";
        } else {
          return "";
        }
      case NOM:
        return player.getType();
      case ENERGY:
        return player.getEnergy() + " / " + player.getMaxEnergy();
      case SHIELD:
        return player.getShield();
    }
    return null;
  }

  /**
   * recupere le nom de la colonne
   * @param col l'index de la colonne
   * @return le nom de la colonne
   */
  @Override
  public String getColumnName(int col) {
    return COL_NAME[col];
  }
  
  /**
   * dis a la table de s'actualiser
   * @param source la source
   */
  @Override
  public void somethingHasChanged(Object source) {
    fireTableDataChanged();
  }
}
