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

    public String getUrlAccount() {
        String url = properties.getProperty("urlAccount");
        if (url != null) return url;
        else throw new RuntimeException("... is not specified in the config.txt file.");
    }
    public String getUrlBookStore() {
        String url = properties.getProperty("urlBookStore");
        if (url != null) return url;
        else throw new RuntimeException("... is not specified in the config.txt file.");
    }
    public String getUrlDemoQA() {
        String url = properties.getProperty("urlDemoQA");
        if (url != null) return url;
        else throw new RuntimeException("... is not specified in the config.txt file.");
    }
    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("... is not specified in the config.txt file.");
    }

    public String getEmail() {
        String email = properties.getProperty("email");
        if (email != null) return email;
        else throw new RuntimeException("... is not specified in the config.txt file.");
    }

    public String getUser() {
        String userName = properties.getProperty("userName");
        if (userName != null) return userName;
        else throw new RuntimeException("userName is not specified in the config.txt file.");
    }

    public String getPassword() {
       String password = properties.getProperty("password");
        if (password != null) return password;
        else throw new RuntimeException("password is not specified in the config.txt file.");
    }
    public String getFirstName() {
        String firstName = properties.getProperty("firstName");
        if (firstName != null) return firstName;
        else throw new RuntimeException("firstName is not specified in the config.txt file.");
    }
    public String getLastName() {
        String lastName = properties.getProperty("lastName");
        if (lastName != null) return lastName;
        else throw new RuntimeException("lastName is not specified in the config.txt file.");
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
