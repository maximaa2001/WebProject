package by.bsuir.command;

public class ResponseCommand {
    private TransitionType typeTransition;
    private String path;

    public ResponseCommand(TransitionType typeTransition, String path) {
        this.typeTransition = typeTransition;
        this.path = path;
    }

    public ResponseCommand() {
        typeTransition = TransitionType.FORWARD;
    }

    public TransitionType getTypeTransition() {
        return typeTransition;
    }

    public void setTypeTransition(TransitionType typeTransition) {
        this.typeTransition = typeTransition;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
