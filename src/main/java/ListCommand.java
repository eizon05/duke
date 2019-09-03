import java.io.IOException;

public class ListCommand extends Command {


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.list();
        taskList.list();
    }
}