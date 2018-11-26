
package game;

import space.Position;
import space.Direction;

public interface Equipement {
    public boolean use(Position position,Direction direction,Game board);

    /**
      * Méthode permettant de retourner le Type.
      * @return retourne le type de l'equipement (le nom)
      */
    public String getType();

    /**
      * Méthode permettant de retourner le proprietaire de l'equipement
      * @return le joueur proprietaire
      */
    public Player getOwner();

    /**
      * Méthode permettant de modifier le proprietaire de l'equipement.
      * @param player le joueur qui devien proprietaire de l'equipement
      */
    public void setOwner(Player player);

    /**
      * Methode renvoyant une copie.
      * @return une copie de l'equipement
      */
    public Equipement getCopy();
}
