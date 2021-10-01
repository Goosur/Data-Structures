public class UnlimitedArrayFast implements UnlimitedArray {

    private int entries = 0;
    private int first = 0;
    private int last = 0;
    private int[] array = new int[4];

    @Override
    public boolean isEmpty() {
        if (entries == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getFirst() {
        return array[first];
    }

    @Override
    public int getLast() {
        return array[last];
    }

    @Override
    public void add(int number) {
        entries++;
        if (entries == array.length + 1) {
            resize();
        }
        array[last] = number;
        last++;
    }

    @Override
    public int removeFirst() {
        entries--;
        int item = array[first];
        first++;
        return item;
    }

    @Override
    public int removeLast() {
        entries--;
        last--;
        int item = array[last];
        return item;
    }

    public void resize() {
        int[] newArray = new int[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }
}
