package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utils.WebDriverWrapper;

public class ProductPage extends LoadableComponent<ProductPage> {

	@FindBy(xpath = "//a[contains(.,'Add to cart')]")
	WebElement btnAddToCart;

	@FindBy(xpath = "//h2[@class='name']")
	WebElement textProductName;

	private WebDriver driver;
	WebDriverWrapper wrapper;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue("Product page  has not loaded correctly", isAddToCartVisible());
	}

	@Override
	protected void load() {
		Assert.assertTrue(isAddToCartVisible());
	}

	public boolean isAddToCartVisible() {
		return btnAddToCart.isDisplayed();
	}

	public String getProductName() {
		return textProductName.getText();
	}

	public boolean clickAddToCart() {
		try {
			btnAddToCart.click();
			wrapper.handleAlert(driver, 0);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}