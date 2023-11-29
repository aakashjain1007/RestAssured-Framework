package constantPaths;

import lombok.Getter;

public final class FilePath {

    private static @Getter
    final String CURRENT_DIRECTORY = System.getProperty("user.dir");

    private static @Getter
    final String CONFIG_INI = CURRENT_DIRECTORY + "/src/main/java/configProperties/config.ini";
}
