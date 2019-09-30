import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * This class contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {

    protected ArrayList<Task> list;

    public TaskList (ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds the task to the task list
     * @param task The Task
     */
    public void add (Task task) {

        assert task != null : "TASK SHOULD BE NON-NULL";

        list.add(task);
    }

    /**
     * Marks the task as done
     * @param index The Index of the Task
     * @return
     */
    public Task done (int index) throws DukeException {
      
      assert index >= 0 : "INDEX SHOULD BE GREATER THAN ZERO";

        if (index < 0 || (index + 1) > list.size()) throw DukeException.outOfBounds();

        Task task = list.get(index);
        task.markAsDone();

        return task;
    }

    /**
     * Deletes a task
     * @param index The index of the task
     * @return The deleted task
     */
    public Task delete (int index) throws DukeException {

        if (index < 0 || (index + 1) > list.size()) throw DukeException.outOfBounds();

        assert index >= 0 : "INDEX SHOULD BE GREATER THAN ZERO";

        Task task = list.remove(index);
        return task;
    }

    public Task[] find(String text) {

        assert text.isBlank() : "TEXT SHOULD NOT BE BLANK";

        ArrayList<Task> tasks =  new ArrayList<>();

        for (Task task : list) {
            if (task.description.toLowerCase().contains(text.toLowerCase())) {
                tasks.add(task);
            }
        }

        return tasks.toArray(new Task[tasks.size()]);
    }

    public Task archive (int index) throws DukeException {

        return this.delete(index);
    }
}
