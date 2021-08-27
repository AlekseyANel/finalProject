package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    Properties properties;
    String propertyFilePath = "src/main/resources/config.txt";

    public ConfigFileReader() {
        BufferedReader reader;
        // Open the file
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the config.txt file.");
    }

/*    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the config.txt file.");
    }*/

    public String getUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url is not specified in the config.txt file.");
    }

    public String getUrlHero() {
        String url1 = properties.getProperty("urlHero");
        if (url1 != null) return url1;
        else throw new RuntimeException("getUrlHero is not specified in the config.txt file.");
    }

    public String getUser() {
        String email = properties.getProperty("userName");
        if (email != null) return email;
        else throw new RuntimeException("email is not specified in the config.txt file.");
    }

    public String getPassword() {
       String password = properties.getProperty("password");
        if (password != null) return password;
        else throw new RuntimeException("password is not specified in the config.txt file.");
    }
    public String getIsbn() {
        String isbn = properties.getProperty("isbn");
        if (isbn != null) return isbn;
        else throw new RuntimeException("isbn is not specified in the config.txt file.");
    }
    public String getIsbn1() {
        String isbn1 = properties.getProperty("isbn1");
        if (isbn1 != null) return isbn1;
        else throw new RuntimeException("isbn1 is not specified in the config.txt file.");
    }
}
