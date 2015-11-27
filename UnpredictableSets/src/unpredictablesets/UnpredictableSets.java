package unpredictablesets;

import java.sql.Timestamp;

/**
 *
 * @author Group 10
 */
public class UnpredictableSets {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    SkipListSet testSet1 = new SkipListSet();
    SkipListSet testSet2 = new CircularSkipListSet();
    
//    for (int i = -2000; i < 2000; i += 5){
//      int j = (int) ((Math.random() - .5) * i);
//      testSet2.addElement(j);
//    }
//    
//    testSet2.diag();
    

    Timestamp timer = new Timestamp(System.nanoTime());
    for (int i = -2000000000; i < 2000000000; i += 500) {
      //int j = (int) ((Math.random() - .5) * i);
      testSet1.addElement(i);
    }
    System.out.println(System.nanoTime() - timer.getTime());
    timer = new Timestamp(System.nanoTime());
    for (int i = -2000000000; i < 2000000000; i += 500) {
      //int j = (int) ((Math.random() - .5) * i);
      testSet2.addElement(i);
    }
    System.out.println(System.nanoTime() - timer.getTime());
    timer = new Timestamp(System.nanoTime());
    testSet1.isInSet(5000);
    System.out.println(System.nanoTime() - timer.getTime());
    timer = new Timestamp(System.nanoTime());
    testSet2.isInSet(5000);
    System.out.println(System.nanoTime() - timer.getTime());
    timer = new Timestamp(System.nanoTime());
    System.out.println(testSet1.getContents().size());
    System.out.println(System.nanoTime() - timer.getTime());
    timer = new Timestamp(System.nanoTime());
    System.out.println(testSet2.getContents().size());
    System.out.println(System.nanoTime() - timer.getTime());
  }
}
