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
 
        data.add(new_data);
       
        int i = parent(data.indexOf(new_data));
        int j = data.indexOf(new_data);

        while (data.get(i).compareTo(new_data) < 0 && i >= 0) {
            data.set(j, data.get(i));
            data.set(i, new_data);

            i = parent(data.indexOf(new_data));
            j = data.indexOf(new_data);
        }

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

        // TODO: Keep working on this. Almost finished.
        T max = data.get(0);
        
        T temp = data.get(data.size() - 1);
        int i = parent(data.indexOf(temp));
        int j = data.indexOf(temp);

        while (i >= 0) {
            data.set(i, data.get(j))
        }

        return null;
    }
    
}
