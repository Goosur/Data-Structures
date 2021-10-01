/**
 * Linked List with some extra fun features.
 * 
 * @author Devon Gardner
 * @date Sep 10 2021
 */

public class ComplexLinked extends SimpleLinked {

    /**
     * Insert the given character at a particular location
     * in the list.
     * 
     * @param location - (int) Location in the list
     * @param data - (char) Character to insert
     */
    public void insert(int location, char data) {
        int count = 0;

        Node current = this.start;
        Node newNode = new Node(data);

        if (location == 0) {
            newNode.setNext(current);
            this.start = newNode;
        } else {
            while (count < location - 1) {
                current = current.getNext();
                count++;
            }

            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }

    /**
     * Remove the first instance of hte given character
     * from the list.
     * 
     * @param data - (char) Character to be removed.
     * @return (bool) true
     */
    public boolean remove(char data) {
        Node previous = null;
        Node current = null;

        // If start of linked list is node to remove do special thing
        if (this.start.getData() == data) {
            this.start = this.start.getNext();
            return true;
        } else { // else go through everything as normal and look for first instance
            previous = this.start;
            current = this.start.getNext();

            // Have we reached end of list yet?
            while (current != null) {
                if (current.getData() == data) { // Current node is a node of interest
                    previous.setNext(current.getNext());
                    return true;
                } else { // Move on to next node in linked list
                    previous = current;
                    current = current.getNext();
                }
            }
        }

        return false;
    }

    /**
     * Create a brand new ComplexLinked list that contains
     * the same characters as the original list, but in
     * the opposite order.
     * 
     * @return (ComplexLinked) reverse ordered list
     */
    public ComplexLinked reverse() {
        ComplexLinked newList = new ComplexLinked();
        Node current = this.start;

        while (current != null) {
            newList.insert(0, current.getData());
            current = current.getNext();
        }

        return newList;
    }
}
