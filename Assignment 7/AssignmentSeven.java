import java.util.ArrayList;
import java.util.Collections;

public class AssignmentSeven {

    public static void fillArray(ArrayList<Integer> a, boolean random) {

        for (int i = 0; i < 1000; i++) {
            a.add(i);
        }

        if (random) {
            Collections.shuffle(a);
        }
    }

    public static void fillTreeWithArray(ArrayList<Integer> a, IntSearchTree t) {
        for (Integer i : a) {
            t.insert(i);
        }
    }

    public static void main(String[] args) {

        ArrayList<Integer> array = new ArrayList<>();
        IntSearchTree tree = new IntSearchTree();

        fillArray(array, true);
        fillTreeWithArray(array, tree);

        TreeUtilities.printTree(tree);
        System.out.println(TreeUtilities.getTreeHeight(tree));

    }

}
