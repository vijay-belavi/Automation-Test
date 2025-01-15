package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowDomExample {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the page containing shadow DOM elements
            driver.get("https://stg-webrevamp.spicemoney.com/uatweb/splash");

            // Locate the shadow host element
            WebElement shadowHost = driver.findElement(By.cssSelector("shadow-host-selector"));

            // Access the shadow root directly using getShadowRoot()
            SearchContext shadowRoot = shadowHost.getShadowRoot();

            // Locate the element inside the shadow root
            WebElement shadowElement = shadowRoot.findElement(By.cssSelector("shadow-element-selector"));

            // Perform actions on the element inside the shadow DOM
            shadowElement.click();

            System.out.println("Successfully interacted with the shadow DOM element!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
