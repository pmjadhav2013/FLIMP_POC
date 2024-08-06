package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Utils.ConsoleOutputCapture;

public class LogListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ConsoleOutputCapture.reset();
        ConsoleOutputCapture.start();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String consoleOutput = ConsoleOutputCapture.stop();
        result.setAttribute("consoleOutput", consoleOutput);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String consoleOutput = ConsoleOutputCapture.stop();
        result.setAttribute("consoleOutput", consoleOutput);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String consoleOutput = ConsoleOutputCapture.stop();
        result.setAttribute("consoleOutput", consoleOutput);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String consoleOutput = ConsoleOutputCapture.stop();
        result.setAttribute("consoleOutput", consoleOutput);
    }

    @Override
    public void onStart(ITestContext context) {
        // Do nothing
    }

    @Override
    public void onFinish(ITestContext context) {
        // Do nothing
    }
}

