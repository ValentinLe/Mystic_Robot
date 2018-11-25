
package observer;

import java.util.*;

public class AbstractListenableModel implements ListenableModel {

  private List<ModelListener> listeners;

  /**
    * Le constructeur de la classe
    */
  public AbstractListenableModel() {
    listeners = new ArrayList<>();
  }

  /**
    * Permet d'ajouter un listener sur cet objet
    *@param l un ModelListener
    */
  @Override
  public void addModelListener(ModelListener l) {
    listeners.add(l);
  }

  /**
    * Permet de supprimer un listener sur cet objet
    *@param l un ModelListener
    */
  @Override
  public void removeModelListener(ModelListener l) {
    listeners.remove(l);
  }

  /**
    * Permet de dire aux listeners que l'objet a changé
    */
  protected void fireChange() {
    for (ModelListener l : listeners) {
      l.somethingHasChanged(this);
    }
  }

}
