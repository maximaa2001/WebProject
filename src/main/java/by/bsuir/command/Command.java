package by.bsuir.command;

public interface Command {
    ResponseCommand execute(RequestCommand requestCommand);
}
