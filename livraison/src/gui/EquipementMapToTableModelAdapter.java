
package gui;

import game.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import observer.ModelListener;

/**
 * adapter d'une map d'equipement d'un joueur a une table model
 *
 */
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

  /**
   * creer une instance de l'adapter
   * @param game le jeu dans lequel aller chercher le joueur
   * pour proxy le joueur qu'il possede et pour le RealGame le joueur qui
   * est en train de jouer
   */
  public EquipementMapToTableModelAdapter(Game game) {
    this.game = game;
    this.equipement = game.getEquipementOfPlayer();
    game.addModelListener(this);
    for (Player player : game.getListPlayers()) {
      player.addModelListener(this);
    }
  }

  /**
   * getter le nombre de lignes
   * @return le nombre de lignes
   */
  @Override
  public int getRowCount() {
    return this.equipement.size();
  }

  /**
   * getter sur le nombre de colonnes
   * @return le nombre de colonnes
   */
  @Override
  public int getColumnCount() {
    return NB_CHAMPS;
  }

  /**
   * parcours les equipements et renvoi le nieme element
   * @param equipement la map d'equipements
   * @param index le nieme equipement a recuperer
   * @return le nieme equipement
   */
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

  /**
   * recupere la valeur en (rowIndex,columnIndex) de la table
   * @param rowIndex la ligne
   * @param columnIndex la colonne
   * @return la valeur de la cellule
   */
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
    this.equipement = game.getEquipementOfPlayer();
    fireTableDataChanged();
  }
}
