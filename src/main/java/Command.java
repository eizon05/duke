import java.io.IOException;

/**
 * Represents a command that has to be carried out
 */
public abstract class Command {

    /**
     * Executes the Command
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public abstract void execute (TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * Checks whether the command is to exit the program
     * @return
     */
    public boolean isExit() {
        return false;
    }
}
