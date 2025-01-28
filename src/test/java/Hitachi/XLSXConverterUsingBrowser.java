package Hitachi;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XLSXConverterUsingBrowser {
    public static void main(String[] args) {
        try {
        	ChromeOptions options = new ChromeOptions();

    		String[] arr = new String[] { "enable-automation" };
    		options.setExperimentalOption("excludeSwitches", arr);
    		options.setExperimentalOption("useAutomationExtension", false);
    		options.addArguments("--headless");  
            options.addArguments("--disable-gpu");  
            options.addArguments("--no-sandbox"); 
    		
    		WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            // Record the script execution start time
            long executionStartTime = Instant.now().toEpochMilli();

            driver.get("https://convertio.co/xls-xlsx/");

            WebElement fileUpload = driver.findElement(By.xpath("//label[@for='pc-upload-add']"));
            WebElement filePath = driver.findElement(By.xpath("//input[@id='pc-upload-add']"));
            
            // Provide the file path to be uploaded
            filePath.sendKeys("C:\\Users\\User\\Downloads\\test1\\CashTransaction_20012025_112236.xls");

            // Click on the Convert button
            driver.findElement(By.xpath("//div[@class='convert-button']//button[contains(text(),'Convert') and @type='button']")).click();
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Set the timeout to 30 seconds
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Conversion completed!']")));
            
            
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30)); // Timeout of 30 seconds
            wait1.until((ExpectedCondition<Boolean>) wd ->
                    ((String) ((org.openqa.selenium.JavascriptExecutor) wd)
                            .executeScript("return document.readyState"))
                            .equals("complete"));

            System.out.println("Page has fully loaded.");

            // Wait for the Download button and click it
            WebElement downloadElement = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Download']")));
            downloadElement.click();
            
            
            // Specify the download directory path
            String downloadDir = "C:\\Users\\User\\Downloads";

            // Wait for the latest downloaded file
            File latestFile = waitForLatestFile(downloadDir, executionStartTime, 30);

            if (latestFile != null) {
                System.out.println("Latest downloaded file: " + latestFile.getAbsolutePath());
            } else {
                System.out.println("No valid files found within the time limit.");
            }

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File waitForLatestFile(String dirPath, long startTime, int timeoutInSeconds) {
        File latestFile = null;
        long timeout = System.currentTimeMillis() + (timeoutInSeconds * 1000);

        while (System.currentTimeMillis() < timeout) {
            latestFile = getLatestFileFromDir(dirPath);
            if (latestFile != null && latestFile.lastModified() > startTime) {
                return latestFile; // Valid file found
            }

            try {
                Thread.sleep(1000); // Wait for 1 second before retrying
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        return null; // Return null if no valid file is found within the time limit
    }

    public static File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return null;
        }

        // Get all files in the directory
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        // Sort files by last modified date in descending order
        return Arrays.stream(files)
                .filter(File::isFile) // Consider only files (not directories)
                .max(Comparator.comparingLong(File::lastModified)) // Get the latest file
                .orElse(null);
    }
}
