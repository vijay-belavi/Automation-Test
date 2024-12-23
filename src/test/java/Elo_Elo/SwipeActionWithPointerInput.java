package Elo_Elo;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SwipeActionWithPointerInput {
	
    public static void main(String[] args) throws Throwable {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("noReset", "true"); // Change as needed

        AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        String commentBoxXpath = "//android.view.ViewGroup[@resource-id='com.eloelo:id/commentsHostRecyclerviewContainer']";
        String usernameInComment = "//android.widget.TextView[@resource-id='com.eloelo:id/userNameCommentLive']";
        ArrayList<String> arrayList = new ArrayList();
        String  username = "";
        String userNames = "Aakash";
        boolean isDisplayed = true;
        try {
        	test(driver, commentBoxXpath, usernameInComment, arrayList, username, userNames, isDisplayed);
		} catch (Exception e) {
			System.out.println("Failed To Swipe");
			e.printStackTrace();
		}
        
    }

    public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY, Duration duration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
    
    public static void test (AppiumDriver driver,  String commentBoxXpath, String usernameInComment, ArrayList<String> arrayList, String username, String userNames, boolean isDisplayed)
	{
    	 //Jaswinder
		try{
			WebElement element = driver.findElement(By.xpath(commentBoxXpath));
        	int startX =element.getSize().getWidth()/2;
        	int startY =element.getLocation().getY();
        	int endX =element.getSize().getWidth()/2;
        	int endY =startY+element.getSize().getHeight();
        	int num = 0;
        	
        	while(driver.findElement(By.xpath(usernameInComment)).isDisplayed() && isDisplayed) {
        		swipe(driver, startX, startY, endX, endY, Duration.ofSeconds(1, 400_000_000));
        		if (driver.findElement(By.xpath(usernameInComment)).isDisplayed()) {
        			List<WebElement> elements = driver.findElements(By.xpath(usernameInComment));
        			for (WebElement webElement : elements) {
        				username = webElement.getText();
        				arrayList.add(username);
        				if (username.equalsIgnoreCase(userNames)) {
    						System.out.println(username);
    						isDisplayed=false;
    						break;
    					}
					}
				}
        	}
		}catch(StaleElementReferenceException E) {
			test(driver, commentBoxXpath, usernameInComment, arrayList, username, userNames, isDisplayed);
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
	}
}