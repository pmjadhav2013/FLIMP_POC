package PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utils.Utilities;

public class EditorPageObjects extends Base.BaseClass {

	WebDriver driver;

	@FindBy(xpath = "//*[@class = 'dashboadtopbar__mainbtn']//button[1]")
	private WebElement CreateNewMicrositeButton;

	@FindBy(xpath = "//input[@id='flimp_title']")
	private WebElement MicrositeTitle;

	@FindBy(xpath = "//div[@class='addToolBtn']")
	private WebElement AddButton_belowDragElementsHere;

	@FindBy(xpath = "//div[@class='addToolBtn']")
	private WebElement AddElementPopup;

	@FindBy(xpath = "//div[normalize-space()='Button']//div[@id='2']")
	private WebElement ButtonElement;

	@FindBy(xpath = "//p[normalize-space()='Add Label']")
	private WebElement AddLabelTextOfButtonElement;

	@FindBy(xpath = "//img[@class='preview']")
	private WebElement PreviewButton;

	@FindBy(xpath = "//span[@class='button text-setting']")
	private WebElement ButtonSettings;
	
	@FindBy(xpath = "//div[@class='header']")
	private WebElement ButtonSettingsPopup;
	
	@FindBy(xpath = "//input[@placeholder='Add Button Text Content']")
	private WebElement ButtonTextContent;
	
	@FindBy(xpath = "//input[@placeholder='Add Button Label']")
	private WebElement ButtonName;
	
	@FindBy(xpath = "//div[@class='header']//img")
	private WebElement ButtonSettingPopupClosebutton;

	
	@FindBy(xpath = "//*[@class = 'btn btn-flimp'][1]")
	private WebElement ButtonNameText;
	
	@FindBy(xpath = "//div[@class='saveAsWrap mylibrary seventh-step']//img")
	private WebElement SaveButton;
	
	@FindBy(xpath = "//span[normalize-space()='Save as File']")
	private WebElement SaveAsFileOption;
		
	@FindBy(xpath = "//ul[contains(@class,'save-menu')]")
	private WebElement SaveAsFileOptionMenu;
	
	public void CloseButtonSettingPopup() throws InterruptedException {
		ButtonSettingPopupClosebutton.click();
		System.out.println("\n Button Setting Popup is Closed");
	}
	
	public void ClickOnSaveMicrositeButton() throws InterruptedException {
		Thread.sleep(5000);
		Actions act = new Actions(driver);
		act.moveToElement(SaveButton).perform();
		Thread.sleep(2000);
		SaveButton.click();
	}
	
	public void CheckTheOptionAndSaveMicrosite() throws InterruptedException, TimeoutException {
		SaveAsFileOption.click();
		// wait mechanism
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOf(SaveAsFileOptionMenu));
		// You can now interact with the element
		element.click();
		Thread.sleep(5000);
		System.out.println("\n Microsite is saved");
	}
	
	public void CheckButtonName() throws InterruptedException {
		
		Assert.assertEquals(ButtonNameText.getText().contains(prop.getProperty("buttonName")), true);
		System.out.println( "\n" + ButtonNameText.getText() + "is visible");
	}
	
	public void ChangeButtonName(String name) throws InterruptedException {
		Thread.sleep(2000);
		// String buttonName = prop.getProperty("buttonName");
		ButtonTextContent.clear();
		ButtonTextContent.sendKeys(name + Utilities.generateTimeStamp());
		Thread.sleep(2000);
		System.out.println("\n New Button Name Entered as :-> " + name + Utilities.generateTimeStamp());
	}
	
	public void VerifyButtonSettingsPopupDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(ButtonSettingsPopup.isDisplayed(), true);
		System.out.println("\n Button setting popup is Displayed");
	}
	
	public void Move_HoverToButton() throws InterruptedException {
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		act.moveToElement(AddLabelTextOfButtonElement).perform();
		System.out.println("\n Moved to Add new Button");
	}
	
	public void ClickOnButtonSettings() throws InterruptedException {
		ButtonSettings.click();
		System.out.println("\n Clicked on Button Settings");
	}
	
	public void CheckPreviewButtonIsDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(PreviewButton.isDisplayed(), true);
		System.out.println("\n Preview Button is displayed");
	}
	
	public void ClickOnPreviewButton() throws InterruptedException {
		Thread.sleep(3000);
		PreviewButton.click();
		System.out.println("\n Preview Button is Clicked");
	}
	
	public void SwithToNewTab() throws InterruptedException {
		Thread.sleep(3000);
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		System.out.println("\n Control Switched to New Tab");
	}
	
	public void SwithToContentLibraryTab() throws InterruptedException {
		Thread.sleep(3000);
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		System.out.println("\n Control Switched to Content Library page");
	}
	
// later make this method generalize to add the any element by providing the element name from the config / excel file
	public void AddButtonElementAndVerify() throws InterruptedException {
		Thread.sleep(5000);
		ButtonElement.click();
		Thread.sleep(2000);
		Assert.assertEquals(AddLabelTextOfButtonElement.isDisplayed(), true);
		System.out.println("\n Button Element is Added to the microsite");
	}

	public void CheckAddElementPopupDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(AddElementPopup.isDisplayed(), true);
		System.out.println("\n Add elements popup is displayed");
	}

	public void ClickOnAddButton() {
		AddButton_belowDragElementsHere.click();
		System.out.println("\n Add button is clicked");
	}

	public EditorPageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		// to avoid the steal element exception - calling using special method below
		PageFactory.initElements(driver, this);
	}

//	public void CreateNewMicrosite()
//	{
//		CreateNewMicrositeButton.click();
//		System.out.println("\n User Clicked on create new microsite button");
//	}

	public void WaitForEditorPageLoad() {
		// implemented explicit wait here to check the editor page get loaded
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//i[@class='fa fa-plus']")));
		System.out.println("\n Editor Page is loaded");
	}

	public void CheckEditorPageURL() {
		Assert.assertEquals(driver.getCurrentUrl().contains(prop.getProperty("editorURL")), true);
		System.out.println("\n Editor Page Displayed and the page URL is :-> \n" + driver.getCurrentUrl() + "\n");
	}
}
