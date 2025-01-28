package Hitachi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelReadWrite {
	public static void main(String[] args) {

		try {
			// File path of the XLS file
			String filePath = "C:\\Users\\User\\Downloads\\test1\\CashTransaction_20012025_112236.xls";

			// Load the Excel file
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			Workbook workbook = new HSSFWorkbook(fileInputStream); // HSSFWorkbook for .xls files
			Sheet sheet = workbook.getSheetAt(0); // Get the first sheet (index 0)

			// Read data from the first row and column (example)
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
			String cellValue = cell.getStringCellValue();
			System.out.println("Read value from Excel: " + cellValue);

			// Write data back to a new cell
			Row newRow = sheet.getRow(1); // Get the second row (index 1)
			if (newRow == null) {
				newRow = sheet.createRow(1); // Create row if it doesn't exist
			}
			Cell newCell = newRow.createCell(1); // Create a cell in column 2 (index 1)
			newCell.setCellValue("Test Value");

			// Write the changes back to the file
			fileInputStream.close(); // Close the input stream
			FileOutputStream fileOutputStream = new FileOutputStream(
					new File("C:\\Users\\User\\Downloads\\test1\\CashTransaction_20012025_112236.xlsx"));
			workbook.write(fileOutputStream); // Write changes to the file
			fileOutputStream.close();

			System.out.println("Excel file updated successfully.");

		} catch (IOException e) {
			e.printStackTrace();
	}
}
}