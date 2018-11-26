
package game;

import java.util.*;
import space.*;
import observer.*;

public interface Game extends ListenableModel {

  /**
    * Permet de retourner une Tile a une certaine position.
    * @param position la position de la tile
    * @return la tile
    */
  public Tile getTileAt(Position position);

  public void loopIa();

  /**
    * permet de recommencer une partie.
    * @param listPlayers la liste des joueurs pour la nouvelle partie
    */
  public void restart(List<Player> listPlayers);

  /**
    * permet de savoir si on peut bouger sur une position.
    * @param position la position sur laquelle on veut bouger
    * @return true si la case est libre
    */
  public boolean canMove(Position position);

  /**
    * permet de mettre une tile.
    * @param tile la nouvelle case a setter
    */
  public void setTile(Tile tile);

  /**
    * Permet d'activer une tile.
    * @param tile la case a activer
    */
  public void activate(Tile tile);

  /**
    * permet de changer de joueur.
    */
  public void switchPlayer();

  /**
    * retourne la grille
    * @return la grille
    */

  public Tile[][] getGrid();

  /**
    * retourne le joueur suivant
    * @return le joueur en train de jouer
    */
  public Player getNextPlayer();

  /**
    * retourne la liste des joueurs
    * @return la liste des joueurs
    */
  public List<Player> getListPlayers();

  /**
    * retourne la largeur.
    * @return la largeur du jeu
    */
  public int getWidth();

  /**
    * retourne la hauteur
    * @return la hauteur de la grille
    */
  public int getHeight();

  /**
    * retourne les tile d'une certaines direction.
   * @param position la position de depart
   * @param direction la direction dans laquelle regarder
   * @param range la distance dans laquelle aller
   * @return la listes des cases dans la direction voulue
    */
  public List<Tile> getTileInDirection(Position position, Direction direction, int range);

  /**
    * retourne le premier joueur dans la direction
    * @param position la position de depart
    * @param direction la direction dans laquelle aller
    * @param range la distance dans la direction
    * @return le premier joueur dans la direction
    */
  public Player getPlayerInDirection(Position position, Direction direction, int range);

  /**
    * permet de passer un tour.
    */
  public void skipTurn();

  /**
    * Dis si la position est dans le jeu.
    * @param position la position du test
    * @return true si la position est dans le jeu
    */
  public boolean isInIndex(Position position);

  /**
    * Retourne l'equipement  du joueur
    * @return recupere l'equipement du joueur
    */
  public Map<Equipement, Integer> getEquipementOfPlayer();
}
