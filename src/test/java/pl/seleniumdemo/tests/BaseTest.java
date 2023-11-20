package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.seleniumdemo.utils.DriverFactory;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentHtmlReporter htmlreporter;
    protected static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuite() {
        htmlreporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlreporter);
    }

    @AfterSuite
    public void afterSuite() {
        htmlreporter.flush();
        extentReports.flush();
    }


    @BeforeMethod
    public void setup() throws IOException {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
