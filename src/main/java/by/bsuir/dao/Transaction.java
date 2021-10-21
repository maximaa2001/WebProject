package by.bsuir.dao;

import by.bsuir.dao.impl.AbstractDao;
import by.bsuir.db.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private static final Logger logger = Logger.getLogger(Transaction.class);
    private Connection connection;

    public void startNoTransaction(AbstractDao dao) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        dao.setConnection(connection);
    }

    public void startYesTransaction(AbstractDao dao, AbstractDao... abstractDaos) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in setAutoCommit(false)", e);
        }
        dao.setConnection(connection);
        for (int i = 0; i < abstractDaos.length; i++) {
            abstractDaos[i].setConnection(connection);
        }
    }

    public void endNoTransaction(AbstractDao dao) {
        if (connection != null) {
            dao.setConnection(null);
            ConnectionPool.getInstance().reverseConnection(connection);
            connection = null;
        }
    }

    public void endYesTransaction(AbstractDao dao, AbstractDao... abstractDaos) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error in setAutoCommit(true)", e);
            }
            dao.setConnection(null);
            for (int i = 0; i < abstractDaos.length; i++) {
                abstractDaos[i].setConnection(null);
            }
            ConnectionPool.getInstance().reverseConnection(connection);
            connection = null;
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e){
            logger.log(Level.ERROR, "Error in commit)", e);
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in rollback)", e);
        }
    }
}
