/**
 * This class represents a node in a Skip List
 * The class holds one character of data
 */
public class SNode<T extends Comparable> {
    private T data;
    private SNode next;
    private SNode previous;
    private SNode up;
    private SNode down;

    public SNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
        this.up = null;
        this.down = null;
    }

    public T getData() {
        return this.data;
    }

    public SNode getNext() {
        return this.next;
    }

    public SNode getPrevious() {
        return this.previous;
    }

    public SNode getUp() {
        return this.up;
    }

    public SNode getDown() {
        return this.down;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(SNode next) {
        this.next = next;
    }

    public void setPrevious(SNode previous) {
        this.previous = previous;
    }

    public void setUp(SNode up) {
        this.up = up;
    }

    public void setDown(SNode down) {
        this.down = down;
    }
}
