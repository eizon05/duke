import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class where the main logic is executed
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
               // ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
    }

    /**
     * This is the starting point of the application
     * @param args
     * @throws DukeException
     * @throws IOException
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("../data/duke.txt", "../data").run();
    }

}
