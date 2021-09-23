package by.bsuir.dao.impl;

import by.bsuir.dao.BaseDao;
import by.bsuir.db.ConnectionPool;
import by.bsuir.entity.Account;
import by.bsuir.entity.Admin;
import by.bsuir.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements BaseDao<Admin> {
    private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
    private static AdminDaoImpl instance;
    private final String SQL_FIND_ALL = "SELECT account.login, account.password, admin.id FROM account INNER JOIN admin ON admin.Account_id = account.id";
    private final String SQL_FIND_BY_ID = "SELECT account.login, account.password, admin.id FROM account INNER JOIN admin ON admin.Account_id = account.id WHERE admin.id = ?";
    private final String SQL_CREATE = "INSERT INTO admin (Account_id) VALUES (?)";
    private final String SQL_FIND_ACCOUNT_ID = "SELECT admin.Account_id FROM admin WHERE admin.id = ?";

    private AdminDaoImpl() {
    }

    public static AdminDaoImpl getInstance() {
        if (instance == null) {
            instance = new AdminDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Admin> findAll() throws DaoException {
        List<Admin> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("admin.id"));
                admin.setLogin(resultSet.getString("account.login"));
                admin.setPassword(resultSet.getString("account.password"));
                list.add(admin);
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public Admin findById(long id) throws DaoException {
        Admin admin = null;
        ResultSet resultSet;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getLong("admin.id"));
                admin.setLogin(resultSet.getString("account.login"));
                admin.setPassword(resultSet.getString("account.password"));
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return admin;
    }

    @Override
    public boolean create(Admin entity) throws DaoException {
        Account account = new Account();
        account.setLogin(entity.getLogin());
        account.setPassword(entity.getPassword());
        if (AccountDaoImpl.getInstance().create(account)) {
            account = AccountDaoImpl.getInstance().findByLogin(entity.getLogin());
            try (Connection connection = ConnectionPool.getInstance().takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
                preparedStatement.setLong(1, account.getId());
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
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ACCOUNT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long accountId = resultSet.getLong("admin.Account_id");
                AccountDaoImpl.getInstance().deleteById(accountId);
                return true;
            } else {
                return false;
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean update(Admin entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ACCOUNT_ID)) {
            preparedStatement.setLong(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("admin.Account_id"));
                account.setLogin(entity.getLogin());
                account.setPassword(entity.getPassword());
                AccountDaoImpl.getInstance().update(account);
                return true;
            } else {
                return false;
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
    }
}
