package unpredictablesets;

import java.util.ArrayList;

/**
 *
 * @author Group 10
 */
public class CircularSkipListSet extends SkipListSet {

  private static final int POS_INFTY = Integer.MAX_VALUE;
  private static final int NEG_INFTY = Integer.MIN_VALUE;
  private static final int DEFAULT_HEIGHT = 4;

  // We will need some kind of head element instance variable
  private Element head;
  // We will need a size instance variable
  private int size;
  // Should we store the height?
  private int maxHeight;

  // Updated for circular -- not yet tested
  // We will need a constructor
  public CircularSkipListSet() {
    // initialize size and height
    size = 0;
    maxHeight = DEFAULT_HEIGHT;
    // Probably we should create dummy head and tail elements?
    head = null;
  }

  // Updated for circular
  // When we add our first element, we need to link it to itself along its
  //  entire height. This initializes the circular property
  private void initialize() {
    Element headLevel = head;
    while (headLevel != null && headLevel.getNext() == null) {
      headLevel.next = headLevel;
      headLevel.previous = headLevel;
      headLevel = headLevel.down;
    }
  }

  // Updated for circular - Maybe buggy -- Definitely buggy!!!!
  // Private method to link two elements together.
  //  Takes as arguments two elements, links them together as long as there is
  //  not another element between them
  private void link(Element first, Element second) {
    // We should make sure we have both elements at the same level
    while (first.getHeight() > second.getHeight()) {
      first = first.getDown();
    }
    while (second.getHeight() > first.getHeight()) {
      second = second.getDown();
    }

    // Now link in our new element until we've gotten something else in the way
    Element third = first.getNext();
    while (first != null){
      if (first.next == first 
              // Spaces are separating the separate logical blocks
              || (first.data < second.data 
              && (third.data > second.data
              || third.data < first.data))
              // Again, Just separating a part of this statement for clarity
              || (first.data > second.data
              && third.data > second.data
              && third.data < first.data)){
        
        first.setNext(second);
        second.setNext(third);
        third.setPrevious(second);
        second.setPrevious(first);
      }
      first = first.getDown();
      second = second.getDown();
      if (first != null) {
        third = first.getNext();
      } else {
        third = null;
      }
    }
  }

  // !!! Not yet updated -- Maybe okay without update
  // Private method to extract an element and repair the links around it
  private void unLink(Element toRemove) {
    Element prev;
    Element next;
    while (toRemove != null) {
      prev = toRemove.getPrevious();
      next = toRemove.getNext();
      prev.setNext(next);
      next.setPrevious(prev);
      toRemove = toRemove.getDown();
    }
  }

  // !!! Not yet updated -- Maybe okay without update
  // Private method to create an element for insertion into our list
  //  Takes as parameters the height and data of the new element
  //  Returns the top level of the new element
  private Element createElement(int elementHeight, int inData) {
    // "down" from the bottom layer should be null
    Element newElement = null;
    // Every element has height at least 1
    //  We will create this out here so we don't have to check for
    //  dereferencing null pointer inside the loop
    newElement = new Element(inData, 0, newElement);
    // Loop through, adding elements as necessary to reach elementHeight
    for (int i = 1; i < elementHeight; i++) {
      newElement = new Element(inData, i, newElement);
      newElement.down.setUp(newElement); // the lower element needs to point up
    }
    return newElement;
  }

  // Updated for circular. Maybe will work???
  // We need a private method to search through the skip list
  private Element find(int toFind) {
    Element currentElement = head;
    while (currentElement != null) {
      if (currentElement.data == toFind) {
        return currentElement;
      } else if (currentElement.next == currentElement 
              // Spaces are separating the separate logical blocks
              || (currentElement.data < toFind 
              && (currentElement.next.data > toFind
              || currentElement.next.data < currentElement.data))
              // Again, Just separating a part of this statement for clarity
              || (currentElement.data > toFind
              && currentElement.next.data > toFind
              && currentElement.next.data < currentElement.data)) {
        if (currentElement.height == 0) {
          return null;
        } else {
          currentElement = currentElement.down;
        }
      } else {
        currentElement = currentElement.next;
      }
    }
    return null;
  }

  // Updated for circular
  // Just a method for printing out values connected to head and tail
  //  to see if everything is working right
  public void diag() {
    if (size == 0){
      System.out.println("Height: " + maxHeight);
      System.out.println("Empty!");
      return;
    }
    System.out.println("Height : " + maxHeight);
    System.out.println("Size: " + size);
    Element current = head;
    while (current != null) {
      System.out.println("Current: " + current.data);
      if (current.next != null) {
        System.out.println("Next: " + current.next.data);
      } else {
        System.out.println("Next = null");
      }
      if (current.previous != null) {
        System.out.println("Previous: " + current.previous.data);       
      } else {
        System.out.println("Previous = null");
      }
      current = current.down;
    }
    
  }

  // !!! Not yet updated -- Probably fine without update
  // We will need a membership operation method
  public boolean isInSet(int toFind) {
    return find(toFind) != null; // If find returns null, it's not there
  }

  // Updated for circular -- Not yet tested
  // We will need an add operation method
  public boolean addElement(int toAdd) {
    if (isInSet(toAdd)) {
      return false;
    } else {
      if (size == 0){
        Element newElement = createElement(maxHeight, toAdd);
        head = newElement;
        initialize();
      } else {
        Element newElement = createElement(chooseHeight(), toAdd);
        insert(newElement);
      }
      size++;
      adjustMaxHeight();
      resizeHead();
      return true;
    }
  }
  
  // Updated for circular -- Maybe working?
  // Method to place an element in its proper location in the list
  private void insert(Element toInsert){
       
      Element current = head;
      while (current != null) {
        if (current.next == current || (current.data < toInsert.data && (current.next.data > toInsert.data || current.next.data < current.data))
                                    || (current.data > current.next.data && current.next.data > toInsert.data)) {
          link(current, toInsert);
          current = current.down;
        } else if (current.next.data == toInsert.getData()) {
          current = current.down;
        } else {
          current = current.next;
        }
      }
      
      // This should take care of cases where the new element is taller than any
      //  other elements
      while (toInsert != null){
        if (toInsert.next == null){
          toInsert.next = toInsert;
          toInsert.previous = toInsert;
        } 
        toInsert = toInsert.down;
      }
  }

  // We will need a remove operation method - Updated for circular
  public boolean removeElement(int toRemove) {
    Element found = find(toRemove);
    if (found == null) {
      return false;
    } else {
      if (size == 1){
          head = null;
          size = 0;
          return true;
      } else {
        unLink(found);
        size--;
        adjustMaxHeight();
        return true;
      }
    }
  }
  
  // Updated for circular -- Not yet tested
  // We need to periodically change the maximum height which is allowable
  //  for the elements
  private void adjustMaxHeight(){
    // 31 - number of leading zeroes should give us the log of our size
    int calculatedHeight = 31 - Integer.numberOfLeadingZeros(size + 1);
    if (calculatedHeight < maxHeight){
      if (calculatedHeight >= DEFAULT_HEIGHT){
        maxHeight = calculatedHeight;
      } else {
        maxHeight = DEFAULT_HEIGHT;
      }
    } else {
      maxHeight = calculatedHeight;
    }
  }
  
  //  Updated for circular
  //  method will have to consider these cases
  private void resizeHead(){
    while (head.getHeight() < maxHeight){
      Element newHead = new Element(head.data, head.height+1, head);
      head.setUp(newHead);
      head = newHead;
    }
    initialize();
  }
  
  // !!! Not yet updated
  // Method to increase or decrease the size of a single element stack
  //  so that it is consistent with our needs
  // Will not work for head and tail elements
  private void resizeStack(Element toResize, int newHeight){
    if (toResize.height > newHeight) {
      sizeDown(toResize, newHeight);
    } else {
      sizeUp(toResize, newHeight);
    }
  }
  
  // !!! Not yet updated
  // Just for increasing the size of a stack
  private void sizeUp(Element toResize, int newHeight){
    while (toResize.getHeight() < newHeight){
      Element newLevel = new Element(toResize.getData(), toResize.getHeight()+1, toResize);
      toResize.setUp(newLevel);
      toResize = newLevel;
    }
    insert(toResize);
  }
  
  // !!! Not yet updated -- Should work, but is very inefficient and should
  //  be rewritten anyway
  // Just for decreasing the size of a stack
  private void sizeDown(Element toResize, int newHeight){
    while (toResize.getHeight() > newHeight){
      toResize.next.setPrevious(toResize.previous);
      toResize.previous.setNext(toResize.next);
      toResize = toResize.getDown();
    }
    toResize.setUp(null);
  }

  // !!! Not yet updated -- Should not need update
  // We need some size returning method
  public int getSize() {
    return size;
  }

  // Updated for circular
  // We will need a method that will return the contents of the set, and
  //  do height re-adjustments in the process
  //  What should this return? An array list? !!!
  public ArrayList<Integer> getContents() {
    ArrayList<Integer> list = new ArrayList();
    if (head == null){
      return list;
    }
    Element current = head;
    // We will travel along the longest paths we have to find the lowest
    //  numbered element
    while(current.down != null){
      if (current.next.data > current.data){
        current = current.next;
      } else {
        current = current.down;
      }
    }
    // Then we will move along the bottom until we are in position
    while(current.next.data > current.data) {
      current = current.next;
    }
    // Then start with the first element
    current = current.next;
    // Keep track of the data in our starting position, so we don't loop forever
    int startPos = current.data;
    // Just add to the list and then move on to the next
    do{
      list.add(current.data);
      current = current.next;
    } while (current.data != startPos);
    return list;
  }
  
  /*
  // !!! Not yet updated -- We do not need this method for circular random
  private void updateSize(Element toAdjust, int position){
    while(toAdjust.getUp() != null){
      toAdjust = toAdjust.getUp();
    }
    // number of trailing zeros should give us the number of times the
    //  position can be divided by 2 before the number becomes odd
    resizeStack(toAdjust, Integer.numberOfTrailingZeros(position));
  }
  */

  // !!! Not yet updated -- Should not need updating
  // Method to randomly choose a height for a newly inserted node
  private int chooseHeight() {
    boolean heads = (Math.random() < 0.50);
    int height = 0;
    while (heads && height < maxHeight) {
      height++;
      heads = (Math.random() < .50);
    }
    return height;
  }

  // !!! Not yet updated -- May not need updating unless we have to add
  //  skip counts and/or indices
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
    private Element(int inData, int inHeight, Element inDown) {
      data = inData;
      down = inDown;
      height = inHeight;
    }

    // Will need to be able to return its next element
    private Element getNext() {
      return next;
    }

    // Will need to be able to change its next element
    private void setNext(Element newNext) {
      next = newNext;
    }

    // Will need to be able to return its previous element
    private Element getPrevious() {
      return previous;
    }

    // Will need to be able to change its previous element
    private void setPrevious(Element newPrevious) {
      previous = newPrevious;
    }

    // Will need to be able to return its down element
    private Element getDown() { // Get down, get down! Get down, get down!
      return down;
    }

    // I don't think there is ever a reason to change its down element...
    // But, shoot. No harm in doing it anyway
    private void setDown(Element newDown) {
      down = newDown;
    }

    // Will need to be able to return its up element
    private Element getUp() { // You slept through your alarm!
      return up;
    }

    // I guess we might as well have this method as well
    private void setUp(Element newUp) { // It's a trap!
      up = newUp;
    }

    // I suppose someone might want the height
    private int getHeight() {
      return height;
    }

    // Will need to be able to return its data
    private int getData() {
      return data;
    }
  }

}
