import java.util.DuplicateFormatFlagsException;

/**
 * This class deals with making sense of the user command
 */
public class Parser {

    /**
     * Parses the input given by the user
     * @param command The inputted text
     * @return
     * @throws DukeException
     */
    public static Command parse (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        Task task = null;
        int index;
        String text;

        switch (words[0]) {
        case "todo":
            task = Parser.parsesAdd(command, TaskType.TODO);
            return new AddCommand(task);
        case "deadline":
            task = Parser.parsesAdd(command, TaskType.DEADLINE);
            return new AddCommand(task);
        case "event":
            task = Parser.parsesAdd(command, TaskType.EVENT);
            return new AddCommand(task);
        case "done":
            index = Parser.parsesDone(command);
            return new DoneCommand(index);
        case "delete":
            index = Parser.parsesDelete(command);
            return new DeleteCommand(index);
        case "find":
            text = Parser.parsesFind(command);
            return new FindCommand(text);
        case "archive":
            index = Parser.parsesArchive(command);
            return new ArchiveCommand(index);
        }

        switch (command) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }

    /**
     * Parses the done string and returns the index of the done task
     * @param command done string
     * @return index of done task
     * @throws DukeException
     */

    private static String parsesFind(String command) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) throw DukeException.emptyDescription();

        if (words[1].isBlank()) throw DukeException.emptyDescription();

        String text = words[1];

        return text;
    }

    /**
     * parses the delete string to get the index of the Task to be deleted
     * @param command delete string
     * @return index of the task to be deleted
     * @throws DukeException
     */
    public static int parsesDelete (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) throw DukeException.emptyDescription();

        if (words[1].isBlank()) throw DukeException.emptyDescription();

        int index = Integer.valueOf(words[1]) - 1;

        return index;

    }

    public static int parsesArchive (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) throw DukeException.emptyDescription();

        if (words[1].isBlank()) throw DukeException.emptyDescription();

        int index = Integer.valueOf(words[1]) - 1;

        return index;

    }

    public static int parsesDone (String command) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) {
            throw DukeException.emptyDescription();
        }

        if (words[1].isBlank()) {
            throw DukeException.emptyDescription();
        }

        int index = Integer.valueOf(words[1]) - 1;

        return index;

    }

    /**
     * Parses the add string and returns the task
     * @param command add string
     * @param taskType type of task (todo, event, deadline)
     * @return The Task to be added
     * @throws DukeException
     */
    public static Task parsesAdd (String command, TaskType taskType) throws DukeException {

        String[] words = command.split(" ", 2);

        if (words.length == 1) {
            throw DukeException.emptyDescription();
        }

        Task task = null;

        String description;

        switch (taskType) {
        case TODO:
            description = words[1];

            if (description.isBlank()) {
                throw DukeException.emptyDescription();
            }

            task = new Todo(description);

            break;

        case DEADLINE:
            String[] split = words[1].split(" /by ", 2);

            if (split.length == 1) {
                throw DukeException.emptyDescription();
            }

            description = split[0];
            String by = split[1];

            if (description.isBlank() || by.isBlank()) {
                throw DukeException.emptyDescription();
            }

            task = new Deadline(description, by);

            break;

        case EVENT:
            split = words[1].split(" /at ", 2);

            if (split.length == 1) {
                throw DukeException.emptyDescription();
            }

            description = split[0];
            String at = split[1];

            if (description.isBlank() || at.isBlank()) {
                throw DukeException.emptyDescription();
            }

            task = new Event(description, at);

            break;

        }

        return task;

    }

}
