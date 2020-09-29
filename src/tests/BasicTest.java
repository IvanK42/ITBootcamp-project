package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class BasicTest {
	private WebDriver driver;
	protected String baseUrl = "demo.yo-meals.com";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"driver-lib\\chromedriver.exe");
		
		this.driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}
	
	@AfterMethod
	public void afterTest(ITestResult result) throws WebDriverException, IOException {
		driver.manage().deleteAllCookies();
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE),
				new File("/screenshot/"+ sdf + ".png"));;
		} else {
			System.out.println("SUCCESS!");
		}
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.quit();
	}
	
}
