package Base;

import IRetryAnalyzer.RetryFailedTestCases;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    protected static WebDriver driver;
    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriverWait wait;
    public static int maxCountRetry;
    public static int maxRetryCount;
    public static String BASE_URL = System.getProperty("BASE_URL");
    public static String BROWSER = System.getProperty("BROWSER");
    public static String PLATFORM_NAME = System.getProperty("PLATFORM_NAME");
    protected String URL = "https://www.saucedemo.com/";

    @BeforeSuite(alwaysRun = true)
    @Parameters("IRetry")
    public void setupSuite(ITestContext context, int IRetry) {
        this.maxCountRetry = IRetry;
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzer(new RetryFailedTestCases());
        }
    }
    @BeforeSuite
    public void setExtent(){
        Date d = new Date();
        String extentName = d.toString().replace(":", "_").replace(" ", "_");
//        reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\Report_"+extentName+".html");
        reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html");
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("Regression Report");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("hostName", "Localhost");
        extent.setSystemInfo("OS", "Windows10");
        extent.setSystemInfo("TesterName", "Vangjush");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @BeforeClass
    @Parameters("browser")
    public void launchBrowser(String browser) throws MalformedURLException {

        if(BASE_URL == null){
            if(browser.equalsIgnoreCase("chrome")){
                WebDriverManager.getInstance(CHROME).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless","enable-automation", "--silent", "--ignore-certificate-errors", "--disable-dev-shm-usage", "--disable-gpu");
                this.driver = new ChromeDriver(options);
            }
            else if (browser.equalsIgnoreCase("firefox")){
                WebDriverManager.getInstance(FIREFOX).setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless", "enable-automation", "--silent", "--ignore-certificate-errors", "--disable-dev-shm-usage", "--disable-gpu");
                this.driver = new FirefoxDriver(options);
            }
        }else {
            ChromeOptions options = new ChromeOptions();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (BROWSER.equalsIgnoreCase("chrome")){
                capabilities.setCapability("browser", BROWSER);
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("window-size=1920,1080");
                options.addArguments("--allow-insecure-localhost");
                capabilities = DesiredCapabilities.chrome();
            } else if (BROWSER.equalsIgnoreCase("firefox")) {
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("browser", BROWSER);
                capabilities.setCapability("marionette", true);
            }

            capabilities.setCapability("platformName", PLATFORM_NAME);
            options.merge(capabilities);
            driver = new RemoteWebDriver(new URL(BASE_URL+":4444/wd/hub"), options);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(URL);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, "TEST CASE FAILED IS: "+result.getName()); // add name in extent report
            test.log(Status.FAIL, "TEST CASE FAILED IS: "+result.getThrowable()); // add error message in the report

            String screenshotPath = BaseTest.getScreenshot(result.getName());

            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, "TEST CASE SKIPPED IS: "+result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, "TEST CASE PASSED IS: "+result.getName());
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.close();
    }

    // Remove from comment if you want to send the reports to email
//    @AfterSuite
//    public void sendEmail(){
//        SendMailSSLWithAttachment mail = new SendMailSSLWithAttachment();
//        mail.sendMail();
//    }

    @AfterTest
    public void endReport(){
        extent.flush();
    }

    public static String getScreenshot(String screenshotName) throws IOException {

        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File scrFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\screenshots\\" + screenshotName + dateName + ".png";
        FileUtils.copyFile(scrFile, new File(destination));
        return destination;
    }
}
