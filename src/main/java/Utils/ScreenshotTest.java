package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjects.ContentLibraryPage;
import PageObjects.DistributionLinkPage;
import PageObjects.EditorPageObjects;
import PageObjects.HomePage;

public class ScreenshotTest extends BaseClass {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		// System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
		driver = new ChromeDriver();
	}

	@Test
	public void testExample() throws InterruptedException {
		HomePage homepage = new HomePage(driver);
		EditorPageObjects EditorPageObjects = new EditorPageObjects(driver);
		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);
		DistributionLinkPage DistributionLinkPage = new DistributionLinkPage(driver);

		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
		// homepage.ClickOnLoginInButton();
		Thread.sleep(2000);

		ContentLibraryPage.ClickOnCreateNewMicrosite();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			captureScreenshot(result.getName());
		}
		driver.quit();
	}

//    public void captureScreenshot(String testName) {
//        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
//        String filePath = "screenshots/" + testName + "_" + timestamp + ".png";
//        try {
//            FileUtils.copyFile(screenshot, new File(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

	public void captureScreenshot(String testName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String filePath = "screenshots/" + testName + "_" + timestamp + ".jpg";
		try {
			FileUtils.copyFile(source, new File(filePath));
			System.out.println("Screenshot taken successfully: " + filePath); // Debug statement
		} catch (IOException e) {
			System.err.println("Failed to capture screenshot: " + e.getMessage()); // Debug statement
			e.printStackTrace();
		}
	}

}
