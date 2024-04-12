package toolkit;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabasePath {
    public static String getDatabasePath(String databaseFileName) {
        // Get the current directory of the project
        Path currentDir = Paths.get("").toAbsolutePath();

        // Check the operating system
        String os = System.getProperty("os.name").toLowerCase();
        Path relativePath;
        if (os.contains("win")) {
            // Windows path
            relativePath = Paths.get("database", databaseFileName);
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            // MacOS, Linux, Unix-like path
            relativePath = Paths.get("database", databaseFileName);
        } else {
            // Default path (you can customize this for other OS)
            relativePath = Paths.get("database", databaseFileName);
        }

        // Combine the current directory with the relative path
        Path databasePath = currentDir.resolve(relativePath);

        // Return the absolute path as a string
        return databasePath.toString();
    }
}

