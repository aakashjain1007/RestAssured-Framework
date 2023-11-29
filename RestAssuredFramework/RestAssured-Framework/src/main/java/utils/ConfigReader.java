package utils;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

public class ConfigReader {

    private Ini ini;

    public ConfigReader(String filePath) {
        try {
            ini = new Ini(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getValue(String profileName, String key) {
        Ini.Section profile = ini.get(profileName);
        if (profile != null && profile.containsKey(key)) {
            return profile.get(key);
        } else {
            return null;
        }
    }
}
