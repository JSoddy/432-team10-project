package unpredictablesets;

import java.sql.Timestamp;
import java.util.ArrayList;

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
    CircularSkipListSet testSet2 = new CircularSkipListSet();
    
    
    testSet2.diag();
    testSet2.skipDiag();
    
    for (int i = 1; i < 25; i++){
      int j = (int) (i * 500 * (Math.random() - .5));
      testSet2.addElement(j);
    testSet2.diag();
    testSet2.skipDiag();
    }
    testSet2.diag();
    testSet2.skipDiag();
    
    ArrayList<Integer> list = testSet2.getContents();
    
    for (int i = 1; i < list.size(); i += 2){
      testSet2.removeElement(list.get(i));
      testSet2.diag();
      testSet2.skipDiag();
    }

    
    
    }
}
