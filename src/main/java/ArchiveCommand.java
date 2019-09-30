public class ArchiveCommand extends Command {

    private int index;

    /**
     * Intialise this class with an index
     * @param index The Index
     */
    public ArchiveCommand (int index) {
        this.index = index;
    }


    /**
     * Deletes the Task in the task list for the given index
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        Task task = taskList.archive(index);

        storage.saveArchiveTask(task);

        return ui.archive(task);

    }

}
