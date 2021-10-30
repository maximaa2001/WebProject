package by.bsuir.command.userCommand;

import by.bsuir.command.*;
import by.bsuir.entity.Product;
import by.bsuir.entity.User;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.impl.ProductServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Set;

public class ShowAllUserProductCommand implements Command {
    private final static Logger logger = Logger.getLogger(ShowAllUserProductCommand.class);
    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        User user = (User) requestCommand.getSessionAttribute(Constant.ATTRIBUTE_USER);
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        try {
            Set<Product> productSet = productService.findAllProductByUserId(user.getId());
            if(productSet.size() != 0){
                requestCommand.addSessionAttribute(Constant.ATTRIBUTE_SET_CONCRETE_USER_PRODUCT,productSet);
            }else {
                requestCommand.addRequestAttribute(Constant.ATTRIBUTE_EMPTY_SET_CONCRETE_USER_PRODUCT,"У вас нет ни одного товара");
            }
            response.setTypeTransition(TransitionType.FORWARD);
            response.setPath("/jsp/myProduct.jsp");
        }catch (ServiceException e){
            logger.log(Level.ERROR,"Error while find user product command", e);
            response.setTypeTransition(TransitionType.REDIRECT);
            response.setPath("/jsp/error.jsp");
        }
        return response;
    }
}
