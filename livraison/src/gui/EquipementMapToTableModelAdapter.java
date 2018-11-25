
package gui;

import game.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import observer.ModelListener;

public class EquipementMapToTableModelAdapter extends AbstractTableModel implements ModelListener {

  private final static int NB_CHAMPS = 2;

  // On peut changer l'ordre des colonnes en permuttant les valeurs 0, 1 et 2 ci-dessous :
  private final static int NAME = 0;
  private final static int AMMO = 1;

  private final static String[] COL_NAME;

  static {
    COL_NAME = new String[NB_CHAMPS];
    COL_NAME[NAME] = "Equipement Name";
    COL_NAME[AMMO] = "Ammo";
  }

  private Game game;
  private Map<Equipement, Integer> equipement;

  public EquipementMapToTableModelAdapter(Game game) {
    this.game = game;
    this.equipement = game.getEquipementOfPlayer();
    game.addModelListener(this);
    for (Player player : game.getListPlayers()) {
      player.addModelListener(this);
    }
  }

  @Override
  public int getRowCount() {
    return this.equipement.size();
  }

  @Override
  public int getColumnCount() {
    return NB_CHAMPS;
  }

  public Equipement getEquipementWithIndex(Map<Equipement, Integer> equipement, int index) {
    int i = 0;
    for (Equipement e : equipement.keySet()) {
      if (i == index) {
        return e;
      }
      i++;
    }
    return null;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Equipement equipement = this.getEquipementWithIndex(this.equipement, rowIndex);
    switch(columnIndex) {
      case NAME:
        return equipement.getType();
      case AMMO:
        return this.equipement.get(equipement);
    }
    return null;
  }

  @Override
  public String getColumnName(int col) {
    return COL_NAME[col];
  }

  @Override
  public void somethingHasChanged(Object source) {
    this.equipement = game.getEquipementOfPlayer();
    fireTableDataChanged();
  }
}
