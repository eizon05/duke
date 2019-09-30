/**
 * This class is initiased and its execute method is called whenever you want to change the done status of a task in the task list.
 */
public class DoneCommand extends Command {

    private int index;

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
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        Task task = taskList.done(this.index);

        storage.save(taskList.list);

        return ui.done(task);
    }
}
