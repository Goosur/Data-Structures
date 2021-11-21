import java.util.ArrayList;

/**
 * Implements a heap (priority queue)
 * The heap can store any comparable object
 *
 * For convenience we use an {@link ArrayList}
 * The {@link ArrayList} implementation handles the re-sizing of the array
 * which simplifies our code a bit
 *
 * This is a MaxHeap which means that the largest value is stored in the root
 * and the value at a node is larger than the values of its children
 */
public class GenericMaxHeap<T extends Comparable<T>>{

    ArrayList<T> data;
    int size;

    /**
     * Create an empty max heap
     */
    public GenericMaxHeap(){
        size = 0;
        data = new ArrayList<T>();
    }

    /**
     * Takes in an index and returns the index of the left child
     * @param index location in the array
     * @return index of left child
     */
    int left(int index) {
        return (2 * index) + 1;
    }

    /**
     * Takes in an index and returns the index of the right child
     * @param index location in the array
     * @return index of right child
     */
    int right(int index) {
        return (2 * index) + 2;
    }

    /**
     * Takes in an index and returns the index of the parent
     * @param index a location in the array
     * @return index of the parent
     */
    int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Inserts new data
     * @param new_data
     */
    public void insert(T new_data) {

        // TODO: Need to do this still
    }
    
    /**
     * Returns largest item in the heap
     * @return item
     */
    public T getMax() {
        return this.data.get(0);
    }

    /**
     * Removes the largest item from the heap
     * @return removed item
     */
    public T removeMax() {

        // TODO: Don't return null, change this line!
        return null;
    }
    
}
