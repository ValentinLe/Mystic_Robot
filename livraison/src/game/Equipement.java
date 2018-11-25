
package game;

import space.Position;
import space.Direction;

public interface Equipement {
    public boolean use(Position position,Direction direction,Game board);

    /**
      * Méthode permettant de retourner le Type.
      *
      *
      *
      */
    public String getType();

    /**
      * Méthode permettant de retourner "Owner"
      *
      *
      *
      */
    public Player getOwner();

    /**
      * Méthode permettant de mettre en place un proprietaire.
      * un "Owner"
      *
      *
      */
    public void setOwner(Player player);

    /**
      * Methode renvoyant une copie.
      *
      *
      *
      */
    public Equipement getCopy();
}
