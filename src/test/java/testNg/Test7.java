package testNg;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Test7 extends BaseClass{
@Test
public void test(WebDriver driver) {
	driver.get("https://www.shoppersstack.com/");
}
}