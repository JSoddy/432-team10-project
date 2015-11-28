package unpredictablesets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Group 10
 */
public class UnpredictableSets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SkipListSet testSet1;
        SkipListSet testSet2;
        BufferedReader reader = null;
        
        String sizes = "";
        String normalFinds = "";
        String circularFinds = "";
        String exploitedFinds = "";
        String circularExploitedFinds = "";
        String normalAdds = "";
        String circularAdds = "";
        String exploitedAdds = "";
        String circularExploitedAdds = "";
        String normalRemoves = "";
        String circularRemoves = "";
        String exploitedRemoves = "";
        String circularExploitedRemoves = "";
        
        /*
        try {
            reader = new BufferedReader(new FileReader("geometric.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnpredictableSets.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] strings = null;
        String string = "";

        try {
            string = reader.readLine();
            string = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(UnpredictableSets.class.getName()).log(Level.SEVERE, null, ex);
        }
        strings = string.trim().split(",");
         */
        int max = 0;
        
        int[] numbers = new int[10000000];
        for (int i = 0; i < numbers.length; i++){
            numbers[i] = (int) ((Math.random() - .5) * 100000000);
        }
        
        for (int k = 2; k < numbers.length; k = k * 2 ) {
            System.out.println(k);
            testSet1 = new SkipListSet();
            testSet2 = new CircularSkipListSet();
            int j = 0;
            /*
            for (int i = 0; i < k; i++) {
                try {
                    j = Integer.parseInt(strings[i]);
                    max = j;
                } catch (NumberFormatException e) {
                    //
                }
                if (j != 0) {
                    testSet1.addElement(j);
                    testSet2.addElement(j);
                }
            }
            */
            for (int i = 0; i < k - 1; i++){
                testSet1.addElement(numbers[i]);
                testSet2.addElement(numbers[i]);
            }
            sizes = String.format("%s,%d", sizes, testSet1.getSize());
                
            long time1 = 0;
            long time2 = 0;
            Timestamp timer;

            ArrayList<Integer> contents = testSet1.getContents();
            testSet2.getContents();

            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.isInSet(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.isInSet(j);
                time2 += System.nanoTime() - timer.getTime();
            }
            normalFinds = String.format("%s,%d", normalFinds, time1);
            circularFinds = String.format("%s,%d", circularFinds, time2);
            
            time1 = 0;
            time2 = 0;
            long time3 = 0;
            long time4 = 0;
            
            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.addElement(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.addElement(j);
                time2 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet1.removeElement(j);
                time3 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.removeElement(j);
                time4 += System.nanoTime() - timer.getTime();
            }
            
            normalAdds = String.format("%s,%d", normalAdds, time1);
            circularAdds = String.format("%s,%d", circularAdds, time2);
            normalRemoves = String.format("%s,%d", normalRemoves, time3);
            circularRemoves = String.format("%s,%d", circularRemoves, time4);
            

            for (int i = 1; i < contents.size(); i += 2) {
                testSet1.removeElement(contents.get(i));
                testSet2.removeElement(contents.get(i));
            }
            
            time1 = 0;
            time2 = 0;

            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.isInSet(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.isInSet(j);
                time2 += System.nanoTime() - timer.getTime();
            }

            
            exploitedFinds = String.format("%s,%d", exploitedFinds, time1);
            circularExploitedFinds = String.format("%s,%d", circularExploitedFinds, time2);
            
            time1 = 0;
            time2 = 0;
            time3 = 0;
            time4 = 0;
            
            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.addElement(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.addElement(j);
                time2 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet1.removeElement(j);
                time3 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.removeElement(j);
                time4 += System.nanoTime() - timer.getTime();
            }
            
            exploitedAdds = String.format("%s,%d", exploitedAdds, time1);
            circularExploitedAdds = String.format("%s,%d", circularExploitedAdds, time2);
            exploitedRemoves = String.format("%s,%d", exploitedRemoves, time3);
            circularExploitedRemoves = String.format("%s,%d", circularExploitedRemoves, time4);
        }
        testSet1 = new SkipListSet();
            testSet2 = new CircularSkipListSet();
            int j = 0;
            /*
            for (int i = 0; i < strings.length; i++) {
                try {
                    j = Integer.parseInt(strings[i]);
                } catch (NumberFormatException e) {
                    //
                }
                if (j != 0) {
                    testSet1.addElement(j);
                    testSet2.addElement(j);
                }
            }
                    */
            
            for (int i = 0; i < numbers.length; i++){
                testSet1.addElement(numbers[i]);
                testSet2.addElement(numbers[i]);
            }
            sizes = String.format("%s,%d", sizes, testSet1.getSize());

            long time1 = 0;
            long time2 = 0;
            Timestamp timer;

            ArrayList<Integer> contents = testSet1.getContents();
            testSet2.getContents();

            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.isInSet(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.isInSet(j);
                time2 += System.nanoTime() - timer.getTime();
            }
            normalFinds = String.format("%s,%d", normalFinds, time1);
            circularFinds = String.format("%s,%d", circularFinds, time2);
            
            time1 = 0;
            time2 = 0;
            long time3 = 0;
            long time4 = 0;
            
            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.addElement(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.addElement(j);
                time2 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet1.removeElement(j);
                time3 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.removeElement(j);
                time4 += System.nanoTime() - timer.getTime();
            }
            
            normalAdds = String.format("%s,%d", normalAdds, time1);
            circularAdds = String.format("%s,%d", circularAdds, time2);
            normalRemoves = String.format("%s,%d", normalRemoves, time3);
            circularRemoves = String.format("%s,%d", circularRemoves, time4);

            for (int i = 1; i < contents.size(); i += 2) {
                testSet1.removeElement(contents.get(i));
                testSet2.removeElement(contents.get(i));
            }

            time1 = 0;
            time2 = 0;

            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.isInSet(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.isInSet(j);
                time2 += System.nanoTime() - timer.getTime();
            }

            exploitedFinds = String.format("%s,%d", exploitedFinds, time1);
            circularExploitedFinds = String.format("%s,%d", circularExploitedFinds, time2);
            
            time1 = 0;
            time2 = 0;
            time3 = 0;
            time4 = 0;
            
            for (int i = 0; i < 50; i ++) {
                j = (int) ((Math.random() - .5) * max * 2);
                timer = new Timestamp(System.nanoTime());
                testSet1.addElement(j);
                time1 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.addElement(j);
                time2 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet1.removeElement(j);
                time3 += System.nanoTime() - timer.getTime();
                timer = new Timestamp(System.nanoTime());
                testSet2.removeElement(j);
                time4 += System.nanoTime() - timer.getTime();
            }
            
            exploitedAdds = String.format("%s,%d", exploitedAdds, time1);
            circularExploitedAdds = String.format("%s,%d", circularExploitedAdds, time2);
            exploitedRemoves = String.format("%s,%d", exploitedRemoves, time3);
            circularExploitedRemoves = String.format("%s,%d", circularExploitedRemoves, time4);
            
            System.out.println("Size" + sizes);
            System.out.println("Finds" + normalFinds);
            System.out.println("Adds" + normalAdds);
            System.out.println("Removes" + normalRemoves);
            System.out.println("Circular Finds" + circularFinds);
            System.out.println("Circular Adds" + circularAdds);
            System.out.println("Circular Removes" + circularRemoves);
            System.out.println("Exploited Finds" + exploitedFinds);
            System.out.println("Exploited Adds" + exploitedAdds);
            System.out.println("Exploited Removes" + exploitedRemoves);
            System.out.println("Exploited Circular Finds" + circularExploitedFinds);
            System.out.println("Exploited Circular Adds" + circularExploitedAdds);
            System.out.println("Exploited Circular Removes" + circularExploitedRemoves);     
    }
}
