package FLIMP.POC;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;

public class PreviewPage extends BaseClass {

	public PreviewPage()
	{
		super(); // constructor to load the properties files
	}

	public WebDriver driver;
	EditorPage test = new EditorPage();

	@BeforeMethod
	public void setup() 
	{
		//driver = initializeBrowserAndOpenApplicationURL("chrome");
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}

	//@Test
	public void Verify_Preview_Button_Is_Visible_On_The_Editorpage() throws InterruptedException
	{
		
		test.verifyUserCanOpenTheEditorPage();
		WebElement Preview_Button = driver.findElement(By.xpath("//img[@class='preview']"));
		Assert.assertEquals(true, Preview_Button.isDisplayed());
		System.out.println("Preview button is visible");
	}
		
//		//@Test
//		public void Verify_Preview_New_Added_Button_Is_Visible_in_the_Preview() throws InterruptedException
//		{
//			test.VerifyUserCanAbleToChangeTheButtonName();
//			
//			WebElement Preview_Button = driver.findElement(By.xpath("//img[@class='preview']"));
//			//Assert.assertEquals(true, Preview_Button.isDisplayed());
//			System.out.println("Preview button is visible");
//			Preview_Button.click();
//			Thread.sleep(2000);
//			// switch to new tab
//			ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//			driver.switchTo().window(tabs.get(1)); 
//			// check the button element is visible
//			WebElement element = driver.findElement(By.id("elementId")); // Replace with your element's identifier
//			if (element.isDisplayed()) {
//			    System.out.println("Element is displayed.");
//			} else {
//			    System.out.println("Element is not displayed.");
//			}	
//	}
	
	
	
	
	@AfterTest
	public void tearDown() 
	{
		driver.quit();
	}
}
