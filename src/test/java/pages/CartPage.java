package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utils.WebDriverWrapper;

public class CartPage extends LoadableComponent<CartPage> {

	@FindBy(xpath = "//button[contains(.,'Place Order')]")
	WebElement btnPlaceOrder;

	String linkDeleteProduct = "//td[contains(.,'{text}')]/..//a[contains(.,'Delete')]";

	@FindBy(id = "totalp")
	WebElement textCartTotal;

	@FindBy(id = "name")
	WebElement txtName;

	@FindBy(id = "country")
	WebElement txtCountry;

	@FindBy(id = "city")
	WebElement txtCity;

	@FindBy(id = "card")
	WebElement txtCard;

	@FindBy(id = "month")
	WebElement txtMonth;

	@FindBy(id = "year")
	WebElement txtYear;

	@FindBy(xpath = "//button[contains(.,'Purchase')]")
	WebElement btnPurchase;

	@FindBy(xpath = "//h2[contains(.,'Thank you for your purchase!')]")
	WebElement msgSuccess;

	@FindBy(xpath = "//p[contains(@class,'lead')]")
	WebElement txtOrderDetails;

	@FindBy(xpath = "//button[contains(.,'OK')]")
	WebElement btnOk;

	private WebDriver driver;
	private WebDriverWrapper wrapper;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		Assert.assertTrue("Cart page has not loaded correctly", url.contains("/cart.html"));
	}

	@Override
	protected void load() {
		Assert.assertTrue(isPlaceOrderVisible());
	}

	public boolean isPlaceOrderVisible() {
		return btnPlaceOrder.isDisplayed();
	}

	public CartPage deleteProduct(String productName) {
		linkDeleteProduct = linkDeleteProduct.replace("{text}", productName);
		WebElement element = driver.findElement(By.xpath(linkDeleteProduct));
		if (element == null) {
			return null;
		} else {
			element.click();
			wrapper.wait(1000);
			wrapper.waitForPageLoad();
			return new CartPage(driver).get();
		}
	}

	public String getCartTotal() {
		return textCartTotal.getText();
	}

	public boolean placeOrder(String name, String country, String city, String cardNumber,
			String cardMonth, String cardYear) {
		btnPlaceOrder.click();
		txtName.sendKeys(name);
		txtCountry.sendKeys(country);
		txtCity.sendKeys(city);
		txtCard.sendKeys(cardNumber);
		txtMonth.sendKeys(cardMonth);
		txtYear.sendKeys(cardYear);
		btnPurchase.click();
		return msgSuccess.isDisplayed();
	}

	public String getOrderNumber() {
		String text = txtOrderDetails.getText();
		String orderId = text.split("\n")[0];
		return orderId.split(":")[1].trim();
	}

	public String getOrderTotal() {
		String text = txtOrderDetails.getText();
		String orderTotal = text.split("\n")[1];
		return orderTotal.split(":")[1].trim();
	}

	public void clickOk() {
		btnOk.click();
	}
}