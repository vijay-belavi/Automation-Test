package Hitachi;
import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class JExcelAPIExample {
    public static void main(String[] args) {
        // Path to the .xls file
        String filePath = "C:\\Users\\User\\Downloads\\example.xls";

        try {
            // Open the workbook
            Workbook workbook = Workbook.getWorkbook(new File(filePath));

            // Get the first sheet
            Sheet sheet = workbook.getSheet(0);

            // Loop through rows and columns
            for (int row = 0; row < sheet.getRows(); row++) {
                for (int col = 0; col < sheet.getColumns(); col++) {
                    // Read the cell content
                    Cell cell = sheet.getCell(col, row);
                    System.out.print(cell.getContents() + "\t");
                }
                System.out.println(); // New line after each row
            }

            // Close the workbook
            workbook.close();

        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }
}
