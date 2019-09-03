import java.io.IOException;

/**
 * This class is initiased and its execute method is called whenever you want to change the done status of a task in the task list.
 */
public class DoneCommand extends Command {

    int index;

    /**
     * Intialises this class with an index
     * @param index The Index
     */
    public DoneCommand (int index) {
        this.index = index;
    }


    /**
     * Changes the done status of the task in the task list found from the given index
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {

        Task task = taskList.done(this.index);

        ui.done(task);

        storage.save(taskList.list);
    }
}
