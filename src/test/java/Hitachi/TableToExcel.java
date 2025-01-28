package Hitachi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TableToExcel {
    public static void main(String[] args) throws Throwable{
        // Setup WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        driver.get("https://cbsuat.hitachi-cashms.com/CBS/Home.aspx"); // Replace with your URL
        String usn = "FIREFLINK02";
        String pwd = "Hitachi@123";
        
        WebElement iframeElement = driver.findElement(By.xpath("//iframe[@name='RadWindowLogIn']"));
		driver.switchTo().frame(iframeElement);
        
        WebElement username = driver.findElement(By.xpath("//input[@name='LoginBox']"));
		username.clear();
		username.sendKeys(usn);
		WebElement password = driver.findElement(By.xpath("//input[@name='PasswordBox']"));
		password.clear();
		password.sendKeys(pwd);
        // Locate the table
		
		driver.switchTo().defaultContent();
		
		Thread.sleep(30000);
		System.out.println("Searching for element");
        WebElement table = driver.findElement(By.xpath("//table[@class='rgMasterTable']"));

        // Fetch all rows of the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Create a workbook and a sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Table Data");

        // Iterate through rows and columns to fetch data
        int rowIndex = 0;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td")); // Fetch cell data (body rows)
            if (cells.isEmpty()) {
                cells = row.findElements(By.tagName("th")); // Fetch header data if present
            }

            // Create a row in Excel
            Row excelRow = sheet.createRow(rowIndex++);
            int cellIndex = 0;

            for (WebElement cell : cells) {
                // Create a cell in Excel and write data
                Cell excelCell = excelRow.createCell(cellIndex++);
                excelCell.setCellValue(cell.getText());
            }
        }

        // Write the Excel file
        try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\User\\Downloads\\TableData.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Data successfully written to Excel file!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook and WebDriver
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}