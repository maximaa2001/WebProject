package by.bsuir.service.impl;

import by.bsuir.dao.Transaction;
import by.bsuir.dao.impl.AccountDaoImpl;
import by.bsuir.dao.impl.UserDaoImpl;
import by.bsuir.entity.Account;
import by.bsuir.entity.User;
import by.bsuir.exception.DaoException;
import by.bsuir.exception.ServiceException;
import by.bsuir.service.AccountService;
import by.bsuir.util.HashGenerator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Map;

public class AccountServiceImpl implements AccountService {
    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
    private static AccountServiceImpl instance;
    private final static HashGenerator hashGenerator = new HashGenerator();
    private final static Transaction transaction = new Transaction();
    private final static AccountDaoImpl accountDao = AccountDaoImpl.getInstance();

    private AccountServiceImpl() {
    }

    public static AccountServiceImpl getInstance() {
        if (instance == null) {
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean checkLoginAndPassword(String login, String password) throws ServiceException {
        boolean result = false;
        transaction.startNoTransaction(accountDao);
        try {
            result = accountDao.findByLoginAndPassword(login, hashGenerator.hash(password));
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while checked login and password", e);
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction(accountDao);
        }
        return result;
    }

    @Override
    public boolean checkLogin(String login) throws ServiceException {
        boolean result;
        transaction.startNoTransaction(accountDao);
        try {
            result = accountDao.findByLogin(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while find login", e);
            throw new ServiceException();
        } finally {
            transaction.endNoTransaction(accountDao);
        }
        return result;
    }

    @Override
    public Account registerUser(Map<String, String> map) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        Account account = new Account();
        account.setLogin(map.get("LOGIN"));
        account.setPassword(hashGenerator.hash(map.get("PASSWORD")));
        account.setNumber(map.get("NUMBER"));
        transaction.startYesTransaction(accountDao, userDao);
        try {
            accountDao.create(account);
            long accountId = accountDao.findIdByLogin(account.getLogin());
            account.setId(accountId);
            User user = new User();
            user.setAccountId(accountId);
            user.setApproved(false);
            userDao.create(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.ERROR, "Error while registration", e);
            throw new ServiceException(e);
        } finally {
            transaction.endYesTransaction(accountDao, userDao);
        }
        return account;
    }

    @Override
    public boolean changePassword(Account account, String newPassword) throws ServiceException {
        boolean result = false;
        account.setPassword(hashGenerator.hash(newPassword));
        transaction.startNoTransaction(accountDao);
        try {
            result = accountDao.update(account);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while change password", e);
            throw new ServiceException();
        } finally {
            transaction.endNoTransaction(accountDao);
        }
        return result;
    }

    @Override
    public boolean changeNumber(Account account, String newNumber) throws ServiceException {
        boolean result = false;
        account.setNumber(newNumber);
        transaction.startNoTransaction(accountDao);
        try {
            result = accountDao.update(account);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while change number", e);
            throw new ServiceException();
        } finally {
            transaction.endNoTransaction(accountDao);
        }
        return result;
    }

    @Override
    public boolean deleteAccount(Account account) throws ServiceException {
        boolean result = false;
        transaction.startNoTransaction(accountDao);
        try {
            result = accountDao.deleteById(account.getId());
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while delete account", e);
            throw new ServiceException();
        } finally {
            transaction.endNoTransaction(accountDao);
        }
        return result;
    }

    @Override
    public Account findByLogin(String login) throws ServiceException {
        Account account = null;
        transaction.startNoTransaction(accountDao);
        try {
           long id = accountDao.findIdByLogin(login);
           account = accountDao.findById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error while finding account by login", e);
            throw new ServiceException();
        } finally {
            transaction.endNoTransaction(accountDao);
        }
        return account;
    }
}
