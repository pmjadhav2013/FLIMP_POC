package FLIMP.POC;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjects.ContentLibraryPage;
import PageObjects.EditorPageObjects;
import PageObjects.HomePage;
import Utils.Utilities;

public class EditorPage extends BaseClass {

	public EditorPage() {
		super(); // constructor to load the properties files
	}

	
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		// driver = initializeBrowserAndOpenApplicationURL("chrome");
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}

//	@Test
//	public void VerifyTheAddButtonOptionIsDisplayed() throws InterruptedException {
//		verifyUserCanOpenTheEditorPage();
//		System.out.println("\n Add button option is displayed");
//	}

	@Test
	public void verifyUserCanOpenTheEditorPage() throws InterruptedException {
		HomePage homepage = new HomePage(driver);
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
		
		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
		homepage.ClickOnLoginInButton();
		// click on the create new microsite button
		ContentLibraryPage.ClickOnCreateNewMicrosite();
		ContentLibraryPage.EnterMicrositeTitle();
		// enter microsite description
		ContentLibraryPage.EnterBriefDescription();
		// click on create new microsite button
		ContentLibraryPage.ClickOnEditNowButton();
		EditorPageObjects.WaitForEditorPageLoad();
		EditorPageObjects.CheckEditorPageURL();
	}

	//@Test
	public void VerifyUserCanAddTheButtonElement() throws InterruptedException {
		verifyUserCanOpenTheEditorPage();
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
		//driver.findElement(By.xpath("//div[@class='addToolBtn']")).click();
		
		EditorPageObjects.ClickOnAddButton();
		// check the add eleemnts popup is displayed

		EditorPageObjects.CheckAddElementPopupDisplayed();
		Thread.sleep(2000);
		// select the button element

		EditorPageObjects.AddButtonElementAndVerify();
	}

	@Test
	public void Verify_Preview_Button_Is_Visible_On_The_Editorpage() throws InterruptedException {
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
		verifyUserCanOpenTheEditorPage();
		// check preview button is visible 
		EditorPageObjects.CheckPreviewButtonIsDisplayed();		
	}

	@Test
	public void Verify_Button_Is_Visible_in_the_Preview() throws InterruptedException {
		//
		//VerifyUserCanAbleToChangeTheButtonName();
		VerifyUserCanAddTheButtonElement();
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
		String buttonName = "Preview Test_";
		EditorPageObjects.Move_HoverToButton();	
		EditorPageObjects.ClickOnButtonSettings();
		EditorPageObjects.ChangeButtonName(buttonName);
		EditorPageObjects.CheckPreviewButtonIsDisplayed();	
		EditorPageObjects.ClickOnPreviewButton();
		
		Thread.sleep(3000);
		
		// switch to new tab

		EditorPageObjects.SwithToNewTab();
		// check the button element is visible
		
		
		String xpath = "//div[@class='text-center']//div[contains(@id, 'element') and contains(@class, 'btn') and contains(text(), '"
				+ buttonName + "')]";

		WebElement element = driver.findElement(By.xpath(xpath)); // Replace with your element's identifier
		if (element.isDisplayed()) {
			System.out.println("\n Button Element is displayed.");
		} else {
			System.out.println("\n Button Element is not displayed.");
		}
	}

	//@Test
	public void VerifyUserCanAbleToChangeTheButtonName(String buttonName) throws InterruptedException {
		VerifyUserCanAddTheButtonElement();
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);

		// move to button
		EditorPageObjects.Move_HoverToButton();		

		// click on button setting
		EditorPageObjects.ClickOnButtonSettings();
		// Verify button setting popup is displayed
		EditorPageObjects.VerifyButtonSettingsPopupDisplayed();
		// change buttton name	

		EditorPageObjects.ChangeButtonName(buttonName);
		// close button setting popup

		EditorPageObjects.CloseButtonSettingPopup();
		// check the button name is changed? 

		EditorPageObjects.CheckButtonName();		
	}
//
//	@Test
//	public void Verify_User_Can_Able_To_Save_The_Microsite() throws InterruptedException, java.util.concurrent.TimeoutException {
//		VerifyUserCanAbleToChangeTheButtonName();
//		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
//		// save the microsite
//		EditorPageObjects.ClickOnSaveMicrositeButton();
//		// check save as file option is visible and click on save as file option
//
//		EditorPageObjects.CheckTheOptionAndSaveMicrosite();
//	}

//	@Test
//	public void Verify_user_able_to_edit_existing_micorsite() throws InterruptedException {
//		
//		HomePage homepage = new HomePage(driver);
//		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
//		// login with valid credentials
//		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
//		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
//		homepage.ClickOnLoginInButton();
//		// scroll down to microsite list
//		ContentLibraryPage.ScrollDownToMicrositeList();
//		
//		
//		List<WebElement> actionButtons = driver
//				.findElements(By.xpath("//button[@class='btn btn-outline-primary dropdown-toggle']"));
//
//		if (!actionButtons.isEmpty()) {
//			// Hover over the first action button
//			Actions actions = new Actions(driver);
//			actions.moveToElement(actionButtons.get(0)).build().perform();
//			Thread.sleep(5000);
//			driver.findElement(
//					By.xpath("(//a[@class='dropdown-item mulitmedia_setting'][normalize-space()='Edit'])[1]")).click();
//			// verify Edit or Rename Multimedia Content popup displayed
//			Thread.sleep(5000);
//			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='multimediaedit']//div[@class='modal-header']"))
//					.isDisplayed());
//			// edit the multimedia
//			driver.findElement(By.xpath("//a[normalize-space()='Edit Multimedia']")).click();
//			boolean test = Verify_The_Editor_Page_URL();
//
//			Assert.assertEquals(test, true);
//			System.out.println("User is on Edit microsite page");
//		} else {
//			System.out.println("Microsite list is empty");
//		}
//	}

//	// page method
//	public boolean Verify_The_Editor_Page_URL() {
//		
//		String pageURL = driver.getCurrentUrl();
//		boolean bol = pageURL.contains(dataProp.getProperty("EditoURLCheckText"));
//		return true;
//	}

//	@Test // (dependsOnMethods = { "AddRedColorPallet" })
//	public void VerifyUserCanAbleToChangeTheButtonColor() throws InterruptedException {
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		VerifyUserCanAbleToAddTheButtonElementToEditorPage();
//		Actions act = new Actions(driver);
//		WebElement button = driver.findElement(By.xpath("//p[normalize-space()='Add Label']"));
//		act.moveToElement(button).perform();
//		driver.findElement(By.xpath("//span[@class='button text-setting']")).click();
//		// Verify button setting popup is displayed
//		Thread.sleep(2000);
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='header']")).isDisplayed(), true);
//		System.out.println("Button setting popup is Displayed");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//a[normalize-space()='Normal']")).click();
//		Thread.sleep(2000);
//		// open background color popup
//		driver.findElement(By.xpath(
//				"//div[contains(@class,'tab-pane normal-tab active')]//div[contains(@class,'modalContentWrapper')]//div//button[contains(@class,'color-picker-button')][normalize-space()='Background Color']"))
//				.click();
//		System.out.println("background color popup is open");
//		Thread.sleep(2000);
//
//		AddRedColorDetails();
//		Thread.sleep(2000);
//		System.out.println("Red color assigned");
//
//		// close the button setting popup
//
//		driver.findElement(By.xpath("//div[@class='header']//img")).click();
//		System.out.println("Popup is closed");
//
//	}
//
//	public void AddRedColorDetails() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-1']")).clear();
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-1']")).sendKeys("EE0E0E");
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-2']")).clear();
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-2']")).sendKeys("238");
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-3']")).clear();
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-3']")).sendKeys("14");
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-4']")).clear();
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-4']")).sendKeys("14");
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-5']")).clear();
//		driver.findElement(By.xpath("//input[@id='rc-editable-input-5']")).sendKeys("100");
//		Thread.sleep(1000);
//	}
//
//	@Test
//	public void AddRedColorPallet() throws InterruptedException {
//		Verify_user_able_to_edit_existing_micorsite();
//		driver.findElement(By.xpath("//div[@class='mylibrary layoutButton first-step']//img")).click();
//		driver.findElement(By.xpath("//a[normalize-space()='Color Palette']")).click();
//		driver.findElement(By.xpath("//button[normalize-space()='Add Color']")).click();
//		Thread.sleep(2000);
//		// add color pallet
//		System.out.println("user moving to add color pallet");
//		// add hexcode / RGB and A
//		AddRedColorDetails();
//		Thread.sleep(2000);
//		System.out.println("Red color details added");
//
//		// click on add to pallet
//
//		driver.findElement(By.xpath("//button[normalize-space()='Add to Palette']")).click();
//
//		System.out.println("Red Color Pallet is Displayed");
//		driver.findElement(By.xpath("//*[@id=\" \"]/div[2]/div[1]/img")).click();
//		System.out.println("Global Style Setting popup is closed");
//
//	}

//	@Test
//	public void Verify_User_Can_Able_To_Click_On_The_Preview_Button() throws InterruptedException {
//		VerifyUserCanAbleToAddTheButtonElementToEditorPage();
//		Thread.sleep(5000);
//		Actions act = new Actions(driver);
//		WebElement Preview_button = driver.findElement(By.xpath("//img[@class='preview']"));
//		act.moveToElement(Preview_button).perform();
//		Preview_button.click();
//		Thread.sleep(3000);
//		// Verify the preview is open in new tab
//		// Get a list of all tab handles
//		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//
//		// Switch to the new tab
//		driver.switchTo().window(tabs.get(1));
//		Thread.sleep(3000);
//		String currentUrl = driver.getCurrentUrl();
//		// confirm the preview URL
//		boolean currentUrlConfirmation = currentUrl.contains("https://php8-dev-player.flimp.live");
//		Assert.assertEquals(currentUrlConfirmation, true);
//		System.out.println("Preview is opened in new tab");
//	}
//
//	@Test
//	public void Verify_The_Button_Element_Is_Visible_In_The_Preview() throws InterruptedException {
//		VerifyUserCanAbleToAddTheButtonElementToEditorPage();
//		Thread.sleep(5000);
//		Actions act = new Actions(driver);
//		WebElement Preview_button = driver.findElement(By.xpath("//img[@class='preview']"));
//		act.moveToElement(Preview_button).perform();
//		Preview_button.click();
//		Thread.sleep(3000);
//		// Verify the preview is open in new tab
//		// Get a list of all tab handles
//		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//
//		// Switch to the new tab
//		driver.switchTo().window(tabs.get(1));
//		Thread.sleep(3000);
//		String currentUrl = driver.getCurrentUrl();
//		// confirm the preview URL
//		boolean currentUrlConfirmation = currentUrl.contains("https://php8-dev-player.flimp.live");
//		Assert.assertEquals(currentUrlConfirmation, true);
//		System.out.println("Preview is opened in new tab");
//		boolean bol = driver.findElement(By.xpath("//*[@class = 'btn button-class']")).isDisplayed();
//		Assert.assertEquals(bol, true);
//		System.out.println("Button is displayed in preview");
//	}

	@AfterTest
	public void tearDown() {
		 driver.quit();
	}
}
