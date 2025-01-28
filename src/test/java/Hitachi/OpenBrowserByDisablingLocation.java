package Hitachi;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenBrowserByDisablingLocation {
public static void main(String[] args) {
	

	        // Create ChromeOptions instance
	        
	        
	        // Add preferences to disable geolocation
	     //   options.addArguments("--disable-geolocation");
	        
	        Map<String, Object> prefs = new HashMap<>();
	        prefs.put("profile.default_content_setting_values.geolocation", 2); // 2 = Block
	        System.out.println(prefs);
	        
	        
	        
	        
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", prefs);
	        // Initialize WebDriver with options
	        WebDriver driver = new ChromeDriver(options);
	        
	        
	        
	        
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        driver.manage().window().maximize();
	        
	        // Navigate to a webpage
	        driver.get("https://stg-webrevamp.spicemoney.com/uatweb/login");
	        
	        // Perform actions...
	        
	        // Quit the browser
	     //  driver.quit();
	    }
	}
