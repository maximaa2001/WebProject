package by.bsuir.dao.impl;

import by.bsuir.dao.UserDao;
import by.bsuir.db.ConnectionPool;
import by.bsuir.entity.Account;
import by.bsuir.entity.Admin;
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

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance;
    private final static String SQL_FIND_ALL = "SELECT account.login, account.password, account.number, user.id, user.isApproved FROM account INNER JOIN user ON user.Account_id = account.id";
    private final static String SQL_FIND_ALL_UNAPPROVED = "SELECT account.login, account.password, account.number, user.id, user.isApproved FROM account INNER JOIN user ON user.Account_id = account.id WHERE user.isApproved = ?";
    private final static String SQL_FIND_BY_ACCOUNT_ID = "SELECT account.login, account.password, account.number, user.id, user.isApproved FROM account INNER JOIN user ON user.Account_id = account.id WHERE account.id = ?";
    private final static String SQL_FIND_BY_ID = "SELECT account.login, account.password, account.number, user.id, user.isApproved FROM account INNER JOIN user ON user.Account_id = account.id WHERE user.id = ?";
    private final static String SQL_CREATE = "INSERT INTO user (isApproved,Account_id) VALUES (?,?)";
    private final static String SQL_SET_APPROVE = "UPDATE user SET user.isApproved = ? WHERE user.id = ?";
    private final static String SQL_FIND_USER_ID_BY_PRODUCT_ID = "SELECT product.User_id FROM product WHERE product.id = ?";
    private final static String SQL_DELETE = "DELETE FROM user WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE user SET user.isApproved = ? WHERE user.id = ?";

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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                list.add(user);
            }
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public User findById(long id) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    private boolean getApprove(int result) {
        return (result == 1);
    }


    /*
            First create account and install accountId in user
     */
    @Override
    public boolean create(User entity) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setInt(1, 0);
            preparedStatement.setLong(2, entity.getAccountId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean update(User entity) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            int isApprove = entity.isApproved() ? 1 : 0;
            preparedStatement.setInt(1, isApprove);
            preparedStatement.setLong(2, entity.getId());
            int result = preparedStatement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public User getUserByProductId(long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_ID_BY_PRODUCT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            long userId;
            if (resultSet.next()) {
                userId = resultSet.getLong("product.User_id");
                return findById(userId);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
        return null;
    }

    @Override
    public User findUserByAccountId(long id) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ACCOUNT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                user.setAccountId(id);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAllUnApprovedUser() throws DaoException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_UNAPPROVED)) {
            preparedStatement.setLong(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean setUserApprove(long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_APPROVE)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e.getMessage());
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user.id"));
        user.setLogin(resultSet.getString("account.login"));
        user.setPassword(resultSet.getString("account.password"));
        user.setNumber(resultSet.getString("account.number"));
        user.setApproved(getApprove(resultSet.getInt("user.isApproved")));
        return user;
    }

}
