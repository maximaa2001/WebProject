package by.bsuir.command.common;

import by.bsuir.command.*;
import by.bsuir.dao.impl.ProductDaoImpl;
import by.bsuir.entity.Product;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.impl.ProductServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.rmi.ServerException;
import java.util.List;

public class ShowCatalogCommand implements Command {
    private final static Logger logger = Logger.getLogger(ShowCatalogCommand.class);

    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        try {
            List<Product> products = productService.findAllProduct();
            requestCommand.addSessionAttribute(Constant.ATTRIBUTE_LIST_ALL_PRODUCT, products);
            response.setTypeTransition(TransitionType.FORWARD);
            response.setPath("/jsp/catalog.jsp");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while show catalog", e);
            response.setTypeTransition(TransitionType.REDIRECT);
            response.setPath("/jsp/error.jsp");
        }
        return response;
    }
}
