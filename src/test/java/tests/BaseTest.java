package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.ConfigReader;
import utils.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportPath = Paths.get(System.getProperty("user.dir"), "test-output", "ExtentReport_" + timestamp + ".html").toString();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Salesforce Service Cloud Automation Report");
        htmlReporter.config().setReportName("Salesforce Service Cloud Tests");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        ExtentTest extentTest = test.get();

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getThrowable());
            String screenshotPath = takeScreenshot(result.getName());
            try {
                extentTest.addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test skipped: " + result.getThrowable());
        } else {
            extentTest.pass("Test passed");
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    protected String takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String dest = Paths.get(System.getProperty("user.dir"), "screenshots", testName + ".png").toString();

        try {
            FileUtils.copyFile(src, new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
