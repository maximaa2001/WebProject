package by.bsuir.service.impl;

import by.bsuir.dao.Transaction;
import by.bsuir.dao.impl.UserDaoImpl;
import by.bsuir.entity.User;
import by.bsuir.exception.DaoException;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
    private static UserServiceImpl instance;

    private final static Transaction transaction = new Transaction();
    private final static UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users = new ArrayList<>();
        transaction.startNoTransaction(userDao);
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while finding all users", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction(userDao);
        }
        return users;
    }

    @Override
    public User findUserById(long id) throws ServiceException {
        User user = null;
        transaction.startNoTransaction(userDao);
        try {
            user = userDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while finding user by id", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction(userDao);
        }
        return user;
    }

    @Override
    public User findUserByProductId(long id) throws ServiceException {
        User user = null;
        transaction.startNoTransaction(userDao);
        try {
            user = userDao.getUserByProductId(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while finding user by product id", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction(userDao);
        }
        return user;
    }

    @Override
    public User findUserByAccountId(long id) throws ServiceException {
        User user = null;
        transaction.startNoTransaction(userDao);
        try {
            user = userDao.findUserByAccountId(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while finding user by account id", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction(userDao);
        }
        return user;
    }

    @Override
    public List<User> findAllUnApprovedUser() throws ServiceException {
        List<User> users = new ArrayList<>();
        transaction.startNoTransaction(userDao);
        try {
            users = userDao.findAllUnApprovedUser();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while finding all unApproved users", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction(userDao);
        }
        return users;
    }

    @Override
    public boolean changeStatus(long id) throws ServiceException {
        boolean result = false;
        transaction.startNoTransaction(userDao);
        try {
            result = userDao.setUserApprove(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while change user status", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction(userDao);
        }
        return result;
    }
}
