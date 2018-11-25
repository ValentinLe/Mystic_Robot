
package observer;

import java.util.*;

public class AbstractListenableModel implements ListenableModel {

  private List<ModelListener> listeners;

  public AbstractListenableModel() {
    listeners = new ArrayList<>();
  }


  @Override
  public void addModelListener(ModelListener l) {
    listeners.add(l);
  }

  @Override
  public void removeModelListener(ModelListener l) {
    listeners.remove(l);
  }

  protected void fireChange() {
    for (ModelListener l : listeners) {
      l.somethingHasChanged(this);
    }
  }

}
