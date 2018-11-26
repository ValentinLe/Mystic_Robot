
package game;

import java.util.*;
import space.*;
import observer.*;

public class ProxyGame implements Game {

  private Game game;
  private Player player;

  /**
   * creer une instance de proxyboard
   * @param game la vision du proxy
   * @param player le joueur qui voit le proxy
   */
  public ProxyGame(Game game, Player player) {
    this.game = game;
    this.player = player;
    player.setGame(this);
  }

  /**
    * ajoute un listener sur le vrai jeu
    * @param l le listener a ajouter
    */
  @Override
  public void addModelListener(ModelListener l) {
    this.game.addModelListener(l);
  }

  /**
    * ajoute un listener sur le vrai jeu
    * @param l le listener a ajouter
    */
  @Override
  public void removeModelListener(ModelListener l) {
    this.game.addModelListener(l);
  }

  /**
   * Getter de la case présente à la position
   * @param position la position dont on veut la case
   * @return la case ciblée
   */
  @Override
  public Tile getTileAt(Position position) {
    Tile tile = this.game.getTileAt(position);
    if (tile instanceof ExplosifPlate) {
      Player playerProprio = ((ExplosifPlate)tile).getPlayer();
      if (playerProprio == this.player) {
        return tile;
      } else {
        return new EmptyTile(position);
      }
    } else {
      return tile;
    }
  }

  /**
   * Test si la position est dans la grille
   * @param position la position a tester
   * @return true si la position est dans la grille
   */
  @Override
  public boolean isInIndex(Position position){
    return this.game.isInIndex(position);
  }

  /**
   * test si un joueur peut se déplacer sur la nouvelle position
   * @param position la position du test
   * @return true si le peux se déplacer à la nouvelle position
   */
  @Override
  public boolean canMove(Position position) {
    return this.game.canMove(position);
  }

  /**
   * Recupére les cases depuis une position dans une direction selon une portée
   * @param position la position de départ
   * @param direction la direction dans laquelle aller chercher les cases
   * @param range la porté (le nombre de cases à avoir)
   * @return la liste des cases voulues
   */
  @Override
  public List<Tile> getTileInDirection(Position position, Direction direction, int range){
    return this.game.getTileInDirection(position,direction,range);
  }

  /**
   * retourne le player qui se trouve dans la direction indiqué
   * @param position position à partir de laquelle on cherche
   * @param direction direction dans laquelle chercher
   * @param range jusqu'où cherché dans la direction voulus
   * @return le player trouvé
   */
  @Override
  public Player getPlayerInDirection(Position position, Direction direction, int range){
    return this.game.getPlayerInDirection(position,direction,range);
  }

  /**
   * Passe le tour du joueur courant
   */
  @Override
  public void skipTurn(){
    this.game.skipTurn();
  }

  /**
   * remplace une Tile par la tile passé en paramêtre
   * @param tile la Tile par laquelle on veut remplacer
   */
  @Override
  public void setTile(Tile tile) {
    this.game.setTile(tile);
  }

  /**
   * Active la case avec la liste des joueurs dans le voisinage de la case si
   * c'est une instance d'usable
   * @param tile la case à éventuellement activer
   */
  @Override
  public void activate(Tile tile) {
    this.game.activate(tile);
  }

  /**
   * place le joueur en tete de file à la fin de celle-ci
   */
  @Override
  public void switchPlayer() {
    this.game.switchPlayer();
  }

  /**
   * Retourne la map de jeu
   * @return Tile[][]
   */
  @Override
  public Tile[][] getGrid() {
    return this.game.getGrid();
  }

  /**
  * récupère le premier joueur de la file
  * @return le prochain joueur qui doit jouer
  */
  @Override
  public Player getNextPlayer() {
    return this.game.getNextPlayer();
  }

  /**
   * Retourne la largeur de la map
   * @return int
   */
  @Override
  public int getWidth() {
    return this.game.getWidth();
  }

  /**
   * Retourne la hauteur de la map
   * @return int
   */
  @Override
  public int getHeight() {
    return this.game.getHeight();
  }

  /**
   * Retourne la liste des joueurs sur la map
   * @return la liste des joueurs
   */
  @Override
  public List<Player> getListPlayers() {
    return this.game.getListPlayers();
  }

  /**
   * Retourne l'équipement du joueur courrant
   * @return le map d'équipement du joueur
   */
  @Override
  public Map<Equipement, Integer> getEquipementOfPlayer() {
    return this.player.getEquipement();
  }

  /**
   * fonction toString permet de retourner la grille de jeu sous forme de chaine
   * de caractères
   * @return String
   */
  @Override
  public String toString() {
    String res = "  ";
    int width = this.game.getWidth();
    int height = this.game.getHeight();
    // numero superieurs
    for (int k = 0; k < width; k++) {
      res += " " + k + " ";
    }
    res += "\n";
    for (int j = 0; j < height; j++) {
      res += j + " "; // numeros coté
      for (int i = 0; i < width; i++) {
        Tile tile = this.getTileAt(new Position(i, j));
        res += " " + tile + " ";
      }
      res += "\n";
    }
    return res;
  }
}
