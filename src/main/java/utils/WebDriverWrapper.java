package utils;

import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWrapper {

	private WebDriver driver;
	WebDriverWait wait;

	public WebDriverWrapper(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToVisible(WebElement element, int timeOutInSeconds) {
		wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
		wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void handleAlert(WebDriver driver, int action) {
		wait = new WebDriverWait(driver, 5);
		if ((wait.until(ExpectedConditions.alertIsPresent()) != null)) {
			Alert alert = driver.switchTo().alert();
			if (0 == action) {
				alert.accept();
			} else if (1 == action) {
				alert.dismiss();
			}
		}
	}

	public void waitForElementNotToVisible(WebElement element, int timeOutInSeconds) {
		wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void wait(int timeOutInMillis) {
		try {
			Thread.sleep(timeOutInMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForPageLoad() {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window State       : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String
						.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}
}
