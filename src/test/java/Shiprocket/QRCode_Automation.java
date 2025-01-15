package Shiprocket;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRCode_Automation {
	public static void main(String[] args) throws Throwable {

		// WebDriver driver = new ChromeDriver();

		/*
		 * WebElement qrCodeElement =
		 * driver.findElement(By.xpath("//img[@id='qr-code']")); File qrCodeImage =
		 * qrCodeElement.getScreenshotAs(OutputType.FILE); FileHandler.copy(qrCodeImage,
		 * new File("C:\\Users\\User\\Downloads\\qr-test.png"));
		 */

		BufferedImage bufferedImage = ImageIO
				.read(new File("C:\\Users\\User\\OneDrive\\Pictures\\Screenshots\\Screenshot 2025-01-10 121017.png"));
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap);
		String a = result.toString();

		// Remove the prefix "upi://pay?"
		String parameters = a.substring(a.indexOf('?') + 1);

		// Split the string into key-value pairs
		String[] pairs = parameters.split("&");

		// Store the key-value pairs in a Map
		Map<String, String> map = new HashMap<>();
		for (String pair : pairs) {
			String[] keyValue = pair.split("=", 2); // Split by '=' only once
			String key = keyValue[0];
			String value = keyValue.length > 1 ? keyValue[1] : ""; // Handle cases where there's no value
			map.put(key, value);
		}

		// Print the Map
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

}
