package Hitachi;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XlstoXlsxUsingAsposeBrowser {
	public static void main(String[] args) {
		try {
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().window().maximize();

			driver.get("https://products.aspose.app/cells/conversion/xls-to-xlsx");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			WebElement fileUpload = driver.findElement(By.xpath("//input[@type='file']"));

			driver.findElement(By.xpath("//form[@id='UploadFile']")).click();

			fileUpload.sendKeys("C:\\Users\\User\\Downloads\\test1\\Test.xls");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='uploadButton']")));

			driver.findElement(By.xpath("//input[@id='uploadButton']")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='DownloadButton']")));
			WebElement downloadButton = driver.findElement(By.xpath("//a[@id='DownloadButton']"));
			downloadButton.click();

			WebElement adsFrame = driver.findElement(By.xpath("//iframe[@name='aswift_3']"));
			driver.switchTo().frame(adsFrame);

			WebElement dismissButton = driver.findElement(By.xpath("//div[@id='dismiss-button']"));
			dismissButton.click();

			driver.switchTo().defaultContent();

			String downloadPath = "C:\\Users\\User\\Downloads"; // Change the path as needed
			File dir = new File(downloadPath);
			String[] previousFiles = dir.list();

			boolean fileDownloaded = false;
			String downloadedFilePath = null;

			while (!fileDownloaded) {
				// Sleep for a few seconds to check for the new file
				Thread.sleep(2000);
				String[] currentFiles = dir.list();
				for (String file : currentFiles) {
					if (!contains(previousFiles, file)) {
						// File downloaded, now capture the full path
						downloadedFilePath = downloadPath + File.separator + file;
						fileDownloaded = true;
						break;
					}
				}
				previousFiles = currentFiles;
			}

			System.out.println("File downloaded successfully: " + downloadedFilePath);

			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Helper function to check if the file already exists in the previous list
	private static boolean contains(String[] arr, String str) {
		for (String s : arr) {
			if (s.equals(str)) {
				return true;
			}
		}
		return false;
	}
}