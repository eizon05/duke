public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveFormat() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.at);
    }

}
