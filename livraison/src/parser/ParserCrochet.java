package parser;

import java.util.*;
import java.io.*;

public class ParserCrochet implements Parser {

  private String texturePath;
  private String configPath;

  /**
    * Constructeur de la classe
    *@param texturePath le chemin du fichier contenant les textures
    *@param configPath le chemin du fichier contenant les configurations (robots et armes)
    */
  public ParserCrochet(String texturePath, String configPath) {
    this.texturePath = texturePath;
    this.configPath = configPath;
  }

  /**
    * Méthode qui lit un fichier nommé texture et qui créé un map
    *@return un Map ayant pour clé le nom d'un élément et pour valeur le lien de sa texture
    */
  @Override
  public Map<String,String> executeTexture() {
    Map<String,String> map = new HashMap<String,String>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(this.texturePath));
      String line;
      while((line = br.readLine()) != null) {
        String[] element = line.split("=");
        map.put(element[0],element[1]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return map;
  }

  /**
    * Méthode qui lit un fichier config et créé un map contenant les armes et un autre contenant les robots
    *@return une liste de Map ayant pour clé un nom et pour valeur un Map correspodant aux attributs
    */
  @Override
  public ArrayList<Map<String,Map<String,String>>> executeConfig() {
    ArrayList<Map<String,Map<String,String>>> elementsList = new ArrayList<>(); //premier élément c'est les armes, 2ème c'est les robots
    Map<String,Map<String,String>> weaponMap = new HashMap<String,Map<String,String>>();
    Map<String,Map<String,String>> robotMap = new HashMap<String,Map<String,String>>();
    Map<String,String> elements;

    try  {
      BufferedReader br = new BufferedReader(new FileReader(this.configPath));
      String line;
      String elementName;
      while ((line = br.readLine()) != null) {
        if(!(line.equals(""))) {
          if(line.charAt(0) == '[') {
            elementName = line.substring(1,line.length()-1);
            elements = new HashMap<String,String>();
            while(!(line = br.readLine()).equals(";")) {
              String[] attribute = line.split("=");
              elements.put(attribute[0],attribute[1]);
            }
            weaponMap.put(elementName,elements);
          } else if (line.charAt(0) == '{') {
            elementName = line.substring(1,line.length()-1);
            elements = new HashMap<String,String>();
            while(!(line = br.readLine()).equals(";")) {
              String[] attribute = line.split("=");
              elements.put(attribute[0],attribute[1]);
            }
            robotMap.put(elementName,elements);
          }
        }
      }
      elementsList.add(weaponMap);
      elementsList.add(robotMap);
    } catch (IOException e){
      e.printStackTrace();
    }
    return elementsList;
  }
}
