package by.bsuir.entity;

import by.bsuir.util.PropertyReader;

import java.io.IOException;

public class ApplicationProperties {
    private static ApplicationProperties instance;
    private final PropertyReader propertyReader;

    private ApplicationProperties() throws IOException {
        propertyReader = new PropertyReader();
    }

    public static ApplicationProperties getInstance() throws IOException {
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }

    public String getUrl() throws IOException {
        return propertyReader.getProperties().getProperty("URL");
    }

    public String getUser() throws IOException {
        return  propertyReader.getProperties().getProperty("USER");
    }

    public String getPassword() throws IOException {
        return propertyReader.getProperties().getProperty("PASSWORD");
    }

    public int getConnectionPoolSize() throws IOException {
        return Integer.parseInt(propertyReader.getProperties().getProperty("connectionPoolSize"));
    }

    public int getMaxConnectionPoolSize() throws IOException {
        return Integer.parseInt(propertyReader.getProperties().getProperty("maxConnectionPoolSize"));
    }

    public String getImagePath() throws IOException {
        return propertyReader.getProperties().getProperty("PathToImage");
    }
}
