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
        data = new ArrayList<>();
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
     * @param newData
     */
    public void insert(T newData) {

        // Check if initial data array is empty
        boolean empty = this.data.isEmpty();
        data.add(newData);
        
        if (!empty) {
        
            int i = parent(data.indexOf(newData));
            int j = data.indexOf(newData);

            while (data.get(i).compareTo(newData) < 0 && i >= 0) {
                data.set(j, data.get(i));
                data.set(i, newData);

                i = parent(data.indexOf(newData));
                j = data.indexOf(newData);
            }
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

        // TODO: Unbelievable close...
        T max;
        
        if (this.data.isEmpty()) {

            max = null;
            
        } else {

            T tempMax = this.data.get(0);

            int i = parent(this.data.size() - 1);
            T j = this.data.get(this.data.size() - 1);
            
            while (j.compareTo(tempMax) != 0) {

                System.out.println(i);
                T temp = this.data.get(i);

                this.data.set(i, j);

                i = parent(i);
                j = temp;

            }
 
            max = j;
            this.data.remove(this.data.size() - 1);

        }

        return max;
    }
    
}
