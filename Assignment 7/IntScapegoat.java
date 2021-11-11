import java.util.ArrayList;
import java.util.Collections;

public class IntScapegoat implements IntDataSet {

    public IntNode root;

    public IntScapegoat() {
        this.root = null;
    }

    @Override
    public void insert(int data) {
       
        IntNode newNode = new IntNode(data);
        int currentHeight = 1;

        if (this.root == null) {
            this.root = newNode; 
            this.root.setHeight(1);
            this.root.setNNodes(1);
        } else {
            ArrayList<IntNode> nodes = new ArrayList<IntNode>();
            nodes.add(this.root);
          
            // While nodes are still queued for checking to insert
            while (nodes.size() > 0) {
                IntNode currentNode = nodes.get(0);

                // Is new data greater than or equal to current node data?
                if (data >= currentNode.getData()) {

                    // If current node's right child is null, insert there.
                    if (currentNode.getRight() == null) {
                        
                        // Increment height of all parents if we are inserting at the deepest point in the tree.
                        if (currentHeight == this.root.getHeight()) {
                            IntNode parent = currentNode;
                            while (parent != null) {
                                parent.setHeight(parent.getHeight() + 1);
                                parent = parent.getParent();
                            }
                        }
                        
                        // Insert new node
                        currentNode.setRight(newNode);
                        currentNode.getRight().setParent(currentNode);

                        // While parent isn't null add one to parent's number of nodes and move to next parent
                        IntNode parent = currentNode;
                        while (parent != null) {
                            parent.setNNodes(parent.getNNodes() + 1);
                            parent = parent.getParent();
                        }

                        // Increment height of all parents if currentNode is a leaf and not the root.
                        if (currentNode.isLeaf() && currentNode != this.root) {
                            parent = currentNode;
                            while (parent != null) {
                                parent.setHeight(parent.getHeight() + 1);
                                parent = parent.getParent();
                            }
                        }
                    
                    } else { // If current node's right child is not null, add it to the queue.
                        nodes.add(currentNode.getRight());
                    }
                }

                // Is new data less than current node data?
                if (data < currentNode.getData()) {

                    // If current node's left child is null, insert there.
                    if (currentNode.getLeft() == null) {
                        
                        // Increment height of all parents if we are inserting at the deepest point in the tree.
                        if (currentHeight == this.root.getHeight()) {
                            IntNode parent = currentNode;
                            while (parent != null) {
                                parent.setHeight(parent.getHeight() + 1);
                                parent = parent.getParent();
                            }
                        }

                        // Insert new node 
                        currentNode.setLeft(newNode);
                        currentNode.getLeft().setParent(currentNode);

                        // While parent isn't null add one to parent's number of nodes and move to next parent
                        IntNode parent = currentNode;
                        while (parent != null) {
                            parent.setNNodes(parent.getNNodes() + 1);
                            parent = parent.getParent();
                        }

                    } else { // If current node's left child is not null, add it to the queue.
                        nodes.add(currentNode.getLeft());
                    }
                }

                // Increment height
                currentHeight++;

                // Once current node has been processed remove it from the queue.
                nodes.remove(currentNode);
            }
            /* 
            // Once inserting is done move up the tree and check if rebuilding is needed
            IntNode parent = newNode.getParent();
            boolean found = false;
            while (parent != null && !found) {
                if (Math.pow(1.5, parent.getHeight()) > parent.getNNodes()) {
                    rebuild(parent);
                    found = true;
                }
            }*/
        }
    }

    public void rebuild(IntNode root) {

        // Get initial nodes into node list.
        ArrayList<IntNode> nodes = new ArrayList<IntNode>();

        if (root.getRight() != null) {
            nodes.add(root.getRight());
        }

        if (root.getLeft() != null) {
            nodes.add(root.getLeft());
        }

        System.out.println("Initial array size: " + nodes.size());

        // Collect all nodes from scapegoat tree.
        int i = 0;
        System.out.println("Subtree Size: " + (root.getNNodes() - 1));
        while (nodes.size() < root.getNNodes() - 1) {
            System.out.println("Current node: " + i);
            System.out.println("Current node a leaf?: " + nodes.get(i).isLeaf());
            System.out.println("Current array size: " + nodes.size());
            // Add all non null children of all nodes in nodes list
            if (nodes.get(i).getRight() != null) {
                nodes.add(nodes.get(i).getRight());
            }

            if (nodes.get(i).getLeft() != null) {
                nodes.add(nodes.get(i).getLeft());
            }

            // Move to next node in nodes list
            i++;
        }

        // Shuffle nodes array to get decent mix for balanced rebuild
        Collections.shuffle(nodes);

        // Sever scapegoat root from parent to not break main tree heights and node counts
        IntNode rootParent = root.getParent();
        root.setParent(null);

        // Remove scapegoat root's children
        root.setRight(null);
        root.setLeft(null);

        // Make temporary tree for root node to insert and track subtree height and nodes
        // Root node should be passed by reference so it should rebuild in original tree too???
        IntScapegoat tempTree = new IntScapegoat();
        tempTree.root = root;

        // Add shuffled nodes to new tree
        for (IntNode node : nodes) {
            tempTree.insert(node.getData());
        }

        // Remove root from temp tree so it gets garbage collected
        tempTree.root = null;

        // Reattach root parents
        root.setParent(rootParent);
        
        // Correct height for all parents
        IntNode current = root;
        while (root.getParent() != null) {
            current.getParent().setHeight(current.getHeight() + 1);
            current = current.getParent();
        }
    }

    @Override
    public boolean search(int target) {
        return false;
    }

    public static void main(String[] args) {
        IntScapegoat sTree = new IntScapegoat();
        int[] ints = {17, 13, 8, 10, 11, 4, 9, 7, 1, 18};
        for (int i : ints) {
            sTree.insert(i);
        }

        ArrayList<IntNode> nodes = new ArrayList<IntNode>();
        nodes.add(sTree.root);

        for (int i = 0; i < sTree.root.getNNodes(); i++) {
            if (nodes.get(i).getLeft() != null) {
                nodes.add(nodes.get(i).getLeft());
            }

            if (nodes.get(i).getRight() != null) {
                nodes.add(nodes.get(i).getRight());
            }
        }
        

        for (IntNode node : nodes) {
            System.out.println("Data: " + node.getData() + ", Height: " + node.getHeight() + ", Nodes: " + node.getNNodes());
        }


        // Make sure insert is probably working and properly keeping track of height and number of nodes per sub tree.
        //System.out.println("Root Data: " + sTree.root.getData() + ", Root Height: " + sTree.root.getHeight() + ", Root Nodes: " + sTree.root.getNNodes());
        //System.out.println("Left Data: " + sTree.root.getLeft().getData() + ", Left Height: " + sTree.root.getLeft().getHeight() + ", Left Nodes: " + sTree.root.getLeft().getNNodes());
        //System.out.println("Right Data: " + sTree.root.getRight().getData() + ", Right Height: " + sTree.root.getRight().getHeight() + ", Right Nodes: " + sTree.root.getRight().getNNodes());
    }
}
