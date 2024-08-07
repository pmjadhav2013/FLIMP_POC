package FLIMP.POC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseClass;
import PageObjects.ContentLibraryPage;
import PageObjects.HomePage;
import Utils.Utilities;

public class Login extends BaseClass {

	public Login() {
		super(); // constructor to load the properties files
	}

	public WebDriver driver;
	// HomePage homePage = new HomePage(driver);

	@BeforeMethod
	public void setup() {
// to mention the browser selection
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

	}
// this is testing project
	// @Test(priority = 1, dataProvider = "validDataSupplier")

	@Test(priority = 1)
	// public void VerifyLoginWithValidCredentials(String username, String password)
	// {
	public void VerifyLoginWithValidCredentials() {

		HomePage homePage = new HomePage(driver);
		// System.out.println("Testing with: " + prop.getProperty("validUserName") + " /
		// " + prop.getProperty("validPassword")); // Debug output

		homePage.EnterUserName(prop.getProperty("validUserName"));
		System.out.println("\n Username is entered : " + prop.getProperty("validUserName"));
		homePage.EnterPassword(prop.getProperty("validPassword"));
		System.out.println("\n Password is entered : " + prop.getProperty("validPassword"));
		homePage.ClickOnLoginInButton();
		System.out.println("\n Clicked on the Login Button");

		Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Content Library']")).isDisplayed());
		System.out.println("\n Logged in to content library page");
	}

	// conversion to data driven testing
	@DataProvider(name = "validDataSupplier") // we can give name of the data provider too in the
	public Object[][] supplyTestData() {
		Object[][] data = { { "anyone", "japh7vej" }, { "2014web", "japh7vej" } };
		return data;
	}

	@DataProvider(name = "InvalidDataSupplier") // we can give name of the data provider too in the
	public Object[][] supplyInvalidTestData() {
		Object[][] data = { { "anyone12345", "japh7vej123" }}; //, { "2014web123", "japh7vej123" } };
		return data;
	}

	@DataProvider(name = "POCDataSupplier") // we can give name of the data provider too in the
	public Object[][] supplyPOCTestData() {
		Object[][] data = { { "POC", "Test@12345" } };
		return data;
	}

//	@Test(dataProvider = "logincredentials")
//	public void testing(String username, String password)
//	{
//		System.out.println(username + "and " + password);
//	}

//	// provide data using excel
//	
//	@DataProvider (name = "logincredentials")
//	public Object[][] dataSupplier() throws IOException {
//	File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\testData.xlsx");
//    FileInputStream fis = new FileInputStream(excelFile);
//    XSSFWorkbook workbook = new XSSFWorkbook(fis);
//    XSSFSheet sheet = workbook.getSheet("data");
//
//    int rowCount = sheet.getLastRowNum();
//    int colCount = sheet.getRow(0).getLastCellNum();
//
//    Object[][] data = new Object[rowCount][colCount];
//
//    for (int r = 0; r < rowCount; r++) {
//        XSSFRow row = sheet.getRow(r + 1); // Start from the second row (index 1)
//
//        for (int c = 0; c < colCount; c++) {
//            XSSFCell cell = row.getCell(c);
//            if (cell == null) {
//                data[r][c] = ""; // Handle null cells
//                continue;
//            }
//
//            CellType cellType = cell.getCellType();
//
//            switch (cellType) {
//                case STRING:
//                    data[r][c] = cell.getStringCellValue();
//                    break;
//                case NUMERIC:
//                    if (DateUtil.isCellDateFormatted(cell)) {
//                        data[r][c] = cell.getDateCellValue().toString(); // Format date as needed
//                    } else {
//                        data[r][c] = Double.toString(cell.getNumericCellValue());
//                    }
//                    break;
//                case BOOLEAN:
//                    data[r][c] = cell.getBooleanCellValue();
//                    break;
//                case FORMULA:
//                    data[r][c] = cell.getCellFormula(); // Evaluate formula if needed
//                    break;
//                case BLANK:
//                    data[r][c] = "";
//                    break;
//                default:
//                    data[r][c] = "";
//            }
//        }
//    }

//    // Close the resources
//    workbook.close();
//    fis.close();
//
//    return data;
//    }
//	

	@Test(priority = 2, dataProvider = "InvalidDataSupplier")
	public void VerifyLoginWithInValidCredentials(String username, String password) // append with time stamp to create
																					// new username everytime
	{
		HomePage homepage = new HomePage(driver);
		homepage.EnterUserName(username + Utilities.generateTimeStamp());
		System.out.println("\n Username is entered : " + username);
		homepage.EnterPassword(password + Utilities.generateTimeStamp());
		System.out.println("\n Password is entered : " + password);
		homepage.ClickOnLoginInButton();
		String errorMessage = homepage.ErrorMessageText();
		System.out.println("\n\n Execution with the Invalid Credentials");
		System.out.println("\n * " + errorMessage + " * " + " - error message is displayed");
	}

	@Test(priority = 3)
	public void VerifyLoginWithEmptyUsername() {
		// driver.findElement(By.id("login_id")).sendKeys(" ");
		HomePage homepage = new HomePage(driver);
		// driver.findElement(By.xpath("//button[@type='submit']")).click();
		homepage.ClickOnLoginInButton();
		WebElement validationMessage = driver.findElement(By.cssSelector("input:invalid"));

		// Get the text of the validation message
		String messageText = validationMessage.getAttribute("validationMessage");

		// check the popup is displayed / warning is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("input:invalid")).isDisplayed());
		System.out.println(messageText);
	}
//
	@Test(priority = 3)
	public void VerifyLoginWithEmptyPassword() {
		// driver.findElement(By.id("login_id")).sendKeys(" ");
		// driver.findElement(By.id("password")).sendKeys("japh7vej");

		HomePage homepage = new HomePage(driver);
		homepage.ClickOnLoginInButton();

		// driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebElement validationMessage = driver.findElement(By.cssSelector("input:invalid"));

		// Get the text of the validation message
		String messageText = validationMessage.getAttribute("validationMessage");
		System.out.println(messageText);
		// check the popup is displayed / warning is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("input:invalid")).isDisplayed());
	}

	@Test(priority = 3)
	public void VerifyUserCanAbleToLogout() throws InterruptedException {

		VerifyLoginWithValidCredentials();
		System.out.println("\n On content library page");

		ContentLibraryPage ContentLibraryPage = new ContentLibraryPage(driver);

		ContentLibraryPage.MoveToLogoutmenu();

		Thread.sleep(3000);

		ContentLibraryPage.ClickOnLogoutButton();

		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains(prop.getProperty("url")));
		// Assert.fail();

		Thread.sleep(3000);
		System.out.println("\n User logged out successfully \n\n");
	}

	// @AfterTest
	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test
	public void loginWithPOCUser() {
		HomePage homepage = new HomePage(driver);
		homepage.EnterUserName(prop.getProperty("validUserName_POC"));
		homepage.EnterPassword(prop.getProperty("validPassword_POC"));
		homepage.ClickOnLoginInButton();
	
		System.out.println("\n POC User Logged in Successfully");
	}

}
