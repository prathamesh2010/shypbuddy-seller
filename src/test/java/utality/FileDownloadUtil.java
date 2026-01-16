package utality;

import java.io.File;

public class FileDownloadUtil {

    public static boolean waitForFileDownload(String fileExtension, int timeoutSeconds) {

        // 🔹 DOWNLOAD DIRECTORY
        File dir = new File(System.getProperty("user.dir") + "/downloads");

        // ✅ CREATE DIRECTORY IF NOT EXISTS
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                // ❌ DO NOT throw exception for SoftAssert flow
                System.out.println("Failed to create download directory: " + dir.getAbsolutePath());
                return false; // ✅ CHANGE #1
            }
        }

        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);

        while (System.currentTimeMillis() < endTime) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(fileExtension));
            if (files != null && files.length > 0) {
                return true; // ✅ CHANGE #2 (return boolean)
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }

        return false; // ✅ CHANGE #3 (NO exception)
    }
}
