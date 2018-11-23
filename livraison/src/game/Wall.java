package game;

import space.Position;

public class Wall extends AbstractTile {

  public Wall(String type,Position position) {
    super(type,position,true);
  }
  
  public Wall(Position position) {
    this("#", position);
  }

  @Override
  public String toString() {
    return this.type;
  }
}
