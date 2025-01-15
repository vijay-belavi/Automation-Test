package Shiprocket;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenBrowserWithoutAutomationPrompt {
	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();

		String[] arr = new String[] { "enable-automation" };
		options.setExperimentalOption("excludeSwitches", arr);
		options.setExperimentalOption("useAutomationExtension", false);
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		// Open a website
		driver.get("https://stg-webrevamp.spicemoney.com/uatweb/splash");

		// Quit the driver
		// driver.quit();
	}
}
