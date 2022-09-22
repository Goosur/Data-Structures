public class UnlimitedArrayCircle implements UnlimitedArray {

    private int elements = 0;
    private int first = 0;
    private int last = -1;
    private int[] array = new int[4];

    @Override
    public boolean isEmpty() {
        return this.elements == 0;
    }

    @Override
    public int getFirst() {
        if (this.elements == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.array[this.first];
    }

    @Override
    public int getLast() {
        if (this.elements == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.array[this.last];
    }

    @Override
    public void add(int number) {
        if (this.entries == this.array.length) {
            resize();
        }
        // Update last pointer
        this.last = (this.last + 1) % this.array.length;
        // Add new element at new last pointer
        this.array[this.last] = number;
        // Update number of elements
        this.elements++;
    }

    @Override
    public int removeFirst() {
        if (this.elements == 0) {
            throw new IndexOutOfBoundsException();
        }
        int item = this.array[this.first];
        // Increment first pointer to "remove" first element
        this.first = (this.first + 1) % this.array.length;
        // Update number of elements
        this.elements--;
        return item;
    }

    @Override
    public int removeLast() {
        if (this.elements == 0) {
            throw new IndexOutOfBoundsException();
        }
        int item = this.array[this.last];
        // Decrement last pointer to "remove" last element
        this.last = (this.last - 1 + this.array.length) % this.array.length;
        // Update number of elements
        this.elements--;
        return item;
    }

    public void resize() {
        int[] newArray = new int[this.array.length * 2];
        int j = 0;
        // We need to copy the elements to the new array while leaving no gaps in elements between first and last pointers
        // Copy elements from first pointer to end of array first
        for (int i = this.first; i < this.array.length; i++) {
            newArray[j] = this.array[i];
            j++;
        }
        // Copy elements from the start of the old array to the last pointer
        for (int i = 0; i <= this.last; i++) {
            newArray[j] = this.array[i];
            j++
        }
        // Update first and last pointers
        this.first = 0;
        this.last = this.elements - 1;
        // Store new array
        this.array = newArray;
    }
}
