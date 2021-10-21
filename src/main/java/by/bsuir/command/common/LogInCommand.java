package by.bsuir.command.common;

import by.bsuir.command.*;
import by.bsuir.entity.Account;
import by.bsuir.entity.User;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.impl.AccountServiceImpl;
import by.bsuir.service.impl.UserServiceImpl;
import by.bsuir.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogInCommand implements Command {
    private final static Logger logger = Logger.getLogger(LogInCommand.class);
    private final static Validator validator = new Validator();

    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        String login = requestCommand.getRequestParameter(Constant.PARAMETER_LOGIN);
        String password = requestCommand.getRequestParameter(Constant.PARAMETER_PASSWORD);
        AccountServiceImpl accountService = AccountServiceImpl.getInstance();
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if(accountService.checkLoginAndPassword(login, password)){
                Account account = accountService.findByLogin(login);
                requestCommand.addSessionAttribute(Constant.PARAMETER_ACCOUNT,account);
                User user = userService.findUserByAccountId(account.getId());
                if(user == null){
                    requestCommand.addSessionAttribute(Constant.PARAMETER_ROLE,"admin");
                    response.setTypeTransition(TransitionType.FORWARD);
                    response.setPath("/jsp/cabinet.jsp");
                }else {
                    if(user.isApproved()){
                        requestCommand.addSessionAttribute(Constant.PARAMETER_ROLE,"user");
                        response.setTypeTransition(TransitionType.FORWARD);
                        response.setPath("/jsp/cabinet.jsp");
                    }else {
                        requestCommand.addRequestAttribute(Constant.PARAMETER_UN_APPROVE,"Admin didn't agree with your profile");
                        response.setTypeTransition(TransitionType.FORWARD);
                        response.setPath("/jsp/log_in.jsp");
                    }
                }
            }else {
                if(accountService.checkLogin(login)){
                    requestCommand.addRequestAttribute(Constant.PARAMETER_INCORRECT_PASSWORD,"incorrect password");
                }else {
                    requestCommand.addRequestAttribute(Constant.PARAMETER_INCORRECT_LOGIN,"incorrect login");
                }
                response.setTypeTransition(TransitionType.FORWARD);
                response.setPath("/jsp/log_in.jsp");
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"Error while login", e);
            response.setTypeTransition(TransitionType.REDIRECT);
            response.setPath("/jsp/error.jsp");
        }
        return response;
    }
}
