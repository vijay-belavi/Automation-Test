package Torus_1;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Verify_Connections {
	public static void main(String[] args) throws Throwable {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		// Initialize the Appium driver
		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println("Airplane Mode Status: " + driver.getConnection().isAirplaneModeEnabled());
		System.out.println("Wifi Status: " + driver.getConnection().isWiFiEnabled());
		System.out.println("Mobile Data Status: " + driver.getConnection().isDataEnabled());
		System.out.println("WIFI MASK: " + driver.getConnection().WIFI_MASK);
		System.out.println("Airplane Mask Mode: " + driver.getConnection().AIRPLANE_MODE_MASK);
		System.out.println("Bit Mask: " + driver.getConnection().getBitMask());
		System.out.println("Hash Code: " + driver.getConnection().hashCode());
		System.out.println("Connection Details: " + driver.getConnection().toString());
		System.out.println("Data Mask: " + driver.getConnection().DATA_MASK);
	}
}
