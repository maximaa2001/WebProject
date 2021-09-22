package by.bsuir.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static Properties instance;

    private PropertyReader(){
    }
    public static Properties getInstance() throws IOException {
        if(instance == null){
            instance = new Properties();
            instance.load(new FileReader("src/main/resources/database.properties"));
        }
        return instance;
    }
}
