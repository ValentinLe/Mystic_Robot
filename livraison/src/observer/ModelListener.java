
package observer;

public interface ModelListener {

  /**
    * Méthode testant si quelque chose a changé sur l'objet passé en argument
    *@param source un objet
    */
  public void somethingHasChanged(Object source);
}
