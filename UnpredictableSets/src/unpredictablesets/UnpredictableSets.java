package unpredictablesets;

/**
 *
 * @author Group 10
 */
public class UnpredictableSets {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    CircularSkipListSet testSet = new CircularSkipListSet();
    
    System.out.println(testSet.getContents());
    testSet.diag();
    
    testSet.addElement(5);
    System.out.println(testSet.isInSet(5));
    
    System.out.println(testSet.getContents());
    testSet.diag();
    
    System.out.println(testSet.addElement(3));
    System.out.println(testSet.isInSet(3));
    System.out.println(testSet.isInSet(5));
    
    System.out.println(testSet.getContents());
    testSet.diag();
    
    testSet.addElement(999);
    System.out.println(testSet.isInSet(999));
    System.out.println(testSet.isInSet(3));
    System.out.println(testSet.isInSet(5));
    
    System.out.println(testSet.getContents());
    testSet.diag();
    
    testSet.addElement(77);
    System.out.println(testSet.isInSet(77));
    System.out.println(testSet.isInSet(999));
    System.out.println(testSet.isInSet(3));
    System.out.println(testSet.isInSet(5));
    
    System.out.println(testSet.getContents());
    testSet.diag();
    
    testSet.addElement(4);
    //System.out.println(testSet.isInSet(-5000));
    System.out.println(testSet.isInSet(77));
    System.out.println(testSet.isInSet(999));
    System.out.println(testSet.isInSet(3));
    System.out.println(testSet.isInSet(5));
    
    System.out.println(testSet.getContents());
    testSet.diag();
    
    testSet.isInSet(77);
    testSet.removeElement(77);
    
    System.out.println(testSet.getContents());
    testSet.diag();
    
//    for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE - 10000001; i += (int)(Math.random() * 1000)){
//      testSet.addElement(i);
//    }
    
    System.out.println(testSet.isInSet(800));
    System.out.println(testSet.isInSet(3));
    System.out.println(testSet.isInSet(77));
    System.out.println(testSet.isInSet(999));
    
    testSet.removeElement(3);
    System.out.println(testSet.isInSet(3));
    System.out.println(testSet.getContents().size());
    testSet.diag();
  }
  
}
