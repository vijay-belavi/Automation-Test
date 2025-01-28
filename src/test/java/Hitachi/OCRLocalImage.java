package Hitachi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRLocalImage {
	public static void main(String[] args) throws Throwable {
		try {
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();

			// Navigate to the webpage
			driver.navigate().to("https://cbsuat.hitachi-cashms.com/CBS/Home.aspx");

			// Switch to iframe containing the captcha
			WebElement iframeElement = driver.findElement(By.xpath("//iframe[@name='RadWindowLogIn']"));
			driver.switchTo().frame(iframeElement);

			WebElement username = driver.findElement(By.xpath("//input[@name='LoginBox']"));
			username.sendKeys("FIREFLINK01");

			WebElement password = driver.findElement(By.xpath("//input[@name='PasswordBox']"));
			password.sendKeys("Hitachi@123");

			// Locate the captcha image
			WebElement captchaElement = driver.findElement(By.xpath("//img[@id='imgCaptchaLogin']"));

			// Take a screenshot of the captcha image
			File screenshot = captchaElement.getScreenshotAs(OutputType.FILE);
			String filePath = "C:\\Users\\User\\Downloads\\test.png";
			File file = new File(filePath);
			FileHandler.copy(screenshot, file);

			Thread.sleep(2000);
			// Load the image from the local path
			BufferedImage image = ImageIO.read(new File(filePath));

			// Initialize Tesseract OCR
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata"); // Absolute path to tessdata
			tesseract.setLanguage("eng"); // Set language to English

			// Perform OCR on the image
			String text = tesseract.doOCR(image);
			System.out.println("Extracted Text: " + text);
			String test = null;
			int count = 0;
			if (text.isEmpty()) {
				System.out.println("If Condition");
				test = captchCapture(count, filePath, captchaElement);
			} else {
				System.out.println("Else Condition");
				test = text;
			}
			System.out.println("Image Text: "+test);
			System.out.println(test.length());
			
			WebElement captchTextField = driver.findElement(By.xpath("//input[@name='txtCaptchaLogin']"));
			Thread.sleep(5000);
			captchTextField.sendKeys(test);

			// driver.findElement(By.xpath("//input[@name='btnLogin_input']")).click();

			Thread.sleep(5000);

			// Quit WebDriver
			// driver.quit();
		} catch (IOException | TesseractException e) {
			e.printStackTrace();
		}
	}

	public static String captchCapture(int count, String filePath, WebElement element) {
		String string1 = null;
		try {
			File screenshot = element.getScreenshotAs(OutputType.FILE);
			File file = new File(filePath);
			FileHandler.copy(screenshot, file);
			BufferedImage image = ImageIO.read(new File(filePath));

			// Initialize Tesseract OCR
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata"); // Absolute path to tessdata
			tesseract.setLanguage("eng"); // Set language to English

			// Perform OCR on the image
			String text1 = tesseract.doOCR(image);
			System.out.println("Extracted Text: " + text1);

		} catch (StaleElementReferenceException e) {
			captchCapture(count, filePath, element);
			if (count < 3) {
				count++;
			}
			else {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return string1;
	}
}
