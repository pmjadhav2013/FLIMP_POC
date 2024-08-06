package PageObjects;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utils.Utilities;

public class DistributionLinkPage extends Base.BaseClass {

	WebDriver driver;

	@FindBy(xpath = "//div[@id='content_list']//div[2]")
	// *[@id='content_list']//div[2]
	private WebElement FirstMicrosite;

	// button[@id='select_content']
	@FindBy(xpath = "//button[@id='select_content']")
	private WebElement Use_selected_content_button;

	// div[@class='custom-control custom-radio']//label[@for='b']
	@FindBy(xpath = "//input[@id ='b']")
	private WebElement CustomControl_Radio_button;

	@FindBy(xpath = "//input[@id='customurl']")
	private WebElement SiteNameTextbox;

	// button[@id='generate_url']
	@FindBy(xpath = "//button[@id='generate_url']")
	private WebElement GenerateURLButton;

	// h3[normalize-space()='Chosen link']
	@FindBy(xpath = "//h3[normalize-space()='Chosen link']")
	private WebElement ChosenLink;

	
	@FindBy(xpath = "//div[@class='modal-header']")
	private WebElement AlertMessage;
	
	@FindBy(xpath = "//button[@id='click_to_copy']")
	private WebElement ClickToCopyButton;
	
	public void EnteTheMicrositeText() throws InterruptedException, UnsupportedFlavorException, IOException {
		// Enter text for custome microsite name

		Thread.sleep(2000);
		Actions act = new Actions(driver);
		// act.scrollToElement(CustomControl_Radio_button).click().perform();
		act.moveToElement(CustomControl_Radio_button).click().perform();				
		System.out.println("\n Selected the option of create custom link");
				
		// Enter the microsite name text
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		SiteNameTextbox.click();
		SiteNameTextbox.sendKeys(prop.getProperty("DistributionSiteName") + Utilities.generateTimeStamp());
		System.out.println("\n Entered the site name to generate the custom link");

		// Click on the generate URL button
		GenerateURLButton.click();
		System.out.println("\n Generate URl button clicked");

		// Click on the generate URL button
		Thread.sleep(1000);
		ChosenLink.isDisplayed();
		System.out.println("\n ChosenLink is displayed");
		
		// Click to copy
		ClickToCopyButton.click();
		System.out.println("\n URL copied");
		
		// Paste the URL
		// Retrieve the text from the clipboard
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String copiedText = (String) clipboard.getData(DataFlavor.stringFlavor);

        // Print the copied text to the console
        System.out.println("\n Generated URl is : \n" + "\t" + copiedText);
		
	}

	public void OpenURLinNewtab() throws InterruptedException, UnsupportedFlavorException, IOException {
		// Open newly created URL in new tab
		// Retrieve the URL from the clipboard
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String copiedUrl = (String) clipboard.getData(DataFlavor.stringFlavor);

        // Print the copied URL to the console
        System.out.println("\n Copied URL from clipboard: \n " + "\t" + copiedUrl);

        // Open a new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Navigate to the copied URL in the new tab
        driver.get(copiedUrl);
        
        System.out.println("\n New url is opened");
        Thread.sleep(10000);
	}
	public DistributionLinkPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		// to avoid the steal element exception - calling using special method below
		PageFactory.initElements(driver, this);
	}

	public void SelectTheFirstMicrosite() throws InterruptedException {
		// get the list of the microsite names

		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(FirstMicrosite).click().perform();
		// FirstMicrosite.click();
		System.out.println("\n Selected the first microsite");
		act.moveToElement(Use_selected_content_button).click().perform();
		// Use_selected_content_button.click();
		System.out.println("\n Clicked on use selected button");
	}
}
