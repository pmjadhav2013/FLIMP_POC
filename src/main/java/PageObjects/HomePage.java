package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(id = "login_id")
	private WebElement UserNameTextBox; // = driver.findElement(By.id("login_id"));

	@FindBy(id = "password")
	private WebElement PasswordTextBox; // = driver.findElement(By.id("password"));

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement LoginInButton; // = driver.findElement(By.xpath("//button[@type='submit']"));

	@FindBy(xpath = "//h1[normalize-space()='Welcome back! Please log in to your account.']")
	private WebElement WelcomeBackText; // = //h1[normalize-space()='Welcome back! Please log in to your account.']

	@FindBy(xpath = "By.cssSelector(\"input:invalid\")")
	private WebElement validationMessage;

	@FindBy(xpath = "//li[normalize-space()='Credentials do not match']")
	private WebElement InvaliCredentialsErrorMessage;

	@FindBy(xpath = "//li[normalize-space()='Credentials do not match']")
	private WebElement EmptyUsernameValidationMessage;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		// to avoid the steal element exception - calling using special method below
		PageFactory.initElements(driver, this);
	}

	// methods / Actions

	public String ErrorMessageText() {
		String errorMessage = InvaliCredentialsErrorMessage.getText();
		return errorMessage;
	}

	public void ClickOnLoginInButton() {
		LoginInButton.click();
	}

	public void EnterUserName(String username) {

		UserNameTextBox.sendKeys(username);
	}

	public void EnterPassword(String password) {

		PasswordTextBox.sendKeys(password);
	}

	public String VerifyWelComeMessage() {

		WelcomeBackText.isDisplayed();
		System.out.println("\n Welcome message is displayed");
		return WelcomeBackText.getText();
	}
	
	
}
