/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unpredictablesets;

import java.util.ArrayList;

/**
 *
 * @author James
 */
public class Visualization {

    public static void main(String[] args) {
        SkipListSet set1 = new SkipListSet();
        SkipListSet set2 = new CircularSkipListSet();
        
        for (int i = 0; i < 10; i++) {
            int j = (int) ((Math.random() - .5) * 199);
            set1.addElement(j);
            set2.addElement(j);
            System.out.println("\nAdding " + j + "\n");
            System.out.println("Normal:");
            set1.skipDiag();
            System.out.println("Circular:");
            set2.skipDiag();
        }
        
        for (int i = 0; i < 10; i++) {
            int j = (int) ((Math.random() - .5) * 199);
            set1.isInSet(j);
            set2.isInSet(j);
            System.out.println("\nFinding " + j + "\n");
            System.out.println("Normal:");
            set1.skipDiag();
            System.out.println("Circular:");
            set2.skipDiag();
        }
        
        System.out.println("\nDerandomizing Heights:\n");
        ArrayList<Integer> list = set1.getContents();
        
        
            System.out.println("Normal:");
            set1.skipDiag();
            System.out.println("Circular:");
            set2.skipDiag();
            
        System.out.println();
        
        for (int i = 1; i < list.size(); i += 2){
            set1.removeElement(list.get(i));
            set2.removeElement(list.get(i));
            System.out.println("\nRemoving " + list.get(i) + "\n");
            System.out.println("Normal:");
            set1.skipDiag();
            System.out.println("Circular:");
            set2.skipDiag();
        }
    }

}
