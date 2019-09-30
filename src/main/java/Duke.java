import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class where the main logic is executed
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;

    public Duke() {
        this("data/duke.txt", "data");
    }

    /**
     * Intialises all the classes needed to run this Application.
     * It also retrieves the previous tasks saved in the last session.
     * @param filePath File Path of duke.txt
     * @param folderPath Folder Path in which duke.txt is saved
     */
    public Duke(String filePath, String folderPath) {

        ui = new Ui();

        storage = new Storage(filePath, folderPath);

        try {
            tasks = new TaskList(storage.retrieve());
        } catch (FileNotFoundException e) {

            try {
                storage.makeDirectory(folderPath);
                tasks = new TaskList();
            } catch (IOException ex) {
                ui.abort();
                System.exit(0);
            }
        }

    }

    /**
     * Runs the main logic of the application
     */
    public String run (String fullCommand) {

        try {
            Command c = Parser.parse(fullCommand);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.run(input);
    }


}
