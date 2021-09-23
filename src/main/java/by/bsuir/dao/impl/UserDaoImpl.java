package by.bsuir.dao.impl;

import by.bsuir.dao.UserDao;
import by.bsuir.db.ConnectionPool;
import by.bsuir.entity.Account;
import by.bsuir.entity.User;
import by.bsuir.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance;
    private final String SQL_FIND_ALL = "SELECT account.login, account.password, user.id, user.isApproved FROM account INNER JOIN user ON user.Account_id = account.id";
    private final String SQL_FIND_BY_ID = "SELECT account.login, account.password, user.id, user.isApproved FROM account INNER JOIN user ON user.Account_id = account.id WHERE user.id = ?";
    private final String SQL_CREATE = "INSERT INTO user (isApproved,Account_id) VALUES (?,?)";
    private final String SQL_FIND_ACCOUNT_ID = "SELECT user.Account_id FROM user WHERE user.id = ?";
    private final String SQL_SET_APPROVE = "UPDATE user SET user.IsApproved = ? WHERE user.id = ?";
    private final String SQL_FIND_USER_ID_BY_PRODUCT_ID = "SELECT product.User_id FROM product WHERE product.id = ?";

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user.id"));
                user.setLogin(resultSet.getString("account.login"));
                user.setPassword(resultSet.getString("account.password"));
                user.setApproved(getApprove(resultSet.getInt("user.isApproved")));
                user.setProducts(ProductDaoImpl.getInstance().getAllProductsByUserId(user.getId()));
                list.add(user);
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public User findById(long id) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user.id"));
                user.setLogin(resultSet.getString("account.login"));
                user.setPassword(resultSet.getString("account.password"));
                user.setApproved(getApprove(resultSet.getInt("user.isApproved")));
                user.setProducts(ProductDaoImpl.getInstance().getAllProductsByUserId(user.getId()));
            }
            return user;
        } catch (IOException | SQLException | InterruptedException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    private boolean getApprove(int result) {
        return (result == 1);
    }

    @Override
    public boolean create(User entity) throws DaoException {
        Account account = new Account();
        account.setLogin(entity.getLogin());
        account.setPassword(entity.getPassword());
        if (AccountDaoImpl.getInstance().create(account)) {
            account = AccountDaoImpl.getInstance().findByLogin(entity.getLogin());
            try (Connection connection = ConnectionPool.getInstance().takeConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
                preparedStatement.setInt(1, 0);
                preparedStatement.setLong(2, account.getId());
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
                long accountId = resultSet.getLong("user.Account_id");
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
    public boolean update(User entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ACCOUNT_ID)) {
            preparedStatement.setLong(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("user.Account_id"));
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

    @Override
    public User getUserByProductId(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_ID_BY_PRODUCT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            long userId;
            if (resultSet.next()) {
                userId = resultSet.getLong("product.User_id");
                return findById(userId);
            }
        } catch (IOException | SQLException | InterruptedException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean setUserApprove(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_APPROVE)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (IOException | SQLException | InterruptedException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
    }
}
