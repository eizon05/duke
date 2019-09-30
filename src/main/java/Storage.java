import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private String folderPath;
    private String filePath;

    /**
     * Initialises a Storage Class with a filePath and a folderPath
     * @param filePath
     * @param folderPath
     */
    public Storage(String filePath, String folderPath) {
        this.filePath = filePath;
        this.folderPath = folderPath;

    }

    /**
     * Makes the directory of the folder if it didn't exist before
     * @param the folder path to make the directory
     * @throws IOException
     */
    public void makeDirectory (String folderPath) throws IOException {
        File file = new File(folderPath);
        file.mkdirs();
    }

    /**
     * Saves the file in the specified filePath
     * @param filePath specified filePath
     * @param textToAdd the data (text) to save inside the file
     * @throws IOException
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Retrives the preivously saved tasks form the filePath
     * @return Previously saved tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> retrieve () throws FileNotFoundException {

        ArrayList<Task> list = new ArrayList<>();

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source


        while (s.hasNext()) {
            String text = s.nextLine();

            String[] split = text.split(" \\| ");

            String description = split[2];

            Task t = null;

            switch (split[0]) {
            case "T":
                t = new Todo(description);
                break;
            case "D":
                t = new Deadline(description, split[3]);
                break;
            case "E":
                t = new Event(description, split[3]);
                break;
            }

            boolean isDone = split[1].equals("1");

            if (t != null) {
                t.isDone = isDone;
                list.add(t);
            }
        }

        assert list.isEmpty() : "LIST SHOULD NOT BE EMPTY";

        return list;
    }

    /**
     * Saves the Tasks in a file
     * @param list The list of tasks to save
     * @throws DukeException
     */
    public void save (ArrayList<Task> list) throws DukeException {
      
      assert list.isEmpty() : "LIST SHOULD NOT BE EMPTY";

        try {

            String s = "";

            for (Task t : list) {
                s += t.saveFormat() + System.lineSeparator();
            }

            this.writeToFile(filePath, s);

        } catch (IOException e) {
          throw DukeException.couldNotSave();
        }

    }

    /**
     * Saves the Archived Task in a file
     * @param task The task to save
     * @throws DukeException
     */
    public void saveArchiveTask (Task task) throws DukeException {

        try {

            FileWriter fileWriter = new FileWriter("data/archive.txt",true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(task.toString() + "\n");
            bufferedWriter.close();

        } catch (IOException e) {

            throw DukeException.couldNotSave();
        }

    }

}