public class UnlimitedArrayFast implements UnlimitedArray {

    private int elements = 0;
    private int[] array = new int[4];

    @Override
    public boolean isEmpty() {
        return this.elements == 0;
    }

    @Override
    public int getFirst() {
        return this.array[0];
    }

    @Override
    public int getLast() {
        return this.array[this.elements - 1];
    }

    @Override
    public void add(int number) {
        if (this.entries == this.array.length) {
            this.resize();
        }
        array[this.elements] = number;
        this.elements++;
    }

    @Override
    public int removeFirst() {
        int item = this.array[0];
        for (int i = 1; i < this.elements; i++) {
            this.array[i - 1] = this.array[i];
        }
        this.elements--;
        return item;
    }

    @Override
    public int removeLast() {
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
