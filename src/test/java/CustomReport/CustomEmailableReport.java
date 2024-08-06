package CustomReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class CustomEmailableReport implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("<html><head><title>TestNG Report</title></head><body>");

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                buildTestContextReport(reportContent, testContext);
            }
        }

        reportContent.append("</body></html>");
        
        try {
            Files.write(Paths.get(outputDirectory, "custom-emailable-report.html"), reportContent.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildTestContextReport(StringBuilder reportContent, ITestContext testContext) {
        reportContent.append("<h1>").append(testContext.getName()).append("</h1>");

        reportContent.append("<h2>Passed Tests</h2>");
        for (ITestResult result : testContext.getPassedTests().getAllResults()) {
            buildTestResultReport(reportContent, result);
        }

        reportContent.append("<h2>Failed Tests</h2>");
        for (ITestResult result : testContext.getFailedTests().getAllResults()) {
            buildTestResultReport(reportContent, result);
        }

        reportContent.append("<h2>Skipped Tests</h2>");
        for (ITestResult result : testContext.getSkippedTests().getAllResults()) {
            buildTestResultReport(reportContent, result);
        }
    }

    private void buildTestResultReport(StringBuilder reportContent, ITestResult result) {
        reportContent.append("<h3>").append(result.getMethod().getMethodName()).append("</h3>");
        reportContent.append("<p>Test Class: ").append(result.getTestClass().getName()).append("</p>");
        String consoleOutput = (String) result.getAttribute("consoleOutput");
        if (consoleOutput != null) {
            reportContent.append("<pre>").append(consoleOutput).append("</pre>");
        }
    }
}

