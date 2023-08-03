package basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

	public static WebDriver driver;
	private FileInputStream fis = null;
	private Properties properties = null;

	ExtentSparkReporter extentSparkReporter;
	protected ExtentReports extentReports;
	protected ExtentTest extentTest;

	@BeforeSuite
	public void beforeSuite() throws IOException {

		fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
		properties = new Properties();
		properties.load(fis);

		if (extentReports == null) {
			extentSparkReporter = new ExtentSparkReporter(
					"C:\\Users\\Krishna Sakinala\\git\\repository\\SeleniumFrameworkJune2023Batch\\reports\\automationreport.html");
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentSparkReporter);

			extentReports.setSystemInfo("OS", "Windows");
			extentReports.setSystemInfo("Environemnt", "QA");
			extentReports.setSystemInfo("Tester", "Krishna");
			extentReports.setSystemInfo("Browser", "Chrome");

			extentSparkReporter.config().setDocumentTitle("Selenium Class");
			extentSparkReporter.config().setReportName("Automation Report");
			extentSparkReporter.config().setTheme(Theme.DARK);

		}
	}

	@BeforeMethod
	public void beforeMethod() {
		if (properties.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
		} else if (properties.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		// driver.get("https://practice.automationtesting.in");
		driver.get(properties.getProperty("url"));
	}

	public static String captureScreenshot(String screenshot) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "/screenshots/" + screenshot + ".png";
		File destination = new File(dest);
		FileHandler.copy(source, destination);
		return dest;
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail("Test Failed...");
			extentTest.fail(result.getThrowable());
			// screenshot
			String screenshot = captureScreenshot(result.getMethod().getMethodName());
			extentTest.fail("Find Failure Screenshot Below:",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass("Test Passed...");
		} else {
			extentTest.skip("Test Skipped...");
		}

		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extentReports.flush();
	}
}