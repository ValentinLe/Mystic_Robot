package game;

public interface IA {

  /**
   * Fais jouer un robot avec comme priorité d'attaquer un autre robot si celui
   * ci est à ça portée sinon il choisit une action aléatoirement
   * @param player la position de départ
   */
  public void execute(Player player);

}
