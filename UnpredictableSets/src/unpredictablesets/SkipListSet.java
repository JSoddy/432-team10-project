
package unpredictablesets;

/**
 *
 * @author Group 10
 */
public class SkipListSet {
  private static final int POS_INFTY = Integer.MAX_VALUE;
  private static final int NEG_INFTY = Integer.MIN_VALUE;
  
  // We will need some kind of head element instance variable
  private Element head;
  // I guess a tail element wouldn't hurt.
  private Element tail;
  // We will need a size instance variable
  private int size;
  // Should we store the height?
  private int height;
  
  // We will need a constructor
  public SkipListSet(){
    // Probably we should create dummy head and tail elements?
    head = createElement(height, NEG_INFTY);
    tail = createElement(height, POS_INFTY);
    link(head, tail);
    // initialize size and height
    size = 0;
    height = 2;
  }
  
  // Private method to link two elements together.
  //  Takes as arguments two elements, and the minimum height
  //  from which they should be linked
  private void link(Element first, Element second){
    // We should make sure we have both elements at the same level
    while(first.getHeight() > second.getHeight()){
      first = first.getDown();
    }
    while(second.getHeight() > first.getHeight()){
      second = second.getDown();
    }
    
    // Now link in our new element until we've gotten something else in the way
    Element third = first.getNext();
    while (third != null && third.getData() > second.getData()){
      first.setNext(second);
      second.setNext(third);
      third.setPrevious(second);
      second.setPrevious(first);
      first = first.getDown();
      second = second.getDown();
      third = first.getNext();
    }
  }
  
  // Private method to extract an element and repair the links around it
  private void unLink(Element toRemove){
    Element prev;
    Element next;
    while(toRemove != null){
      prev = toRemove.getPrevious();
      next = toRemove.getNext();
      prev.setNext(next);
      next.setPrevious(prev);
    }
  }
  
  // Private method to create an element for insertion into our list
  //  Takes as parameters the height and data of the new element
  //  Returns the top level of the new element
  private Element createElement(int elementHeight, int inData){
    // "down" from the bottom layer should be null
    Element newElement = null;
    // Every element has height at least 1
    //  We will create this out here so we don't have to check for
    //  dereferencing null pointer inside the loop
    newElement = new Element(inData, 0, newElement);
    // Loop through, adding elements as necessary to reach elementHeight
    for (int i = 1; i < elementHeight; i++){
      newElement = new Element(inData, i, newElement);
      newElement.down.setUp(newElement); // the lower element needs to point up
    }
    return newElement;
  }
  
  // We need a private method to search through the skip list
  private Element find(int toFind){
    
    return null; // !!! placeholder
  }
  
  // We will need a membership operation method
  public boolean isInSet(int toFind){
    Element found = find(toFind);
    if (found == null){
      return false;
    } else {
      return true;
    }
  }
  
  // We will need an add operation method
  public boolean addElement(){
    
    return false; // !!! placeholder
  }
  
  // We will need a remove operation method
  public boolean removeElement(int toRemove){
    Element found = find(toRemove);
    if (found == null){
      return false;
    } else {
      unLink(found);
      return true;
    }
  }
  
  // We need some size returning method
  public int size(){
    return size;
  }
  
  // !!! NYI
  // We will need a method that will return the contents of the set, and
  //  do height re-adjustments in the process
  //  What should this return? An array list? !!!
  public void getContents(){
    
  }
  
  // We will need a private class for our data elements
  private class Element {
    // Will need a reference to the next element on this level
    private Element next;
    // And to the preceeding element
    private Element previous;
    // Will need a reference to the lower level
    private Element down;
    // And to the one on the higher level
    private Element up;
    // Should the elements know what height they are at?
    private final int height;
    // Will need some data
    private final int data; // Maybe this doesn't want to be an int. Dunno.
    
    // Constructor. I think data and the 'down' element are enough.
    private Element(int inData, int inHeight, Element inDown){
      data = inData;
      down = inDown;
      height = inHeight;
    }
    
    // Will need to be able to return its next element
    private Element getNext(){
      return next;
    }
    
    // Will need to be able to change its next element
    private void setNext(Element newNext){
      next = newNext;
    }
    
    // Will need to be able to return its previous element
    private Element getPrevious(){
      return previous;
    }
    
    // Will need to be able to change its previous element
    private void setPrevious(Element newPrevious){
      previous = newPrevious;
    }
    
    // Will need to be able to return its down element
    private Element getDown(){ // Get down, get down! Get down, get down!
      return down;
    }
    
    // I don't think there is ever a reason to change its down element...
    // But, shoot. No harm in doing it anyway
    private void setDown(Element newDown){
      down = newDown;
    }
    
    // Will need to be able to return its up element
    private Element getUp(){ // You slept through your alarm!
      return up;
    }
    
    // I guess we might as well have this method as well
    private void setUp(Element newUp){ // It's a trap!
      up = newUp;
    }
    
    // I suppose someone might want the height
    private int getHeight(){
      return height;
    }
    
    // Will need to be able to return its data
    private int getData(){
      return data;
    }
  }
  
}
