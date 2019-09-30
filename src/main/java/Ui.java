import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {

    /**
     * Greets the user
     */
    public static String greeting () {

        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Bids farewell to the user
     */
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Lists all the Tasks to the user
     */
    public String list (ArrayList<Task> list) {

        String text = "Here are the tasks in your list:\n";

        for (int i = 0; i < list.size(); i++) {
            text += i+1 + "." + list.get(i) +"\n";
        }

        return text;
    }


    /**
     * Notifies user that a Task was added to the tasks list
     * @param list
     */
    public String add (ArrayList<Task> list) {

        return "Got it. I've added this task:\n" + list.get(list.size() - 1) + "\n" + String.format("Now you have %d tasks in the list.\n", list.size());
    }


    /**
     * Notifies the user that a task was deleted from the task list
     * @param task list
     * @param task Task that was deleted
     */
    public String delete(ArrayList<Task> list, Task task) {

        return task + "\n" + String.format("Now you have %d tasks in the list.\n", list.size());
    }

    /**
     * Notifies user that a task was completed (done status changed to true)
     * @param task Task that was completed
     */
    public String done(Task task) {

        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Notifies user that a task was archived
     * @param task Task that was completed
     */
    public String archive(Task task) {

        return "Nice! I've archived the task:\n" + task + "\n" + "(TIP: YOU CAN VIEW YOUR ARCHIVED TASKS FROM THE archive.txt FILE)";
    }

    /**
     * Shows an error to the user
     * @param message The error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Aborts the application
     */
    public static String abort() {
        return "SORRY SOMETHING WENT SERIOUSLY WRONG! \nGoodbye!";
    }

    public String find(Task[] matchedTasks) {

        String text = "Here are the matching tasks in your list:\n";

        for (Task task : matchedTasks) {
            text += task + "\n";
        }

        return text;
    }
}
