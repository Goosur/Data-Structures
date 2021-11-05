import java.util.ArrayList;

public class IntScapegoat implements IntDataSet {

    public IntNode root;

    public IntScapegoat() {
        this.root = null;
    }

    @Override
    public void insert(int data) {
       
        IntNode newNode = new IntNode(data);

        if (root == null) {
            root = newNode; 
            root.setHeight(1);
            root.setNNodes(1);
        } else {
            ArrayList<IntNode> nodes = new ArrayList<IntNode>();
            nodes.add(root);
          
            // While nodes are still queued for checking to insert
            while (nodes.size() > 0) {
                IntNode currentNode = nodes.get(0);

                // Is new data greater than or equal to current node data?
                if (data >= currentNode.getData()) {

                    // If current node's right child is null, insert there.
                    if (currentNode.getRight() == null) {
                        
                        // Increment height of all parents if currentNode is a leaf and not the root.
                        if (currentNode.isLeaf()) {
                            IntNode parent = currentNode;
                            while (parent != null) {
                                parent.setHeight(parent.getHeight() + 1);
                                parent = parent.getParent();
                            }
                        }
                        
                        currentNode.setRight(newNode);
                        currentNode.getRight().setParent(currentNode);
                        currentNode.getRight().setHeight(1);
                        currentNode.getRight().setNNodes(1);

                        // While parent isn't null add one to parent's number of nodes and move to next parent
                        IntNode parent = currentNode;
                        while (parent != null) {
                            parent.setNNodes(parent.getNNodes() + 1);
                            parent = parent.getParent();
                        }

                        // Increment height of all parents if currentNode is a leaf and not the root.
                        if (currentNode.isLeaf() && currentNode != root) {
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
                        
                        // Increment height of all parents if currentNode is a leaf and not the root.
                        if (currentNode.isLeaf()) {
                            IntNode parent = currentNode;
                            while (parent != null) {
                                parent.setHeight(parent.getHeight() + 1);
                                parent = parent.getParent();
                            }
                        }

                        // Insert new node and do the stuff
                        currentNode.setLeft(newNode);
                        currentNode.getLeft().setParent(currentNode);
                        currentNode.getLeft().setHeight(1);
                        currentNode.getLeft().setNNodes(1);

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

                // Once current node has been processed remove it from the queue.
                nodes.remove(currentNode);
            }
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

        System.out.println("Root Data: " + sTree.root.getData() + ", Root Height: " + sTree.root.getHeight() + ", Root Nodes: " + sTree.root.getNNodes());
        System.out.println("Left Data: " + sTree.root.getLeft().getData() + ", Left Height: " + sTree.root.getLeft().getHeight() + ", Left Nodes: " + sTree.root.getLeft().getNNodes());
        System.out.println("Right Data: " + sTree.root.getRight().getData() + ", Right Height: " + sTree.root.getRight().getHeight() + ", Right Nodes: " + sTree.root.getRight().getNNodes());
    }
}
