package by.bsuir.dao.impl;

import by.bsuir.dao.AccountDao;
import by.bsuir.db.ConnectionPool;
import by.bsuir.entity.Account;
import by.bsuir.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {
    private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
    private static AccountDaoImpl instance;
    private final static String SQL_FIND_ALL = "SELECT * FROM account";
    private final static String SQL_FIND_BY_LOGIN = "SELECT * FROM account WHERE login = ?";
    private final static String SQL_FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM account WHERE login = ? AND password = ?";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM account WHERE id = ?";
    private final static String SQL_CREATE = "INSERT INTO account (login, password, number) VALUES(?, ?, ?)";
    private final static String SQL_DELETE = "DELETE FROM account WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE account SET account.login = ?, account.password = ?, account.number = ? WHERE account.id = ?";


    private AccountDaoImpl() {
    }

    public static AccountDaoImpl getInstance() {
        if (instance == null) {
            instance = new AccountDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean findByLogin(String login) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean findByLoginAndPassword(String login, String password) throws DaoException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            logger.log(Level.ERROR,e);
            throw new DaoException(e);
        }
    }

    @Override
    public long findIdByLogin(String login) throws DaoException {
        Account account = new Account();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account = getAccountFromResultSet(resultSet);
            }
        }catch (SQLException e){
            logger.log(Level.ERROR,e);
            throw new DaoException(e);
        }
        return account.getId();
    }

    @Override
    public List<Account> findAll() throws DaoException {
        List<Account> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Account account = getAccountFromResultSet(resultSet);
                list.add(account);
            }
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public Account findById(long id) throws DaoException {
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account = getAccountFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
        return account;
    }


    public boolean create(Account entity) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getNumber());
            int result = preparedStatement.executeUpdate();
            return result != 0;
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            return result != 0;
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }


    @Override
    public boolean update(Account entity) throws DaoException {
        boolean isUpdate = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getNumber());
            preparedStatement.setLong(4, entity.getId());
            int result = preparedStatement.executeUpdate();
            return  result != 0;
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("account.id"));
        account.setLogin(resultSet.getString("account.login"));
        account.setPassword(resultSet.getString("account.password"));
        account.setNumber(resultSet.getString("account.number"));
        return account;
    }
}
