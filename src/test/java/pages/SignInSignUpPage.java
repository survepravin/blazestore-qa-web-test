package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utils.WebDriverWrapper;

public class SignInSignUpPage extends LoadableComponent<SignInSignUpPage> {

	@FindBy(id = "sign-username")
	WebElement txtSignUpUsername;

	@FindBy(id = "sign-password")
	WebElement txtSignUpPassword;
	
	@FindBy(id = "loginusername")
	WebElement txtLoginUsername;

	@FindBy(id = "loginpassword")
	WebElement txtLoginPassword;
	
	@FindBy(xpath = "//div[@class='modal-header']//h5[contains(.,'Sign up') or contains(.,'Log in')]")
	WebElement pageHeader;

	@FindBy(xpath = "//button[@type='button' and contains(.,'Sign up')]")
	WebElement btnSignUp;

	@FindBy(xpath = "//button[@type='button' and contains(.,'Log in')]")
	WebElement btnLogIn;

	private WebDriver driver;
	WebDriverWrapper wrapper;

	public SignInSignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue("SignIn/SignUp page has not loaded correctly", isUsernameVisible());
	}

	@Override
	protected void load() {
		Assert.assertTrue("Unable to load SignInSignUpPage", isUsernameVisible());
	}
	
	protected void load(String name) {
		Assert.assertTrue("Unable to load SignInSignUpPage", isUsernameVisible());
	}

	public boolean isUsernameVisible() {
		return pageHeader.isDisplayed();
	}

	public HomePage doSignUp(String username, String password) {
		txtSignUpUsername.sendKeys(username);
		txtSignUpPassword.sendKeys(password);
		btnSignUp.click();
		wrapper.wait(1000);
		driver.switchTo().alert().accept();
		return new HomePage(driver).get();
	}

	public HomePage doLogin(String username, String password) {
		txtLoginUsername.sendKeys(username);
		txtLoginPassword.sendKeys(password);
		btnLogIn.click();
		wrapper.wait(2000);
		return new HomePage(driver).get();
	}
}