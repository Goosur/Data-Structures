import java.util.ArrayList;
import java.util.Collections;

import javax.print.event.PrintEvent;

public class TestHeap {
    
    public static void main(String[] args) {

        GenericMaxHeap<Task> heap = new GenericMaxHeap<>();

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Sleep", 1));
        tasks.add(new Task("Eat", 4));
        tasks.add(new Task("Drink", 56));
        tasks.add(new Task("Walk", 152));
        tasks.add(new Task("Run", 999));

        Collections.shuffle(tasks);

        System.out.println("\nInserting Tasks...");
        for (Task task : tasks) {
            System.out.println(task + " inserted.");
            heap.insert(task);
        }

        System.out.println("\nRemoving Tasks by Priority...");

        Task task;
        for (int i = 0; i < 7; i++) {
            System.out.println("Tasks: " + heap.data.toString());
            task = heap.removeMax();
            System.out.println(task + " removed.");
        }

    }
    
}
