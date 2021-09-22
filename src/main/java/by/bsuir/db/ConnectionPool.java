package by.bsuir.db;

import by.bsuir.entity.ApplicationProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private ApplicationProperties applicationProperties;
    private int connectionPoolSize;
    private int maxConnectionPoolSize;
    public BlockingDeque<Connection> availableConnections;
    public BlockingDeque<Connection> takenConnections;


    private ConnectionPool() throws IOException, SQLException {
        applicationProperties = ApplicationProperties.getInstance();
        connectionPoolSize = applicationProperties.getConnectionPoolSize();
        maxConnectionPoolSize = applicationProperties.getMaxConnectionPoolSize();
        availableConnections = new LinkedBlockingDeque<>();
        takenConnections = new LinkedBlockingDeque<>();
        initConnectionPool();
    }

    public static ConnectionPool getInstance() throws IOException, SQLException {
        if(connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    private void initConnectionPool() throws IOException, SQLException {
        for (int i = 0; i < connectionPoolSize; i++) {
            Connection connection = DriverManager.getConnection(applicationProperties.getUrl(),
                    applicationProperties.getUser(), applicationProperties.getPassword());
            availableConnections.add(new ConnectionProxy(connection));
        }
    }

    public Connection takeConnection() throws InterruptedException, IOException, SQLException {
        Connection connection = null;
        if(!availableConnections.isEmpty()){
            connection = availableConnections.take();
            takenConnections.put(connection);
        }else if(takenConnections.size() < maxConnectionPoolSize){
            for (int i = 0; i < maxConnectionPoolSize - connectionPoolSize; i++) {
                availableConnections.add(new ConnectionProxy( DriverManager.getConnection(applicationProperties.getUrl(),
                        applicationProperties.getUser(), applicationProperties.getPassword())));
            }
            connection = availableConnections.take();
            takenConnections.put(connection);
        }
        return connection;
    }

    public void reverseConnection(Connection connection) throws InterruptedException {
        takenConnections.remove(connection);
        availableConnections.put(connection);
    }
}
