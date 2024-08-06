package FLIMP.POC;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjects.ContentLibraryPage;
import PageObjects.DistributionLinkPage;
import PageObjects.EditorPageObjects;
import PageObjects.HomePage;

public class EndToEndTest extends BaseClass {

	public EndToEndTest() {
		super(); // constructor to load the properties files
	}

	public WebDriver driver;
	
	@BeforeMethod
	public void setup(ITestContext context) {
		// driver = initializeBrowserAndOpenApplicationURL("chrome");
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		context.setAttribute("driver", driver); // Store WebDriver instance in TestNG context
	}

	@Test
	public void FlimpEndToEndTest() throws InterruptedException, UnsupportedFlavorException, IOException {

		HomePage homepage = new HomePage(driver);
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
		DistributionLinkPage DistributionLinkPage = new DistributionLinkPage(driver);

		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
		homepage.ClickOnLoginInButton();
		Thread.sleep(2000);

		ContentLibraryPage.ClickOnCreateNewMicrosite();
		Thread.sleep(2000);
		// microsite title enter
		ContentLibraryPage.EnterMicrositeTitle();
		Thread.sleep(2000);

		// microsite description enter
		ContentLibraryPage.EnterBriefDescription();
		Thread.sleep(2000);

		// click on saveforlater button
		ContentLibraryPage.ClickSaveForLaterButton();

		// driver.findElement(By.xpath("//button[@id='save_for_later']")).click();
		Thread.sleep(5000);
		System.out.println("\n New Microsite is saved");
		ContentLibraryPage.ScrollDownToMicrositeList();
//		// click on the create new microsite button
//		ContentLibraryPage.ClickOnCreateNewMicrosite();
//		Thread.sleep(2000);
//		ContentLibraryPage.EnterMicrositeTitle();
//		Thread.sleep(2000);
//		// enter microsite description
//		ContentLibraryPage.EnterBriefDescription();
//		Thread.sleep(2000);
		// click on create new microsite button
		ContentLibraryPage.EditTheLatestCreatedMicrosite();
		//ContentLibraryPage.ClickOnEditNowButton();
		Thread.sleep(2000);
		EditorPageObjects.WaitForEditorPageLoad();
		Thread.sleep(2000);
		EditorPageObjects.CheckEditorPageURL();
		Thread.sleep(2000);

		EditorPageObjects.ClickOnAddButton();
		Thread.sleep(2000);
		// check the add eleemnts popup is displayed

		EditorPageObjects.CheckAddElementPopupDisplayed();
		Thread.sleep(2000);
		// select the button element

		EditorPageObjects.AddButtonElementAndVerify();
		Thread.sleep(2000);

		String buttonName = "Preview Test_";
		EditorPageObjects.Move_HoverToButton();
		Thread.sleep(2000);
		EditorPageObjects.ClickOnButtonSettings();
		Thread.sleep(2000);
		EditorPageObjects.ChangeButtonName(buttonName);
		Thread.sleep(2000);
		EditorPageObjects.CheckPreviewButtonIsDisplayed();
		Thread.sleep(2000);
		EditorPageObjects.ClickOnPreviewButton();

		Thread.sleep(3000);

		EditorPageObjects.SwithToNewTab();
		Thread.sleep(2000);
		// check the button element is visible

		String xpath = "//div[@class='text-center']//div[contains(@id, 'element') and contains(@class, 'btn') and contains(text(), '"
				+ buttonName + "')]";

		WebElement element = driver.findElement(By.xpath(xpath)); // Replace with your element's identifier
		if (element.isDisplayed()) {
			System.out.println("\n Button Element is displayed.");
		} else {
			System.out.println("\n Button Element is not displayed.");
		}

//			EditorPageObjects.SwithToContentLibraryTab();
		Thread.sleep(2000);

		// open conten library page
		ContentLibraryPage.OpenContentLibraryPage();
		Thread.sleep(2000);
		ContentLibraryPage.HoverToDistributeMenu();
		Thread.sleep(2000);
		ContentLibraryPage.SelectCreateUniqueURLOption();
		Thread.sleep(2000);
		ContentLibraryPage.CheckDistributionPageURL();
		Thread.sleep(2000);
		ContentLibraryPage.CheckContentLibraryPopupIsVisible();
		Thread.sleep(2000);
		DistributionLinkPage.SelectTheFirstMicrosite();
		Thread.sleep(2000);
		DistributionLinkPage.EnteTheMicrositeText();
		Thread.sleep(2000);
		DistributionLinkPage.OpenURLinNewtab();
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
