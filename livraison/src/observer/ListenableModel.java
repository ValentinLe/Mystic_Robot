
package observer;

public interface ListenableModel {
  
  public void addModelListener(ModelListener l);
  public void removeModelListener(ModelListener l);
}
