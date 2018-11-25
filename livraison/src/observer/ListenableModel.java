
package observer;

public interface ListenableModel {

  /**
    * Permet d'ajouter un listener sur cet objet
    *@param l un ModelListener
    */
  public void addModelListener(ModelListener l);

  /**
    * Permet de supprimer un listener sur cet objet
    *@param l un ModelListener
    */
  public void removeModelListener(ModelListener l);
}
