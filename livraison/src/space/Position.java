package space;

public class Position {

  private int x;
  private int y;

  /**
    * Constructeur de la classe
    * @param x , de type int.
    * @param y , de type int.
    */
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /**
    * Retourne X.
    * @return this.x
    */
  public int getX() {
    return this.x;
  }
  /**
    * Retourne Y
    *  @return this.y
    */
  public int getY() {
    return this.y;
  }
  /**
    * Mets à jour X
    *  @param x , qui est de type int
    */
  public void setX(int x) {
    this.x = x;
  }

  /**
    * Mets à jour Y
    * @param y , qui est de type int.
    */
  public void setY(int y) {
    this.y = y;
  }

  /**
    * Permet de modifier X
    *
    * @param x le nombre a ajouter
    */
  public void addX(int x){
    this.x+=x;
  }
  /**
    * Permet de modifier Y
    *
    * @param y le nombre a ajouter
    */
  public void addY(int y){
    this.y+=y;
  }

  /**
    * Méthode permettant de retourner une copie.
    *  @return  une nouvelle position.
    */
  public Position getCopy() {
    return new Position(this.x, this.y);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this==o) {
      return true;
    } else {
      if (o instanceof Position) {
        Position pos = (Position) o;
        return this.x == pos.getX() && this.y == pos.getY();
      } else {
        return false;
      }
    }
  }

  /**
    * string representation
    * @return une chaine de caractère.
    */
  @Override
  public String toString() {
    return "("+this.x+","+this.y+")";
  }
}
