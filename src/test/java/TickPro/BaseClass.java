package TickPro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	WebDriver driver = null;
@BeforeTest
public WebDriver test() {
	driver = new ChromeDriver();
	return driver;
}
}
