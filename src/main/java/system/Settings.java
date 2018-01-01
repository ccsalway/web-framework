package system;

import system.common.Loaders;

import java.io.IOException;
import java.util.Properties;

public class Settings {

    private Properties properties = new Properties();

    //-----------------------------

    private Settings() {
        try {
            Loaders.properties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Settings instance;

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    //-----------------------------

    public Properties getProperties() {
        return properties;
    }

    //-----------------------------

    public String get(String key) {
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
