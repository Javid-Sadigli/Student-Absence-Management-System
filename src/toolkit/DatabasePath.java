package toolkit;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabasePath {
    public static String getDatabasePath(String databaseFileName) {
        // Get the current directory of the project
        Path currentDir = Paths.get("").toAbsolutePath();
        Path relativePath;

        relativePath = Paths.get("database", databaseFileName);

        // Combine the current directory with the relative path
        Path databasePath = currentDir.resolve(relativePath);

        // Return the absolute path as a string
        return databasePath.toString();
    }
}

