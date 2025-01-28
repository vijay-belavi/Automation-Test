package Hitachi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipExtractor {

    public static void extractZipFile(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        // Create output directory if it doesn't exist
        if (!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        // Buffer for read and write data to file
        byte[] buffer = new byte[1024];
        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            System.out.println("Unzipping to " + newFile.getAbsolutePath());
            // Create directories for sub directories in zip
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            // Close this ZipEntry
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        // Close last ZipEntry
        zis.closeEntry();
        zis.close();
        fis.close();
    }
    
    public static List<String> fetchFilePaths(String folderPath, boolean recursive) {
        List<String> filePaths = new ArrayList<>();
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path: " + folderPath);
            return filePaths;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filePaths.add(file.getAbsolutePath());
                } else if (file.isDirectory() && recursive) {
                    filePaths.addAll(fetchFilePaths(file.getAbsolutePath(), true));
                }
            }
        }

        return filePaths;
    }
    public static void main(String[] args) {
        try {
            String zipFilePath = "C:\\Users\\User\\Downloads\\Cash_Transaction_Report_20012025112236.zip";
            String destDir = "C:\\Users\\User\\Downloads\\test";
            extractZipFile(zipFilePath, destDir);
            
            List<String> filePaths = fetchFilePaths(destDir, true); // Set true for recursive fetching

            // Print all file paths
            filePaths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}