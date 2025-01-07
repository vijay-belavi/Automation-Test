package Shiprocket;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HideAutomationPrompt {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" }); // Remove "automation"
																								// flag
		options.setExperimentalOption("useAutomationExtension", false); // Disable automation extension

		// Initialize WebDriver with ChromeOptions
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		// Open a website
		driver.get("https://www.nseindia.com/market-data/all-upcoming-issues-ipo");

		// Perform other automation actions...
		String a = "Available Limit\r\n"
				+ "₹ 171.74\r\n"
				+ "Withdrawable Balance\r\n"
				+ "₹ 171.74";
		// Quit the driver
		//driver.quit();
	}
}
