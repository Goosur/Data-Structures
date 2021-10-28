//IMPORTANT: You will modify this class to add functions
//the functions that you need to add are described in the assignment document

//All functions in this class are static.
//This class is never intended to be constructed.

import java.util.Random;

public abstract class TreeUtilities {

    public static void printTreeHelper(IntNode nd) {
		if (nd != null) {
			printTreeHelper(nd.getLeft());
			System.out.print(nd.getData());
			System.out.print(" ");
			printTreeHelper(nd.getRight());
		}
    }

    //This function prints all the data an IntSearchTree in order
    //Starting with the lowest and going to the highest
    public static void printTree(IntSearchTree tree) {
		printTreeHelper(tree.getRoot());
		System.out.println();
    }

    //This function creates a IntSearchTree filled with randomly chosen data
    //num is the number of nodes in the random search tree
    //max_val is the upper limit on the randomly chosen integer data
    public static IntSearchTree makeRandomTree(int num, int max_val) {

		Random rand = new Random();
		IntSearchTree tree = new IntSearchTree();

		for (int i=0; i<num; i++) {
			int rand_data = rand.nextInt(max_val);
			tree.insert(rand_data);
		}

		return tree;
    }

	/**
	 * Returns the largest integer stored in given tree.
	 * @param the_tree (IntSearchTree) tree of interest
	 * @return (int) the largest integer stored in the_tree
	 */
	public static int getMax(IntSearchTree the_tree) {

		IntNode current = the_tree.getRoot();
		while (current.getRight() != null) {
			current = current.getRight();
		}

		return current.getData();
	}

	/**
	 * Finds target node in tree.
	 * @param nd (IntNode) starting node
	 * @param target (int) node data to search for
	 * @return (IntNode) target node
	 */
	private static IntNode findNode(IntNode nd, int target) {

		// TODO: maybe need to fix something here
		if (nd.getData() == target) {
			return nd;
		}

		if (target > nd.getData()) {

			//Find larger values on the right
			if (nd.getRight() != null) {
				return findNode(nd.getRight(), target);
			}
		}

		if (target < nd.getData()) {

			//Find smaller values on the left
			if (nd.getLeft() != null) {
				return findNode(nd.getLeft(), target);
			}
		}

		return null;
	}

	/**
	 * Find the first node that stores the target integer and return the height of that node.
	 * @param the_tree (IntSearchTree) tree to search and get height of
	 * @param target (int) value to search for and find height of
	 * @return (int) height of target in tree
	 */
	public static int getNodeHeight(IntSearchTree the_tree, int target) {

		// Find distance from target node to root
		IntNode currentNode = findNode(the_tree.getRoot(), target);

		if (currentNode == null) {
			return -1;
		}

		int height = 1;

		// Increment height until root reached.
		while (currentNode.getParent() != null) {
			height++;
			currentNode = currentNode.getParent();
		}

		return height;
	}

	// TODO: fix
	public static int getTreeHeight(IntSearchTree the_tree) {
		return 0;
	}

	/**
	 * Find target in tree and get next int.
	 * @param the_tree (IntSearchTree) tree of interest
	 * @param target (int) node target value
	 * @return (int) next int
	 */
	public static int getNextInt(IntSearchTree the_tree, int target) {

		IntNode targetNode = findNode(the_tree.getRoot(), target);

		if (targetNode == null || targetNode.getRight() == null) {
			throw new RuntimeException();
		} else {
			return targetNode.getRight().getData();
		}
	}

    //Don't hesitate to modify this code, it is only here for testing purposes
    public static void main(String[] args) {

		IntSearchTree tree = makeRandomTree(10, 20);
		printTree(tree);

		System.out.println("Is 5 in the tree?");
		System.out.println(tree.search(5));
		System.out.println(TreeUtilities.getNodeHeight(tree, 5));
		System.out.println(TreeUtilities.getNextInt(tree, 5));

		System.out.println("Is 7 in the tree?");
		System.out.println(tree.search(7));
		System.out.println(TreeUtilities.getNodeHeight(tree, 7));
		System.out.println(TreeUtilities.getNextInt(tree, 7));

		System.out.println("Is 18 in the tree?");
		System.out.println(tree.search(18));
		System.out.println(TreeUtilities.getNodeHeight(tree, 18));
		System.out.println(TreeUtilities.getNextInt(tree, 18));
	}
}
