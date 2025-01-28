package Hitachi;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTableToExcel {
public static void main(String[] args) throws Throwable{
	WebDriver driver = null;
	try {
		int userInput = 4;
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("http://makeseleniumeasy.com/2020/05/09/storing-web-table-with-pagination-data-into-list-of-map-java/");
		
		// Locate the target element
        WebElement targetElement = driver.findElement(By.xpath("//div[contains(@class,'dataTables_info')]")); // Replace with the element's locator
        
        // Scroll until the element is in the middle of the page
        ((JavascriptExecutor) driver).executeScript(
            "var element = arguments[0];" +
            "var elementRect = element.getBoundingClientRect();" +
            "var absoluteElementTop = elementRect.top + window.scrollY;" +
            "var middleOfPage = window.innerHeight / 2;" +
            "var scrollTo = absoluteElementTop - middleOfPage;" +
            "window.scrollTo({ top: scrollTo, behavior: 'smooth' });",
            targetElement
        );
        	
        List<WebElement> pagination = driver.findElements(By.xpath("//div/span//a[@aria-controls='dtBasicExample']"));
        System.out.println(pagination.size());
        
        if (pagination.size()>=userInput) {
        	for (int k = 0; k < userInput; k++) {
        		try {
    				WebElement table = driver.findElement(By.xpath("//div[@id='dtBasicExample_filter']/following-sibling::table[@id='dtBasicExample']")); // Replace with the actual table ID or locator
                    
                    // Get all rows of the table
                    List<WebElement> rows = table.findElements(By.tagName("tr"));

                    // Loop through rows
                    for (int i = 1; i < rows.size(); i++) {
                        WebElement row = rows.get(i);

                        // Get all cells (columns) in the row
                        List<WebElement> columns = row.findElements(By.tagName("td"));
                        
                        // Loop through columns
                        for (int j = 0; j < columns.size(); j++) {
                            String cellText = columns.get(j).getText();
                            System.out.print(cellText + "\t"); // Print cell value with a tab space
                        }
                        System.out.println();
        		}
    			}
    			catch(Exception e) {
    				System.out.println(e.getLocalizedMessage());
    				System.out.println("Hello");
    			}
        		if (k+1<userInput) {
        			
        			int clickOnNextPage = k+2;
        			driver.findElement(By.xpath("(//div/span//a[@aria-controls='dtBasicExample'])["+clickOnNextPage+"]")).click();
				
    		}
		}
        }
        else {
        	throw new Exception("User Input is Greater than Pages Available.");
        }
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	driver.quit();
}
}
