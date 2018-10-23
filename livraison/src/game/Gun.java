package game;

public abstract class Gun extends Weapon {

  public Gun(int damage, int range) {
    super(damage, range);
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public int getRange() {
    return this.range;
  }

  @Override
  public void applyDamage(Board board, Position playerPosition, Position direction) {
    Tile[][] grid = board.getGrid();
    if (direction.getX()!=0) {
      for (int i = playerPosition.getX(); i < this.range; i+=direction.getX()) {
        if (grid[i][playerPosition.getY()] instanceof Player) {
          ((Player)grid[i][playerPosition.getY()]).addEnergy(-this.damage);
          break;
        }
      }
    } else {
      for (int i = playerPosition.getY(); i < this.range; i+=direction.getY()) {
        if (grid[playerPosition.getX()][i] instanceof Player) {
          ((Player)grid[playerPosition.getX()][i]).addEnergy(-this.damage);
          break;
        }
      }
    }
  }
}
