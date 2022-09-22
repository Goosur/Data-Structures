public class UnlimitedArrayCircle implements UnlimitedArray {
  private int elements = 0;
  private int first = 0;
  private int last = -1;
  private int[] array;

  public UnlimitedArrayCircle() {
    this.array = new int[4];
  }

  @Override
  public boolean isEmpty() {
    return this.elements == 0;
  }

  @Override
  public int getFirst() {
    if (this.isEmpty())
      throw new IndexOutOfBoundsException("Array is empty.");
    return this.array[this.first];
  }

  @Override
  public int getLast() {
    if (this.isEmpty())
      throw new IndexOutOfBoundsException("Array is empty.");
    return this.array[this.last];
  }

  @Override
  public void add(int number) {
    // If array is full we resize
    if (this.elements == this.array.length) {
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
    if (this.isEmpty())
      throw new IndexOutOfBoundsException("Array is empty.");

    int item = this.array[this.first];
    // Increment first pointer to "remove" first element
    this.first = (this.first + 1) % this.array.length;
    // Update number of elements
    this.elements--;
    return item;
  }

  @Override
  public int removeLast() {
    if (this.isEmpty())
      throw new IndexOutOfBoundsException("Array is empty.");

    int item = this.array[this.last];
    // Decrement last pointer to "remove" last element
    this.last = (this.last - 1 + this.array.length) % this.array.length;
    // Update number of elements
    this.elements--;
    return item;
  }

  public void resize() {
    int[] newArray = new int[this.array.length * 2];

    // If elements wrap around the array we need to copy the elements to the new
    // array while leaving no gaps in
    // elements between first and last pointers
    if (this.first > this.last) {
      int j = 0;
      // Copy elements from first pointer to end of array first
      for (int i = this.first; i < this.array.length; i++) {
        newArray[j] = this.array[i];
        j++;
      }
      // Copy elements from the start of the old array to the last pointer
      for (int i = 0; i <= this.last; i++) {
        newArray[j] = this.array[i];
        j++;
      }
      // Update first and last pointers
      this.first = 0;
      this.last = this.elements - 1;
    } else { // If elements don't wrap around array we just copy them over directly
      // Directly copy
      for (int i = this.first; i <= this.last; i++)
        newArray[i] = this.array[i];
    }
    // Store new array
    this.array = newArray;
  }
}
