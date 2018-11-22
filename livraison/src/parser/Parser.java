package parser;

import java.util.*;

public interface Parser {

  public Map<String,String> executeTexture();

  public ArrayList<Map<String,Map<String,String>>> executeConfig();

}
