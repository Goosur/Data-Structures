/**
 * Double Linked list that is ordered.
 * 
 * @author Devon Gardner
 * @date Sep 21 2021
 */
public class OrderedLinked<T extends Comparable> implements SearchList<T> {
    DNode start;
    DNode end;
    int len;

    /**
     * Create an empty OrderedLinked list.
     */
    public void OrderedLinked() {
		this.start = null;
		this.end = null;
		this.len = 0;
    }

    /**
     * Insert given data at the correct position in the list.
     * @param data - Data to insert
     */
    @Override
    public void insert(T data) {
        DNode newDNode = new DNode<T>(data);
        DNode current = this.start;

        // If list is empty make the inserted data the start and end of the list.
        if (current == null) {
            this.start = newDNode;
            this.end = newDNode;
        }
        // Else If inserting at the start of the list make the inserted data the new start.
        else if (current.getData().compareTo(data) > 0) {   
            newDNode.setNext(current);
            current.setPrevious(newDNode);
            this.start = newDNode;
        }
        // Else look for place to insert and do it. 
        else {
            while ((current.getNext() != null) && (current.getNext().getData().compareTo(data) < 0)) {
                current = current.getNext();
            }

            // If inserting at end of list make node the end node.
            if (current.getNext() == null) {
                this.end = newDNode;
            } else {
                current.getNext().setPrevious(newDNode);
            }

            newDNode.setNext(current.getNext());
            newDNode.setPrevious(current);
            current.setNext(newDNode);
        }

        this.len++;
    }

    /**
     * Remove a node from the list.
     * @param data - Node data to search for and remove. 
     * @return True if node found and false if node not found.
     */
    @Override
    public boolean remove(T data) {
        DNode current = null;

        // If start of linked list is node to remove do special thing
        if (this.start == null) {
            return false;
        } else if (this.start.getData().equals(data)) {
            this.start = this.start.getNext();
            return true;
        } else { // else go through everything as normal and look for first instance
            current = this.start.getNext();

            // Have we reached end of list yet?
            while (current != null) {
                if (current.getData().equals(data)) { // Current node is a node of interest
                    current.getPrevious().setNext(current.getNext());
                    this.len--;
                    return true;
                } else { // Move on to next node in linked list
                    current = current.getNext();
                }
            }
        }

        return false;
    }

    /**
     * Search for specific node data in the list. 
     * @param data - Node data to search for.
     * @return True if data present false if data not present. 
     */
    @Override
    public boolean search(T data) {
        DNode current = this.start;

        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            } else {
                current = current.getNext();
            }
        }

        return false;
    }

    /**
     * Print out all the elements of the OrderedList.
     */
    @Override
    public void print() {
        DNode current = this.start;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
