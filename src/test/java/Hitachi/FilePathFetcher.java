package Hitachi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePathFetcher {

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\User\\Downloads\\test"; // Replace with your folder path
        List<String> filePaths = fetchFilePaths(folderPath, true); // Set true for recursive fetching

        // Print all file paths
        filePaths.forEach(System.out::println);
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
}