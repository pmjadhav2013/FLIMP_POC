package Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import Utils.Utilities;

public class BaseClass {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public BaseClass() { // constructor to initialize the properties file

		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\Config\\Config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		dataProp = new Properties();
		File datapropFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\TestData.properties");
		try {
			FileInputStream fis = new FileInputStream(datapropFile);
			dataProp.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browser_Name) {

		if (browser_Name.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		//	ChromeOptions options = new ChromeOptions();
			// Add headless argument
	      //  options.addArguments("--headless"); // Change this to false if you want the browser to be visible
		} else if (browser_Name.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser_Name.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser_Name.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();

		}
		// driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		System.out.println("\n Website is launched in :-> " + browser_Name + " browser");
		return driver;
	}
}
