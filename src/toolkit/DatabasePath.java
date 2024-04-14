package toolkit;

import java.nio.file.Paths;

/**
 *  A database path class to show the correct path of database for saving objects.
 */
public class DatabasePath {

    /**
     * Gives the path of the database file for saving objects.
     * @param modelName The name of the Model to which the object to be saved belongs.
     * @return The path of the database file for saving the object whose modelName is given.
     */
    public static String getDatabasePath(String modelName) {
        // Get the current directory of the project
        String currentDir = Paths.get("").toAbsolutePath().toString();
    
        if (currentDir.endsWith("src")) 
        {   
            return "./database/"+ modelName + ".fdb"; 
        }
        else 
        {
            return "./src/database/"+ modelName + ".fdb";
        }
    }
}

