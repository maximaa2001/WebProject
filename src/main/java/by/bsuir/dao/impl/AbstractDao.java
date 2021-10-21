package by.bsuir.dao.impl;

import by.bsuir.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> {
    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    public Connection connection;

    public abstract List<T> findAll() throws DaoException;

    public abstract T findById(long id) throws DaoException;

    public abstract boolean create(T entity) throws DaoException;

    public abstract boolean deleteById(long id) throws DaoException;

    public abstract boolean update(T entity) throws DaoException;

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public void close(){
        try {
            connection.close();
        }catch (SQLException e ){
            logger.warn(e);
        }
    }
}
