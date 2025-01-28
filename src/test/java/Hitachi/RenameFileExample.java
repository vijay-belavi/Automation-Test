package Hitachi;

import java.io.File;

public class RenameFileExample {
    public static void main(String[] args) {
        // Specify the original file
        File originalFile = new File("C:\\Users\\User\\Downloads\\test1\\CashTransaction_20012025_112236.xls");

        // Specify the new file name
        File renamedFile = new File("C:\\Users\\User\\Downloads\\test1\\Test.xls");

        // Attempt to rename the file
        if (originalFile.renameTo(renamedFile)) {
            System.out.println("File renamed successfully!");
        } else {
            System.out.println("Failed to rename the file.");
        }
    }
}
