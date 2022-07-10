package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.BrowserFactory;
import utils.EnvironmentVariables;

public class BaseTest {

	public static WebDriver driver;
	public static Scenario scenario;
	
	@Before("@web")
	public void beforeWeb(Scenario scenario) {
		BaseTest.scenario = scenario;
		
		String toggle = System.getProperty("headless");
		String browserState = toggle != null ? toggle : EnvironmentVariables.HEADLESS;
		
		driver = BrowserFactory.getDriver(EnvironmentVariables.BROWSER, browserState);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(EnvironmentVariables.BASE_URL);
		
		scenario.log(EnvironmentVariables.BASE_URL);
		
		EnvironmentVariables.WEB_USERNAME = RandomStringUtils.randomAlphanumeric(8);
		EnvironmentVariables.WEB_PASSWORD = RandomStringUtils.randomAlphanumeric(8);
	}

	@After("@web")
	public void afterWeb(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		if (driver != null) driver.quit();
	}
}
