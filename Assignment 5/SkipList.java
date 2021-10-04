/**
 * Represents a SkipList.
 * Supports any comparable type. 
 * 
 * @author Devon Gardner
 * @date Oct 3 2021
 */
public class SkipList<T extends Comparable> implements SearchList<T> {
    SNode start;
    SNode end;
    SNode nodeSentinalTop;
    int len;

    /**
     * Constructs an empty SkipList.
     */
    public SkipList() {
        this.start = null;
        this.end = null;
        this.nodeSentinalTop = new SNode<T>(null);
        this.nodeSentinalTop.setNext(this.start);
        this.len = 0;
    }

    /**
     * Searches for specific node starting from top level sentinal node.
     * @param data - Node data to search for. 
     * @return Target node. 
     */
    public SNode secretSearch(T data) {
        SNode nodeCurrent = nodeSentinalTop;

        // Until target node is found
        while (true) {
            // Is current node end of current layer? Is next node less than or equal to target node?
            while (nodeCurrent.getNext() != null && !(nodeCurrent.getNext().getData().compareTo(data) > 0)) {
                nodeCurrent = nodeCurrent.getNext();
            }
            
            // If the bottom of the skip list is reached
            if (nodeCurrent.getDown() != null) {
                nodeCurrent = nodeCurrent.getDown();
            }

            // (If at the bottom of the skip list) and (end of layer or next node is bigger than target node)
            if (nodeCurrent.getDown() == null && (nodeCurrent.getNext() == null || nodeCurrent.getNext().getData().compareTo(data) > 0)){
                return nodeCurrent;
            }
        }
    }

    /**
     * Insert given data into the skiplist.
     * @param data - Data to insert
     */
    @Override
    public void insert(T data) {
        SNode nodeNewCurrent = new SNode<T>(data);
        SNode nodeTarget = this.secretSearch(data);
        SNode nodeNewNext;

        // Put new node between target node and target node next
        nodeNewCurrent.setNext(nodeTarget.getNext());
        nodeNewCurrent.setPrevious(nodeTarget);
        if (nodeTarget.getNext() != null) { // If target node next is the end of the list we ignore setting previous
            nodeTarget.getNext().setPrevious(nodeNewCurrent);
        }
        nodeTarget.setNext(nodeNewCurrent);

        // If previous node is sentinal node then current node is the start of the list.
        if (nodeNewCurrent.getPrevious().getData() == null) {
            this.start = nodeNewCurrent;
        }

        // If next node is null then current node is the end of the list.
        if (nodeNewCurrent.getNext() == null) {
            this.end = nodeNewCurrent;
        }

        double randomValue = Math.random();

        // While we still need to insert another layer of the node
        while (randomValue > 0.5) {
            // Attach the new layer to the previous layer
            nodeNewNext = new SNode<T>(data);
            nodeNewCurrent.setUp(nodeNewNext);
            nodeNewNext.setDown(nodeNewCurrent);
            nodeNewCurrent = nodeNewNext;

            // While we can't move up to the next layer from the target node
            while (nodeTarget.getUp() == null) {
                // If the top sentinal node is reached make a new layer
                if (nodeTarget.getPrevious() == null && nodeTarget.getUp() == null) {
                    SNode nodeSentinalNew = new SNode<T>(null);
                    this.nodeSentinalTop.setUp(nodeSentinalNew);
                    nodeSentinalNew.setDown(this.nodeSentinalTop);
                    this.nodeSentinalTop = nodeSentinalNew;
                } else { // Otherwise move back a node in the current layer
                    nodeTarget = nodeTarget.getPrevious();
                }
            }

            nodeTarget = nodeTarget.getUp();

            // Insert the new node in the new layer
            nodeNewCurrent.setNext(nodeTarget.getNext());
            nodeNewCurrent.setPrevious(nodeTarget);
            if (nodeTarget.getNext() != null) {
                nodeTarget.getNext().setPrevious(nodeNewCurrent);
            }
            nodeTarget.setNext(nodeNewCurrent);

            randomValue = Math.random();
        }

        this.len++;
    }

    /**
     * Search for given data in the SkipList
     * @param data - Data to search for
     * @return True if data found false if data not found
     */
    @Override
    public boolean search(T data) {
        SNode targetNode = this.secretSearch(data);
        if (targetNode.getData().compareTo(data) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove given data from the SkipList.
     * @param data - Data to be removed.
     * @return True if data found and successfully removed, false if data not found. 
     */
    @Override
    public boolean remove(T data) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Print elements of SkipList to console. 
     */
    @Override
    public void print() {
        SNode nodeCurrent = this.start;
        // While not at end of list print current character
        while (nodeCurrent != null) {
            System.out.print(nodeCurrent.getData() + " ");
            nodeCurrent = nodeCurrent.getNext();
        }
        System.out.println();
    }
}
