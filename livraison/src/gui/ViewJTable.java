
package gui;

import javax.swing.*;
import java.awt.*;

/**
 * vue d'un JTable
 * 
 */
public class ViewJTable extends JScrollPane {

  /**
   * creer une vue sur la jtable
   * @param table la JTable
   */
  public ViewJTable(JTable table) {
    super(table);
  }
}
