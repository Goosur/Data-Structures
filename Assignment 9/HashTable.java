import java.util.List;
import java.util.ArrayList;

public class HashTable<T> implements Dictionary<T> {

    List<Node<T>>[] table;
    int numItems;

    public HashTable() {
        this.table = new ArrayList[16];

        // Have to initialize each arraylist so they aren't null
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = new ArrayList<>();
        }
    }

    public void resize() {
        
        List<Node<T>>[] newTable = new ArrayList[table.length * 2];

        // Have to initialize each arraylist so they aren't null
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new ArrayList<>();
        }

        // Insert old elements into new table with new hashes
        for (List<Node<T>> slot : this.table) {
            for (Node<T> item : slot) {
                newTable[Math.abs(item.key.hashCode() % newTable.length)].add(item);
            }
        }

        this.table = newTable;

    }

    @Override
    public void insert(String key, T value) {

        // Number of items should be less than or equal to size of table
        // because ideally we have one item in each table slot
        if (this.numItems + 1 > this.table.length) {
            resize();
        }

        // If they key does not already exist, add key and value to table
        if (this.lookup(key) == null) {
            this.table[Math.abs(key.hashCode() % this.table.length)].add(new Node<>(key, value));
            this.numItems++;
        }

    }

    @Override
    public T lookup(String key) {

        // Search each slot for node with given key
        for (Node<T> item : this.table[Math.abs(key.hashCode() % this.table.length)]) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }

        return null;

    }

}
