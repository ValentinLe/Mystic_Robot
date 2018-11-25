package parser;

import java.util.*;

public interface Parser {

  /**
    * Méthode qui lit un fichier nommé texture et qui créé un map
    *@return un Map ayant pour clé le nom d'un élément et pour valeur le lien de sa texture
    */
  public Map<String,String> executeTexture();

  /**
    * Méthode qui lit un fichier config et créé un map contenant les armes et un autre contenant les robots
    *@return une liste de Map ayant pour clé un nom et pour valeur un Map correspodant aux attributs
    */
  public ArrayList<Map<String,Map<String,String>>> executeConfig();

}
