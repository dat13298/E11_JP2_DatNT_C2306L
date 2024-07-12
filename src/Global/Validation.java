package Global;

import java.io.File;

public class Validation {
    public static boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
