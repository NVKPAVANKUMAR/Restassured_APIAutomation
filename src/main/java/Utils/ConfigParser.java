package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigParser {

    public static String parser(String key) {
        try {
            FileInputStream fis = new FileInputStream(new File("configuration/config.properties"));
            Properties properties = new Properties();
            properties.load(fis);
            return properties.get(key).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
