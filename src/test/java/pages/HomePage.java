package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utils.WebDriverWrapper;

public class HomePage extends LoadableComponent<HomePage> {

	@FindBy(xpath = "//a[@id='signin2']")
	WebElement linkSignUp;

	@FindBy(xpath = "//a[@id='login2']")
	WebElement linkLogin;

	@FindBy(xpath = "//a[@id='logout2']")
	WebElement linkLogout;

	@FindBy(xpath = "//div[@id='navbarExample']//a[@href='index.html']")
	WebElement linkHome;

	@FindBy(id = "cat")
	WebElement navCategories;

	@FindBy(xpath = "//a[@id='cat']/../a[contains(.,'Phones')]")
	WebElement leftNavPhones;

	@FindBy(xpath = "//a[@id='cat']/../a[contains(.,'Laptops')]")
	WebElement leftNavLaptops;

	@FindBy(xpath = "//a[@id='cat']/../a[contains(.,'Monitors')]")
	WebElement leftNavMonitors;

	@FindBy(id = "cartur")
	WebElement linkCart;

	private WebDriver driver;
	WebDriverWrapper wrapper;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue("Home page  has not loaded correctly", isCategoriesVisible());
	}

	@Override
	protected void load() {
		Assert.assertTrue(isCategoriesVisible());
	}

	public boolean isCategoriesVisible() {
		return navCategories.isDisplayed();
	}

	public boolean isLogoutVisible() {
		return linkLogout.isDisplayed();
	}
	
	public SignInSignUpPage navigateToLoginPage() {
		linkLogin.click();
		wrapper.wait(1000);
		return new SignInSignUpPage(driver);
	}

	public SignInSignUpPage navigateToSignUpPage() {
		linkSignUp.click();
		wrapper.wait(2000);
		return new SignInSignUpPage(driver);
	}

	public HomePage navigateToHomePage() {
		linkHome.click();
		return new HomePage(driver).get();
	}

	public CartPage navigateToCartPage() {
		linkCart.click();
		return new CartPage(driver).get();
	}

	public CategoriesPage navigateToCategories(String categoryName) {
		switch (categoryName.toUpperCase()) {
		case "PHONES":
			leftNavPhones.click();
			break;
		case "LAPTOPS":
			leftNavLaptops.click();
			break;
		case "MONITORS":
			leftNavMonitors.click();
			break;
		default:
			break;
		}
		wrapper.wait(1000);
		return new CategoriesPage(driver).get();
	}
	
	public HomePage logout() {
		linkLogout.click();
		return new HomePage(driver).get();
	}
}