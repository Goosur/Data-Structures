public class UnlimitedArrayFast implements UnlimitedArray {
  private int elements = 0;
  private int[] array;

  public UnlimitedArrayFast() {
    this.array = new int[4];
  }

  @Override
  public boolean isEmpty() {
    return this.elements == 0;
  }

  @Override
  public int getFirst() {
    if (this.isEmpty())
      throw new ArrayIndexOutOfBoundsException("Array is empty.");

    return this.array[0];
  }

  @Override
  public int getLast() {
    if (this.isEmpty())
      throw new ArrayIndexOutOfBoundsException("Array is empty.");
    
    return this.array[this.elements - 1];
  }

  @Override
  public void add(int number) {
    // Check if array is full before adding new number
    if (this.elements == this.array.length)
      this.resize();

    // Insert the new element and increment element counter
    array[this.elements] = number;
    this.elements++;
  }

  @Override
  public int removeFirst() {
    // Make sure array isn't empty before proceeding
    if (this.isEmpty())
      throw new ArrayIndexOutOfBoundsException("Array is empty.");

    // Store first item before updating array
    int item = this.array[0];

    // Shift all elements left one
    for (int i = 1; i < this.elements; i++)
      this.array[i - 1] = this.array[i];

    // Decrement number of elements in array
    this.elements--;

    return item;
  }

  @Override
  public int removeLast() {
    // Make sure array isn't empty before proceeding
    if (this.isEmpty())
      throw new ArrayIndexOutOfBoundsException("Array is empty");
    int item = this.array[this.elements - 1];
    this.elements--;
    return item;
  }

  public void resize() {
    int[] newArray = new int[this.array.length * 2];
    for (int i = 0; i < this.array.length; i++) {
      newArray[i] = this.array[i];
    }

    this.array = newArray;
  }
}
