
package gui;

import game.Player;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import observer.ModelListener;
import space.Position;

public class ListPlayersToTableModelAdapter extends AbstractTableModel implements ModelListener {
  
  private final static int NB_CHAMPS = 4;
    
  // On peut changer l'ordre des colonnes en permuttant les valeurs 0, 1 et 2 ci-dessous :
  private final static int NOM = 0;
  private final static int POSITION = 1;
  private final static int ENERGY = 2;
  private final static int SHIELD = 3;

  private final static String[] COL_NAME;

  static {
    COL_NAME = new String[NB_CHAMPS];
    COL_NAME[NOM] = "Name";
    COL_NAME[POSITION] = "Position";
    COL_NAME[ENERGY] = "Energy";
    COL_NAME[SHIELD] = "Shield";
  }
  
  private List<Player> listPlayers;
  
  public ListPlayersToTableModelAdapter(List<Player> listPlayers) {
    this.listPlayers = listPlayers;
    for (Player player : listPlayers) {
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
      case NOM:
        return player.getType();
      case POSITION:
        Position pos = player.getPosition();
        return "(" + pos.getX() + " , " + pos.getY() + ")";
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
