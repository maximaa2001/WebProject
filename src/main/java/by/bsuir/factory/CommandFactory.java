package by.bsuir.factory;

import by.bsuir.command.Command;
import by.bsuir.command.CommandType;
import by.bsuir.command.RequestCommand;

public class CommandFactory {
    private CommandFactory(){

    }

    public static Command getCommand(RequestCommand requestCommand){
        String nameCommand = requestCommand.getRequestParameter("command");
        if(nameCommand != null){
            CommandType commandType = CommandType.valueOf(nameCommand.toUpperCase());
            return commandType.getCommand();
        }else {
            return null;
        }
    }
}
