
package game;

import java.util.*;
import space.*;
import observer.*;

public interface Game extends ListenableModel {

  /**
    * Permet de retourner une Tile.
    */
  public Tile getTileAt(Position position);
  /**
    * permet de recommencer une partie.
    */
  public void restart();
  /**
    * permet de savoir si on peut bouger.
    */
  public boolean canMove(Position position);
  /**
    * permet de mettre une tile.
    */
  public void setTile(Tile tile);
  /**
    * Permet d'activer une tile.
    */
  public void activate(Tile tile);
  /**
    * permet de changer de joueur.
    */
  public void switchPlayer();
  /**
    * retourne la grille
    */
  public Tile[][] getGrid();
  /**
    * retourne le joueur suivant
    */
  public Player getNextPlayer();
  /**
    * retourne la liste des joueurs
    */
  public List<Player> getListPlayers();
  /**
    * retourne la largeur.
    */
  public int getWidth();
  /**
    * retourne la hauteur
    */
  public int getHeight();
  /**
    * retourne les tile d'une certaines direction.
    */
  public List<Tile> getTileInDirection(Position position, Direction direction, int range);
  /**
    * permet de passer un tour.
    */
  public void skipTurn();
  /**
    * Dis si la position est dans le jeu.
    */
  public boolean isInIndex(Position position);
  /**
    * Retourne l'equipement  du joueur
    */
  public Map<Equipement, Integer> getEquipementOfPlayer();
}
