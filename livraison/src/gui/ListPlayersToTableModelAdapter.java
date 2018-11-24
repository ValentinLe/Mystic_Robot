
package gui;

import game.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import observer.ModelListener;

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

  public ListPlayersToTableModelAdapter(Game game) {
    this.game = game;
    this.listPlayers = new ArrayList<>(game.getListPlayers());
    for (Player player : this.listPlayers) {
      player.addModelListener(this);
    }
  }

  @Override
  public int getRowCount() {
    return listPlayers.size();
  }

  @Override
  public int getColumnCount() {
    return NB_CHAMPS;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Player player = this.listPlayers.get(rowIndex);
    switch(columnIndex) {
      case TOUR:
        return (player == this.game.getNextPlayer() ? "---->" : "");
      case NOM:
        return player.getType();
      case ENERGY:
        return player.getEnergy() + " / " + player.getMaxEnergy();
      case SHIELD:
        return player.getShield();
    }
    return null;
  }

  @Override
  public String getColumnName(int col) {
    return COL_NAME[col];
  }

  @Override
  public void somethingHasChanged(Object source) {
    fireTableDataChanged();
  }
}
