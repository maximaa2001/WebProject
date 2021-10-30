package by.bsuir.command.common;

import by.bsuir.command.Command;
import by.bsuir.command.RequestCommand;
import by.bsuir.command.ResponseCommand;
import by.bsuir.command.TransitionType;

public class LogOutCommand implements Command {
    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        requestCommand.setInvalidateSession(true);
        response.setTypeTransition(TransitionType.FORWARD);
        response.setPath("/jsp/log_in.jsp");
        return response;
    }
}
