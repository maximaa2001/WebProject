package by.bsuir.db;

import by.bsuir.entity.ApplicationProperties;
import by.bsuir.util.PropertyReader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

public class ConnectionPool {
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool;
    private ApplicationProperties applicationProperties;
    private int connectionPoolSize;
    private int maxConnectionPoolSize;
    public BlockingDeque<Connection> availableConnections;
    public BlockingDeque<Connection> takenConnections;


    private ConnectionPool() {
        try {
            applicationProperties = ApplicationProperties.getInstance();
            connectionPoolSize = applicationProperties.getConnectionPoolSize();
            maxConnectionPoolSize = applicationProperties.getMaxConnectionPoolSize();
            System.out.println(applicationProperties.getUrl());
            System.out.println(applicationProperties.getUser());
            System.out.println(applicationProperties.getPassword());
            System.out.println(applicationProperties.getConnectionPoolSize());
            System.out.println(applicationProperties.getMaxConnectionPoolSize());
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while creating connection pool");
        }
        availableConnections = new LinkedBlockingDeque<>();
        takenConnections = new LinkedBlockingDeque<>();
        initConnectionPool();

    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    private void initConnectionPool() {
        try {
            for (int i = 0; i < connectionPoolSize; i++) {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                Connection connection = DriverManager.getConnection(applicationProperties.getUrl(),
                      applicationProperties.getUser(), applicationProperties.getPassword());
                System.out.println(connection);
                availableConnections.add(new ConnectionProxy(connection));
            }
        } catch (IOException | SQLException e) {
            logger.log(Level.ERROR, "Error while creating connections", e);
        }
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            if (!availableConnections.isEmpty()) {
                connection = availableConnections.take();
                takenConnections.put(connection);
            } else if (takenConnections.size() < maxConnectionPoolSize) {
                for (int i = 0; i < maxConnectionPoolSize - connectionPoolSize; i++) {
                    availableConnections.add(new ConnectionProxy(DriverManager.getConnection(applicationProperties.getUrl(),
                            applicationProperties.getUser(), applicationProperties.getPassword())));
                }
                connection = availableConnections.take();
                takenConnections.put(connection);
            }
        } catch (IOException | InterruptedException | SQLException e) {
            logger.log(Level.ERROR, "Error while taking connection", e);
        }
        return connection;
    }

    public void reverseConnection(Connection connection) {
        try {
            takenConnections.remove(connection);
            availableConnections.put(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while reversing connection", e);
        }
    }
}
