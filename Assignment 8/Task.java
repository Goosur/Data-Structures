public class Task implements Comparable<Task> {

    String description;
    int priority;
   
    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task task) {

        if (this.priority > task.priority) {
            return 1;
        } else if (this.priority < task.priority) {
            return -1;
        } else {
            return 0;
        }

    }

}
