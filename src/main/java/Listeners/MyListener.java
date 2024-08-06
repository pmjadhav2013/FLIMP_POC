package Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ExtendReportsTesting.ExtentReporter;
import Utils.Utilities;

public class MyListener implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	//String testName = result.getName();
	
	@Override
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExtentReport();	
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		//String testName = result.getName();
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//String testName = result.getName();
		extentTest.log(Status.PASS, result.getName() + " got successfully executed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// String testName = result.getName();
		// extentTest.log(Status.FAIL, testName + " got failed");
		// to call driver from the individual test use below instruction
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destinationScreenShotPath = Utilities.captureScreenshot(driver, result.getName()); 
		// attach screen shot to extend report
		
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got failed");
		//System.out.println(result.getThrowable()); // exception details will be printed in get throable
		//System.out.println(testName+ " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+ " got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// String testName = result.getName();
		// System.out.println("Finished Execution of Project Tests");
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
