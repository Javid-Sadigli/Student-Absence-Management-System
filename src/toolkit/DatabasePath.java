package toolkit;

import java.nio.file.Paths;

public class DatabasePath {
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

