package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class Utilities {
	
	WebDriver driver;
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;
	
// this method can be make static or can be use the extends (inheritance concept also)
	public static String generateTimeStamp() 
	{
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
	
	public boolean waitforElementtoBeVisible(WebElement elementLocator)
	{
		// Create an instance of WebDriverWait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait until the element is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOf(elementLocator));
		Assert.assertTrue(element.isDisplayed());
        return true;
	}
	
	public void mytestcode()
	{
		
		// code to find element, move to it and click on it
	      //  driver.findElement(By.xpath("//a[@id='multimedia-tab']")).click();
        
//      System.out.println("clicked on multimedia tab");
//      WebElement CreateresponsiveMicrositeButton = driver.findElement(By.xpath("//button[@class='btn create_portal']"));
//		Actions act = new Actions(driver);
//		act.moveToElement(CreateresponsiveMicrositeButton);
//		Thread.sleep(3000);
//		act.click();
//		// 	act.doubleClick();
	}
	
	@DataProvider (name = "logincredentials")
	public Object[][] dataSupplier() throws IOException
	{
	File ExcelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\testData.xlsx");  
	
	FileInputStream fis = new FileInputStream(ExcelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("data");
            
            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            	                     
            Object[][] data = new Object[rowCount][colCount];
            
            for(int r=0;r<rowCount;r++)
            {
            	XSSFRow row = sheet.getRow(r+1);
            	
            	for(int c=0; c<colCount; c++)
            	{
            	 XSSFCell cell = row.getCell(c);
            	 CellType cellType = cell.getCellType();
            	 
            	 switch(cellType)
            	 {
            	 case STRING:
            		 data[r][c] = cell.getStringCellValue();
            		 break;
            	 case NUMERIC:
            		 data[r][c] = Integer.toString((int)cell.getNumericCellValue());
            		 break;
            		 
            	 case BOOLEAN:
            		 data[r][c] = cell.getBooleanCellValue();
            		 break;
            	 }
            	}
            }

        return data;
    }
	
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		File ExcelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\POC_TestData.xlsx");
		
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(ExcelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		XSSFSheet sheet =  workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for (int i=0;i<rows;i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			
			for (int j=0; j<cols ;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
//				if (cell != null) {
				switch(cellType)
				{
				case STRING:
					data[i][j] = cell.getStringCellValue();
					System.out.println(data);
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				
				case BOOLEAN:
					data[i][j] = cell.getNumericCellValue();
					break;	
				}} 
//				else 
//				{
//					data[i][j] = ""; // Handle null cells
//                }
			//}			
		}
		return data;		
	}
	
	public static String captureScreenshot(WebDriver driver, String testname)
	{
		System.out.println("Screenshot taken");
		File srcScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		String destinationScreenShotPath = System.getProperty("user.dir")+"\\ScreenShots\\"+testname + ".png";
		// for copying screen shot 
		try {
			FileHandler.copy(srcScreenShot, new File(destinationScreenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenShotPath;
	}
}
