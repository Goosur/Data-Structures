import java.util.ArrayList;
import java.util.Collections;

public class AssignmentSeven {

    /**
     * Fills an array with 1000 integers.
     * @param a Array to fill
     * @param random Select whether array values are randomized
     */
    public static void fillArray(ArrayList<Integer> a, boolean random) {

        for (int i = 0; i < 1000; i++) {
            a.add(i);
        }

        if (random) {
            Collections.shuffle(a);
        }
    
    }

    /**
     * Fills a tree with values from an array.
     * @param a Array to fill tree with
     * @param t Tree to fill
     */
    public static void fillTreeWithArray(ArrayList<Integer> a, IntSearchTree t) {
    
        for (Integer i : a) {
            t.insert(i);
        }
    
    }

    public static void main(String[] args) {

        // Initialize array and tree
        ArrayList<Integer> array = new ArrayList<>();
        IntSearchTree tree = new IntSearchTree();

        // Fill array and tree
        fillArray(array, true); // false for part a, true for part b
        fillTreeWithArray(array, tree);

        // Do some things to the tree
        TreeUtilities.printTree(tree);
        System.out.println(TreeUtilities.getTreeHeight(tree));

    }

}
