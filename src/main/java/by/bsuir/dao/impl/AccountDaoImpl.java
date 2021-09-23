package by.bsuir.dao.impl;

import by.bsuir.dao.AccountDao;
import by.bsuir.db.ConnectionPool;
import by.bsuir.entity.Account;
import by.bsuir.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
    private static AccountDaoImpl instance;
    private final String SQL_FIND_ALL = "SELECT * FROM account";
    private final String SQL_FIND_BY_LOGIN = "SELECT * FROM account WHERE login = ?";
    private final String SQL_FIND_BY_ID = "SELECT * FROM account WHERE id = ?";
    private final String SQL_CREATE = "INSERT INTO account (login, password) VALUES(?, ?)";
    private final String SQL_DELETE = "DELETE FROM account WHERE id = ?";
    private final String SQL_UPDATE = "UPDATE account SET account.login = ?, account.password = ? WHERE account.id = ?";

    private AccountDaoImpl() {
    }

    public static AccountDaoImpl getInstance() {
        if (instance == null) {
            instance = new AccountDaoImpl();
        }
        return instance;
    }

    @Override
    public Account findByLogin(String login) throws DaoException {
        Account account = null;
        ResultSet resultSet;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getLong("account.id"));
                account.setLogin(resultSet.getString("account.login"));
                account.setPassword(resultSet.getString("account.password"));
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return account;
    }

    @Override
    public List<Account> findAll() throws DaoException {
        List<Account> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("account.id"));
                account.setLogin(resultSet.getString("account.login"));
                account.setPassword(resultSet.getString("account.password"));
                list.add(account);
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public Account findById(long id) throws DaoException {
        Account account = null;
        ResultSet resultSet;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getLong("account.id"));
                account.setLogin(resultSet.getString("account.login"));
                account.setPassword(resultSet.getString("account.password"));
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return account;
    }

    @Override
    public boolean create(Account entity) throws DaoException {
        String login = entity.getLogin();
        Account createdAccount = findByLogin(login);
        if (createdAccount == null) {
            try (Connection connection = ConnectionPool.getInstance().takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
                preparedStatement.setString(1, entity.getLogin());
                preparedStatement.setString(2, entity.getPassword());
                preparedStatement.executeUpdate();
            } catch (IOException | SQLException | InterruptedException e) {
                logger.warn(e);
                throw new DaoException(e);
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        Account accountById = findById(id);
        if (accountById != null) {
            try (Connection connection = ConnectionPool.getInstance().takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } catch (IOException | SQLException | InterruptedException e) {
                logger.warn(e);
                throw new DaoException(e);
            }
        } else {
            return false;
        }
        return true;
    }


    @Override
    public boolean update(Account entity) throws DaoException {
        boolean isUpdate = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getId());
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                isUpdate = true;
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }
}
