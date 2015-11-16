
package unpredictablesets;

/**
 *
 * @author Group 10
 */
public class SkipListSet {
  
  // We will need some kind of head element instance variable
  private Element head;
  // We will need a size instance variable
  private int size;
  
  // We will need a constructor
  public SkipListSet(){
    
  }
  
  // We need a private method to search through the skip list
  private Element find(){
    
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
  
  // !!! After some thought, we should probably change this to a double-linked
  //  list in both the horizontal and vertical directions
  // We will need a private class for our data elements
  private class Element {
    // Will need a reference to the next element on this level
    private Element next;
    // Will need a reference to the lower level
    private Element down;
    // Will need some data
    private final int data; // Maybe this doesn't want to be an int. Dunno.
    
    private Element(int inData){
      data = inData;
    }
    
    // Will need to be able to return its next element
    private Element getNext(){
      return next;
    }
    
    // Will need to be able to change its next element
    private void setNext(Element newNext){
      next = newNext;
    }
    
    // Will need to be able to return its down element
    private Element getDown(){ // Get down, get down! Get down, get down!
      return down;
    }
    // I don't think there is ever a reason to change its down element...
    
    // Will need to be able to return its data
    private int getData(){
      return data;
    }
  }
  
}
