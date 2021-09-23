package by.bsuir.entity;

import by.bsuir.util.PropertyReader;

import java.io.IOException;

public class ApplicationProperties {
    private static ApplicationProperties instance;

    private ApplicationProperties() {
    }

    public static ApplicationProperties getInstance() {
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }

    public String getUrl() throws IOException {
        return (String) PropertyReader.getInstance().get("URL");
    }

    public String getUser() throws IOException {
        return (String) PropertyReader.getInstance().get("USER");
    }

    public String getPassword() throws IOException {
        return (String) PropertyReader.getInstance().get("PASSWORD");
    }

    public int getConnectionPoolSize() throws IOException {
        return Integer.parseInt((String) PropertyReader.getInstance().get("connectionPoolSize"));
    }

    public int getMaxConnectionPoolSize() throws IOException {
        return Integer.parseInt((String) PropertyReader.getInstance().get("maxConnectionPoolSize"));
    }
}
