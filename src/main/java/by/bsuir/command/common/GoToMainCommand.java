package by.bsuir.command.common;

import by.bsuir.command.Command;
import by.bsuir.command.RequestCommand;
import by.bsuir.command.ResponseCommand;
import by.bsuir.command.TransitionType;

public class GoToMainCommand implements Command {
    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        response.setTypeTransition(TransitionType.FORWARD);
        response.setPath("/jsp/main.jsp");
        return response;
    }
}
