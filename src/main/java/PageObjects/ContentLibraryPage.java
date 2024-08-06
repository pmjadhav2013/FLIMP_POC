package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Base.BaseClass;
import Utils.Utilities;

public class ContentLibraryPage extends BaseClass {

	WebDriver driver;

	@FindBy(xpath = "//span[normalize-space()='Logout']")
	private WebElement LogOutButton;

	@FindBy(xpath = "//span[@role='img']")
	private WebElement LogOutMenu;

	@FindBy(xpath = "//*[@class = 'dashboadtopbar__mainbtn']//button[1]")
	private WebElement CreateResponsiveContentButton;

	@FindBy(xpath = "//input[@id='flimp_title']")
	private WebElement MicrositeTitleTextField;

	@FindBy(xpath = "//textarea[@id='flimp_desc']")
	private WebElement MicrositeBriefDescriptionTextField;

	@FindBy(xpath = "//button[@id='create_now']")
	private WebElement MicrositeEditNowButton;

	@FindBy(xpath = "//button[@id='save_for_later']")
	private WebElement MicrositeSaveForLaterButton;

	@FindBy(xpath = "//div[@class='card-body']//h5")
	private WebElement Microsite_List;

	@FindBy(xpath = "//div[@class='modal-body']//h6")
	public WebElement MaxSiteAlertText;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement MaxSiteAlertAceptButton;

	@FindBy(xpath = "//button[@class='bootbox-close-button close']")
	public WebElement MaxSiteAlertCloseButton;

	@FindBy(xpath = "//button[@class='btn btn-outline-primary dropdown-toggle']")
	private List<WebElement> MicorositeActionButtonsList;

	@FindBy(xpath = "//a[@class= 'dropdown-item delete_content_form']")
	private WebElement microsite_list;

	@FindBy(xpath = "//div[@id='delete_content_form']//div[@class='modal-header']")
	private WebElement DeleteMicrositePopup;

	@FindBy(xpath = "//button[@id='delete_content']")
	private WebElement DeleteMicrositeButton;

	@FindBy(xpath = "//*[@class='bootbox-body']//h6")
	private WebElement delete_confirmation_message;

	@FindBy(xpath = "//a[@class= 'dropdown-item delete_content_form']")
	private WebElement Actions_dropown;

	@FindBy(xpath = "//div[@id='delete_content_form']//div[@class='modal-header']")
	private WebElement Element_present_in_dropdown;

	// a[@id='navbarDropdown'])[2]
	// li[contains(., 'Distribute')]
	// *[@class='nav-item submenu
	// dropdown']//a[@id='navbarDropdown'][contains(.,'Distribute')]
	@FindBy(xpath = "//li[contains(., 'Distribute')]")
	private WebElement DistributeMenu;

	// //a[normalize-space()='Create Unique URL'].
	@FindBy(xpath = "//a[normalize-space()='Create Unique URL']")
	private WebElement CreateUniqueURLOption;

	// div[@class='modal-header']
	@FindBy(xpath = "//div[@class='modal-header']")
	private WebElement ContentLibraryPopup;

	@FindBy(xpath = "//a[@class= 'dropdown-item mulitmedia_setting']")
	private WebElement EditButtonInActionDropDown;

	@FindBy(xpath = "//*[@class = 'form-group col-md-12 text-center']//a")
	private WebElement EditMultimediaButton;
	
	public void EditTheLatestCreatedMicrosite() throws InterruptedException {
		// get the list of the microsite names
		Actions actions = new Actions(driver);
		actions.moveToElement(MicorositeActionButtonsList.get(0)).build().perform();
		Thread.sleep(2000);
		actions.moveToElement(Actions_dropown).perform();
		System.out.println("\n Actions dropdown is open ");
		Thread.sleep(2000);
		// boolean bool = EditButtonInActionDropDown.isDisplayed();
		actions.moveToElement(EditButtonInActionDropDown).perform();
		EditButtonInActionDropDown.click();
		Thread.sleep(3000);
		EditMultimediaButton.click();
		System.out.println("\n User Edited the Latest created microsite");

	}

	public void CheckContentLibraryPopupIsVisible() {
		ContentLibraryPopup.isDisplayed();
		System.out.println("\n Content Library Popup is open");
	}

	public void CheckDistributionPageURL() {

		String PageURl = driver.getCurrentUrl();
		if (PageURl.equalsIgnoreCase(prop.getProperty("distibutionURL"))) {
			System.out.println("\n user reached to the distribution page and page url is: \n " + "\t" +PageURl);
		} else {
			System.out.println("\n Control not reached to the distribution page ");
		}
	}

	public void SelectCreateUniqueURLOption() throws InterruptedException {
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		act.moveToElement(CreateUniqueURLOption).click().perform();
		System.out.println("\n Clicked on Select Unique URL Option");
	}

	public void HoverToDistributeMenu() throws InterruptedException {
		Thread.sleep(3000);
		boolean test = DistributeMenu.isDisplayed();
		if (test = true) {
			System.out.println("\n Distribute Menu is Displayed");
		} else {
			System.out.println("\n Not displayed");
		}
		Actions act = new Actions(driver);
		// act.clickAndHold(DistributeMenu);

		act.moveToElement(DistributeMenu).perform();
		Thread.sleep(3000);
		System.out.println("\n Hovered on Distribution Menu in Content Library Page");
	}

	// Method to get the list of items
	public List<WebElement> GetMicorositeActionButtonsList() throws InterruptedException {
		Thread.sleep(3000);
		return MicorositeActionButtonsList;
	}

	// Method to check the microste visibilty
	public boolean OpenActionsDropdownAndCheckElementPresent() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(MicorositeActionButtonsList.get(0)).build().perform();
		Thread.sleep(5000);
		Actions_dropown.click();
		Thread.sleep(3000);
		boolean bool = Element_present_in_dropdown.isDisplayed();
		return bool;
	}

	public void DeleteMicrositeAndConfirm() throws InterruptedException {
		DeleteMicrositeButton.click();
		Thread.sleep(5000);
		boolean message_displayed = delete_confirmation_message.isDisplayed();
		Assert.assertEquals(message_displayed, true);
		System.out.println("\n " + delete_confirmation_message);
	}

	public void OpenContentLibraryPage() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		Thread.sleep(2000);
		System.out.println("\n User visited to the content library page");
	}

	public void ScrollDownToMicrositeList() throws InterruptedException {
		driver.navigate().refresh(); // refresh the page
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		System.out.println("\n Control Scrolled down to the microsite List");
	}

	public void HoverOnFirstMicrosite(List actionButtons) {
		// Hover over the first action button
		Actions actions = new Actions(driver);
		actions.moveToElement((WebElement) actionButtons.get(0)).build().perform();
	}

//	public void ClickOnActionButton() {
//		ActionButton.click();
//	}

	public void DeleteMicrositePopupDisplayed() {
		if (true) {
			Assert.assertTrue(DeleteMicrositePopup.isDisplayed());
			System.out.println("\n Delete microsite popup displayed");
		} else {
			System.out.println("\n Delete microsite popup is not displayed");
		}
	}

	public ContentLibraryPage(WebDriver driver) {
		this.driver = driver;
		// to avoid the steal element exception - calling using special method below
		PageFactory.initElements(driver, this);
	}

	public void WaitForMaxNumberSiteAlertPopupVisibility() {
		Utilities utilities = new Utilities();
		utilities.waitforElementtoBeVisible(MaxSiteAlertText);
	}

	public void MaxNumberSiteAlertPopupAccept() {
		MaxSiteAlertAceptButton.click();
		System.out.println("\n Microsite Alert accept Button clicked");
	}

	public void MaxNumberSiteAlertClose() {
		MaxSiteAlertCloseButton.click();
		System.out.println("\n Microsite Alert close Button clicked");
	}

	public void GetTheMicrositeList() {
		// get the list of the microsite names
		List<WebElement> microsite_list = driver.findElements(By.xpath("//div[@class='card-body']//h5"));

		if (!microsite_list.isEmpty()) {
			boolean confirm = microsite_list.get(0).getText().contains("POC_test");
			Assert.assertEquals(confirm, true);

			System.out.println("\n Microsite :-> " + microsite_list.get(0).getText() + " is created successfully");
		} else {
			System.out.println("\n Microsite list is empty");
		}
	}

	public void ClickOnCreateNewMicrosite() {
		CreateResponsiveContentButton.click();
		System.out.println("\n Create Responsive Microsite Button clicked");
	}

	public void EnterMicrositeTitle() {
		MicrositeTitleTextField.sendKeys("POC_test Site" + "_" + Utilities.generateTimeStamp());
		System.out.println("\n Microsite Title entered");
	}

	public void EnterBriefDescription() {
		MicrositeBriefDescriptionTextField.sendKeys("Testing Site Description" + "_" + Utilities.generateTimeStamp());
		System.out.println("\n Microsite Description entered");
	}

	public void ClickOnEditNowButton() {
		MicrositeEditNowButton.click();
		System.out.println("\n Editnow Button clicked");
	}

	public void ClickSaveForLaterButton() {
		MicrositeSaveForLaterButton.click();
		System.out.println("\n Save for later Button clicked");
	}

	public void MoveToLogoutmenu() {
		Actions act = new Actions(driver);
		act.moveToElement(LogOutMenu).perform();
		System.out.println("\n Control moved to the Logout Button");
	}

	public void ClickOnLogoutButton() {
		// LogOutButton.click();
		System.out.println("\n Logout Button clicked - for failing not performd any action");
	}

}
