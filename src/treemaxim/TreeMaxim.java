/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemaxim;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Maxim
 */
public class TreeMaxim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryTree<String> myTree=new BinaryTree<>();
        myTree.addRoot("Der Ursprung");
        Position<String> childR=myTree.addRight(myTree.root(),"Maxim");
        Position<String> childL=myTree.addLeft(myTree.root(),"Rebecca");
        
        myTree.addLeft(childR, "Benji1");
        try {
            myTree.addLeft(childR, "f");
        } catch (IllegalArgumentException exc) {
            System.out.println(exc);
            myTree.addRight(childR, "Benji2");
        }
        
        
        myTree.addLeft(childL, "Benjibee");
        
        Iterator<String> iterator=myTree.iterator();
        print(iterator);
        
        myTree.remove(childL);
        Iterator<String> iterator2=myTree.iterator();
        print(iterator2);
        
    }
    
    public static void print(Iterator<String> iterator) {
       while(iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove(); // has no effect on iterator2
        } 
    }
    
}
