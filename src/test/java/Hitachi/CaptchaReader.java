package Hitachi;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class CaptchaReader {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();

		driver.navigate().to("https://cbsuat.hitachi-cashms.com/CBS/Home.aspx");

		

		boolean homePage = false; // Tracks if login was successful
		String catpchaText = null;
		do {
			WebElement iframeElement = driver.findElement(By.xpath("//iframe[@name='RadWindowLogIn']"));
			driver.switchTo().frame(iframeElement);
			// Capture CAPTCHA image
			catpchaText = captcha(driver);
			
			WebElement username = driver.findElement(By.xpath("//input[@name='LoginBox']"));
			username.clear();
			username.sendKeys("FIREFLINK01");
			WebElement password = driver.findElement(By.xpath("//input[@name='PasswordBox']"));
			password.clear();
			password.sendKeys("Hitachi@123");

			// Fill login details and submit
			driver.findElement(By.xpath("//input[@name='txtCaptchaLogin']")).sendKeys(catpchaText);
			driver.findElement(By.xpath("//input[@name='btnLogin_input']")).click();

			// Check if login was successful
			try {
				driver.switchTo().defaultContent();
				homePage = driver.findElement(By.xpath("//span[text()='Masters']")).isDisplayed();
				System.out.println("Login successful: " + homePage);
			}catch (StaleElementReferenceException e) {
				System.out.println("Login failed. Retrying...");
				homePage = false;
				// Ensure loop retries if login fails
			} 
			catch (Exception e) {
				System.out.println("Login failed. Retrying...");
				homePage = false; // Ensure loop retries if login fails
			}

		} while (!homePage); // Continue looping until login is successful

		System.out.println("Successfully logged in!");
		driver.quit();
	}

	public static String captcha(WebDriver driver) throws Throwable {
		String captchaText = null;
		try {
			File captchImage = driver.findElement(By.xpath("//img[@id='imgCaptchaLogin']"))
					.getScreenshotAs(OutputType.FILE);
			File newFile = new File("C:\\Users\\User\\Downloads\\test.PNG");
			FileHandler.copy(captchImage, newFile);

			// Initialize Tesseract OCR
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // Set Tesseract data path
			

			try {
				// Extract text from CAPTCHA image
				captchaText = tesseract.doOCR(newFile).trim();
				System.out.println("Captcha Text: " + captchaText);
			} catch (TesseractException e) {
				System.out.println("Error reading CAPTCHA: " + e.getMessage());
				captcha(driver); // Retry the loop if OCR fails
			}

			if (captchaText.isEmpty()) {
				System.out.println("Captcha text is empty. Retrying...");
				driver.findElement(By.xpath("//input[@name='btnRefreshLogin']")).click();
				captcha(driver);
			}
		} catch (StaleElementReferenceException e) {
			captcha(driver);
		}
		
		return captchaText;
	}
}
