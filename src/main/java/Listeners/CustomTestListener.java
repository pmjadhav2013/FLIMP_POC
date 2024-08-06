package Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    private WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        // Initialize the driver if it's not already done
//        if (driver == null) {
//           // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//            driver = new ChromeDriver();
//            
//        }

        // Capture screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
        String testName = result.getName();
        String filePath = "src/main/java/ScreenShots/" + testName + "_" + timestamp + ".jpg";
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            String screenshotPath = "<img src='file:///" + new File(filePath).getAbsolutePath() + "' height='100' width='100'/>";
            result.setAttribute("screenshotPath", screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Add the screenshots to the emailable report
        for (ITestResult result : context.getFailedTests().getAllResults()) {
            String screenshotPath = (String) result.getAttribute("screenshotPath");
            if (screenshotPath != null) {
                // Modify the report to include the screenshot
                System.out.println("Screenshot path: " + screenshotPath);
            }
        }
    }

    
}
