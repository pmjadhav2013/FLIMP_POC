package FLIMP.POC;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = "./screenshots/" + result.getName() + ".png";
            File destination = new File(dest);

            try {
                FileUtils.copyFile(source, destination);
                Reporter.log("Failed screenshot: <a href='" + destination.getAbsolutePath() + "'>Screenshot</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("WebDriver instance is null. Unable to capture screenshot.");
        }
    }

    // Other overridden methods from ITestListener (not used in this example)
}
