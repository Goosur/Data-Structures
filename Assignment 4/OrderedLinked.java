/**
 * Double Linked list that is ordered.
 * 
 * @author Devon Gardner
 * @date Sep 21 2021
 */
public class OrderedLinked<T extends Comparable> extends DoubleLinked {

    /**
     * Insert given data at the correct position in the list.
     * @param data - Data to insert
     */
    public void insert(T data) {
        DNode newDNode = new DNode<T>(data);
        DNode current = start;


        if (start == null) {
            start = newDNode;
            end = newDNode;
        } else if (current.getData().compareTo(data) > 0) {   
            newDNode.setNext(current);
            current.setPrevious(newDNode);
            start = newDNode;
        } else {
            while ((current.getNext() != null) && (current.getNext().getData().compareTo(data) < 0)) {
                current = current.getNext();
            }

            if (current.getNext() == null) {
                end = newDNode;
            } else {
                current.getNext().setPrevious(newDNode);
            }

            newDNode.setNext(current.getNext());
            newDNode.setPrevious(current);
            current.setNext(newDNode);
        }
    }

    public boolean remove(T data) {
        DNode current = null;

        // If start of linked list is node to remove do special thing
        if (start == null) {
            return false;
        } else if (start.getData().equals(data)) {
            start = start.getNext();
            return true;
        } else { // else go through everything as normal and look for first instance
            current = this.start.getNext();

            // Have we reached end of list yet?
            while (current != null) {
                if (current.getData().equals(data)) { // Current node is a node of interest
                    current.getPrevious().setNext(current.getNext());
                    return true;
                } else { // Move on to next node in linked list
                    current = current.getNext();
                }
            }
        }

        return false;
    }

    public boolean search(T data) {
        DNode current = start;

        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            } else {
                current = current.getNext();
            }
        }

        return false;
    }

}
