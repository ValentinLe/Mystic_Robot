
package space;

public enum Direction {
  UP (0,-1),
  LEFT (-1,0),
  DOWN (0,1),
  RIGHT (1,0);

  private final int x;
  private final int y;

/**
  * Constructeur de la classe
  *  @param x , qui est un int.
  * @param y , qui est un int.
  */
  Direction(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
    * Retourne X
    * @return this.x
    */
  public int getX() {
    return this.x;
  }
  /**
    * Retourne Y
    * @return this.y
    */
  public int getY() {
    return this.y;
  }
}
