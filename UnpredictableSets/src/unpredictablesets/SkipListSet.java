
package unpredictablesets;

/**
 *
 * @author Group 10
 */
public class SkipListSet {
  
  // We will need some kind of head element instance variable
  private element head;
  // We will need a size instance variable
  private int size;
  
  // We will need a constructor
  public SkipListSet(){
    
  }
  
  // We need a private method to search through the skip list
  private element find(){
    
    return null;
  }
  
  // We will need a membership operation method
  public boolean isInSet(){
    
    return false; // placeholder
  }
  
  // We will need an add operation method
  public boolean addElement(){
    
    return false; // placeholder
  }
  
  // We will need a remove operation method
  public boolean removeElement(){
    
    return false; // placeholder
  }
  
  // We need some size returning method
  public int size(){
    return size;
  }
  
  // We will need a private class for our elements
  private class element {
    // Will need a reference to the next element on this level
    // Will need a reference to the lower level
    // Will need some data
    private int data; // Maybe this doesn't want to be an int. We can change it.
    
    // Will need to be able to return its next element
    
    // Will need to be able to change its next element
    
    // Will need to be able to return its down element
    
    // I don't think there is ever a reason to change its down element...
    
    // Will need to be able to return its data
  }
  
}
