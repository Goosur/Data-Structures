public class UnlimitedArrayCircle implements UnlimitedArray {

    private int entries = 0;
    private int first = 0;
    private int last = -1;
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
        last++;
        if (entries == array.length + 1) {
            resize();
        }
        if (last == array.length) {
            last = 0;
        }
        array[last] = number;
    }

    @Override
    public int removeFirst() {
        entries--;
        int item = array[first];
        first++;
        if (first == array.length) {
            first = 0;
        }
        return item;
    }

    @Override
    public int removeLast() {
        entries--;
        int item = array[last];
        last--;
        if (last == -1) {
            last = array.length - 1;
        }
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
