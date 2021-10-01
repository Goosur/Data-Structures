public class TestOrderedLinked {
    
    public static void main (String[] args) {
        OrderedLinked list = new OrderedLinked<Integer>();
        
        System.out.println("Printing newly created list");
        list.print();

        System.out.println("Removed 5: " + list.remove(5));
        System.out.println("Inserted 5");
        list.insert(5);

        System.out.println("Printing latest list contents");
        list.print();

        System.out.println("Inserted 5, 2, 15, 7, 100");
        list.insert(5);
        list.insert(2);
        list.insert(15);
        list.insert(7);
        list.insert(100);

        System.out.println("Printing latest list contents");
        list.print();

        System.out.println("Removed 2: " + list.remove(2));
        System.out.println("Removed 7: " + list.remove(7));
        System.out.println("Removed 100: " + list.remove(100));

        System.out.println("Printing latest list contents");
        list.print();

        System.out.println("Removed 999: " + list.remove(999));
        System.out.println("Found 5: " + list.search(5));
        System.out.println("Found 15: " + list.search(15));
        System.out.println("Found 1: " + list.search(1));
    }
}
