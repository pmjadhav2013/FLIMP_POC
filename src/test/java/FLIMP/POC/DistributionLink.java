package FLIMP.POC;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
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
import PageObjects.DistributionLinkPage;
import PageObjects.EditorPageObjects;
import PageObjects.HomePage;
import Utils.Utilities;

public class DistributionLink extends BaseClass {

	private static final String String = null;

	public DistributionLink() {
		super(); // constructor to load the properties files
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		// driver = initializeBrowserAndOpenApplicationURL("chrome");
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}

	@Test
	public void VerifyUserCanCeateAndOpenDistributionLink() throws InterruptedException, UnsupportedFlavorException, IOException, java.util.concurrent.TimeoutException {
		HomePage homepage = new HomePage(driver);
		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
		DistributionLinkPage DistributionLinkPage = new DistributionLinkPage(driver);
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
		
		EditorPage editorObject = new EditorPage();
		
		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
		homepage.ClickOnLoginInButton();	
		
		ContentLibraryPage.ClickOnCreateNewMicrosite();
		ContentLibraryPage.EnterMicrositeTitle();
		ContentLibraryPage.EnterBriefDescription();
		ContentLibraryPage.ClickOnEditNowButton();
		
		EditorPageObjects.ClickOnAddButton();
		EditorPageObjects.AddButtonElementAndVerify();
		EditorPageObjects.Move_HoverToButton();
		EditorPageObjects.ClickOnButtonSettings();	
		EditorPageObjects.ChangeButtonName("Distribution Link Test_");
		EditorPageObjects.ClickOnSaveMicrositeButton();
		EditorPageObjects.CheckTheOptionAndSaveMicrosite();
		
		
		ContentLibraryPage.OpenContentLibraryPage();
		
		ContentLibraryPage.HoverToDistributeMenu();
		ContentLibraryPage.SelectCreateUniqueURLOption();
		ContentLibraryPage.CheckDistributionPageURL();
		ContentLibraryPage.CheckContentLibraryPopupIsVisible();
		DistributionLinkPage.SelectTheFirstMicrosite();
		DistributionLinkPage.EnteTheMicrositeText();
		DistributionLinkPage.OpenURLinNewtab();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
