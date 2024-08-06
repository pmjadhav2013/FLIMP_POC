package FLIMP.POC;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjects.ContentLibraryPage;
import PageObjects.HomePage;

public class CreateMicrosite extends BaseClass {

	public CreateMicrosite() {
		super(); // constructor to load the properties files
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}

	//@Test
	public void verifyUserCanAbleToCreateMicrosite() throws InterruptedException {

		HomePage homepage = new HomePage(driver);
		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);

		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
		homepage.ClickOnLoginInButton();

		ContentLibraryPage.ClickOnCreateNewMicrosite();
		// microsite title enter
		ContentLibraryPage.EnterMicrositeTitle();
		
		//microsite description enter
		ContentLibraryPage.EnterBriefDescription();

		// click on saveforlater button
		ContentLibraryPage.ClickSaveForLaterButton();
		// driver.findElement(By.xpath("//button[@id='save_for_later']")).click();
		Thread.sleep(5000);
		System.out.println("\n New Microsite is saved");
	}

	 @Test
	public void verifyNewMicrositeIsVisibleInContentLibraryPage() throws InterruptedException {

		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);

		verifyUserCanAbleToCreateMicrosite();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");

		System.out.println("\n Control Navigated to the Microsite List");

		// get the list of the microsite names
		ContentLibraryPage.GetTheMicrositeList();
	}

//	@Test // (dependsOnMethods = { "verifyUserCanAbleToCreateMicrosite" })
//	public void verifyMaxNumberOfMicrositeValidation() throws InterruptedException {
//
//		HomePage homepage = new HomePage(driver);
//		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
//
//		homepage.EnterUserName(prop.getProperty("MaxNumberOfMicrositeValidation_userName"));
//		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
//		homepage.ClickOnLoginInButton();
//		// click on create responsive content button
//		ContentLibraryPage.ClickOnCreateNewMicrosite();
//		// driver.findElement(By.xpath("//*[@class =
//		// 'dashboadtopbar__mainbtn']//button[1]")).click();
//		// go to aleter popup
//		// WebElement alertTextElement =
//		// driver.findElement(By.xpath("//div[@class='modal-body']//h6"));
//		// wait for altertext popup visible
//
////		Utilities utilities = new Utilities();
////		utilities.waitforElementtoBeVisible(alertTextElement);
////		
//		ContentLibraryPage.WaitForMaxNumberSiteAlertPopupVisibility();
//
//		// Get the text from the element
//		String alertText = ContentLibraryPage.MaxSiteAlertText.getText();
//		String maxNumbTextMessage = dataProp.getProperty("maxNumbTextMessage");
//		boolean confirmation = alertText.contains(maxNumbTextMessage);
//		System.out.println("Alert text message is: " + alertText);
//		Assert.assertEquals(confirmation, true);
//
//		System.out.println(alertText + "is displayed");
//	}
//
//	@Test // (dependsOnMethods = { "verifyUserCanAbleToCreateMicrosite" })
//	public void verifyUserCanAcceptMaxNumberOfMicrositeAlert() throws InterruptedException {
//
//		HomePage homepage = new HomePage(driver);
//		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
//
//		homepage.EnterUserName(prop.getProperty("MaxNumberOfMicrositeValidation_userName"));
//		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
//		homepage.ClickOnLoginInButton();
//		ContentLibraryPage.ClickOnCreateNewMicrosite();
//		ContentLibraryPage.WaitForMaxNumberSiteAlertPopupVisibility();
//		ContentLibraryPage.MaxNumberSiteAlertPopupAccept();
//		System.out.println("Accepted the alert popup ");
//	}
//
//	@Test // (dependsOnMethods = { "verifyUserCanAbleToCreateMicrosite" })
//	public void verifyUserCanCloseMaxNumberOfMicrositeAlert() throws InterruptedException {
//
//		HomePage homepage = new HomePage(driver);
//		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
//
//		homepage.EnterUserName(prop.getProperty("MaxNumberOfMicrositeValidation_userName"));
//		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
//		homepage.ClickOnLoginInButton();
//		ContentLibraryPage.ClickOnCreateNewMicrosite();
//		ContentLibraryPage.WaitForMaxNumberSiteAlertPopupVisibility();
//		ContentLibraryPage.MaxNumberSiteAlertClose();
//		System.out.println("Closed the alert popup ");
//	}
//
//	@Test // (dependsOnMethods = { "verifyUserCanAbleToCreateMicrosite" })
//	// this test is valid only when the microsite is exist in the content library
//	public void verifyUserCanAbleToDeleteMicrosite() throws InterruptedException {
//
//		HomePage homepage = new HomePage(driver);
//		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
//
//		homepage.EnterUserName(prop.getProperty("MaxNumberOfMicrositeValidation_userName"));
//		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
//		homepage.ClickOnLoginInButton();
//
//		// driver.navigate().refresh(); // refresh the page
//		// Thread.sleep(5000);
//		// JavascriptExecutor js = (JavascriptExecutor) driver;
//		// js.executeScript("window.scrollBy(0,350)", "");
//
//		ContentLibraryPage.ScrollDownToMicrositeList();
//		// calling the action buttons list from content library page
//
//		List<WebElement> actionButtons = ContentLibraryPage.GetMicorositeActionButtonsList();
//
//		if (!actionButtons.isEmpty()) {
////			// Hover over the first action button
////			Actions actions = new Actions(driver);
////			actions.moveToElement(actionButtons.get(0)).build().perform();
//
//			ContentLibraryPage.HoverOnFirstMicrosite(actionButtons);
//
//			Thread.sleep(5000);
//			// click on action button of first microsite
//			// driver.findElement(By.xpath("//a[@class= 'dropdown-item
//			// delete_content_form']")).click();
//			ContentLibraryPage.ClickOnActionButton();
//			Thread.sleep(5000);
//			ContentLibraryPage.DeleteMicrositePopupDisplayed();
//
////			Assert.assertTrue(
////					driver.findElement(By.xpath("//div[@id='delete_content_form']//div[@class='modal-header']"))
////							.isDisplayed());
////			System.out.println("Delete microsite popup displayed");
//			// delete microsite button click
//			// driver.findElement(By.xpath("//button[@id='delete_content']")).click();
//			ContentLibraryPage.DeleteMicrositeAndConfirm();
//
////			WebElement delete_confirmation_message = driver.findElement(By.xpath("//*[@class='bootbox-body']//h6"));
////			boolean delete_confirmation_message_displayed = delete_confirmation_message.isDisplayed();
////			Assert.assertEquals(delete_confirmation_message_displayed, true);
////			System.out.println(delete_confirmation_message);
//
//		} else {
//			System.out.println("No action buttons / multimedia found!");
//		}
//	}

//	@Test // (dependsOnMethods = { "verifyUserCanAbleToCreateMicrosite" })
//	// this test is valid only when the microsite is exist in the content library
//	public void Microsite_visibility_in_content_library() throws InterruptedException {
//
//		HomePage homepage = new HomePage(driver);
//		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
//		driver.navigate().refresh(); // refresh the page
//		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
//		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
//		homepage.ClickOnLoginInButton();
//
//		Thread.sleep(2000);
//		ContentLibraryPage.ScrollDownToMicrositeList();
//		Thread.sleep(2000);
//		// check microsite list is visible
//
////		List<WebElement> actionButtons = driver
////				.findElements(By.xpath("//button[@class='btn btn-outline-primary dropdown-toggle']"));	
//
//		// List<WebElement> actionButtons =
//		// ContentLibraryPage.GetMicorositeActionButtonsList();
//		System.out.println("list is displayed");
//		if (!ContentLibraryPage.GetMicorositeActionButtonsList().isEmpty()) {
//
//			System.out.println("list is not Empty");
////			 Hover over the first action button
////			Actions actions = new Actions(driver);
////			actions.moveToElement(actionButtons.get(0)).build().perform();
////			Thread.sleep(5000);
////			//
////			driver.findElement(By.xpath("//a[@class= 'dropdown-item delete_content_form']")).click();
////			Thread.sleep(5000);
////			Assert.assertTrue(
////					driver.findElement(By.xpath("//div[@id='delete_content_form']//div[@class='modal-header']"))
////							.isDisplayed());
//			Assert.assertTrue(ContentLibraryPage.OpenActionsDropdownAndCheckElementPresent());
//
//			System.out.println("Microsite is visible in content library ");
//		} else {
//			System.out.println("Microsite list is empty");
//		}
//	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
