package by.bsuir.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private final Properties properties = new Properties();

    public PropertyReader() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
        properties.load(inputStream);
    }

    public Properties getProperties() throws IOException {
        return properties;
    }
}
