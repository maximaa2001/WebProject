package by.bsuir;

import by.bsuir.dao.Transaction;
import by.bsuir.dao.impl.AccountDaoImpl;
import by.bsuir.dao.impl.ProductDaoImpl;
import by.bsuir.dao.impl.UserDaoImpl;
import by.bsuir.db.ConnectionPool;
import by.bsuir.entity.Account;
import by.bsuir.entity.ApplicationProperties;
import by.bsuir.entity.User;
import by.bsuir.exception.DaoException;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.impl.AccountServiceImpl;
import by.bsuir.util.HashGenerator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ServiceException, IOException {
//        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
//        int connectionPoolSize = applicationProperties.getConnectionPoolSize();
//        int maxConnectionPoolSize = applicationProperties.getMaxConnectionPoolSize();
//        System.out.println(connectionPoolSize);

    }
}
