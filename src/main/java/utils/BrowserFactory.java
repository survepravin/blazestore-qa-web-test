package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	private static WebDriver driver;

	public static WebDriver getDriver(String browser, String headless) {
		if ("CHROME".equalsIgnoreCase(browser)) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			if ("ON".equalsIgnoreCase(headless)) options.addArguments("headless");
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if ("FIREFOX".equalsIgnoreCase(browser)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if ("EDGE".equalsIgnoreCase(browser)) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			try {
				throw new Exception("Browser not defined...");
			} catch (Exception e) {}
		}
		return driver;
	}
}
