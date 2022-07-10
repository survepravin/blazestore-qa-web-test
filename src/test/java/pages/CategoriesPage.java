package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class CategoriesPage extends LoadableComponent<CategoriesPage> {

	@FindBy(xpath = "//div[contains(@class,'card h')]")
	WebElement productCard;

	String elementProductTitle = "//h4[@class='card-title']//a[contains(.,'{text}')]";

	private WebDriver driver;

	public CategoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue("Categories page has not loaded correctly", isProductCardVisible());
	}

	@Override
	protected void load() {
		Assert.assertTrue(isProductCardVisible());
	}

	public boolean isProductCardVisible() {
		return productCard.isDisplayed();
	}

	public ProductPage selectProduct(String productName) {
		elementProductTitle = elementProductTitle.replace("{text}", productName);
		WebElement element = driver.findElement(By.xpath(elementProductTitle));
		if (element == null) {
			return null;
		} else {
			element.click();
			return new ProductPage(driver).get();
		}
	}
}