package SpiceMoney;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandlePopUpWithCoordinates {
public static void main(String[] args) throws Throwable{
	try {
		ChromeOptions options = new ChromeOptions();

		String[] arr = new String[] { "enable-automation" };
		options.setExperimentalOption("excludeSwitches", arr);
		options.setExperimentalOption("useAutomationExtension", false);
		
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		driver.get("https://stg-webrevamp.spicemoney.com/uatweb/login");
		
		Thread.sleep(50000);
		
		WebElement element =  driver.findElement(By.xpath("//flt-semantics-host/flt-semantics/flt-semantics-container/flt-semantics/flt-semantics-container/flt-semantics/flt-semantics-container/flt-semantics/flt-semantics-container/flt-semantics"));
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		
		int x1 = element.getRect().getHeight();
		int y1 = element.getRect().getWidth();
		
		System.out.println(x+" "+y+" "+x1+" "+y1);
		driver.quit();
		
	} catch (Exception e) {
		e.printStackTrace();
		
	}
}
}
