package tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public abstract class BasicTest {
	protected WebDriver driver;
	protected String baseUrl = "http://demo.yo-meals.com";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"driver-lib\\chromedriver.exe");
		
		this.driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}
	
	@AfterMethod
	public void afterTest(ITestResult result) throws WebDriverException, IOException {
		driver.manage().deleteAllCookies();
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			  Date date = new Date();
			  
			  String fileName = (dateFormat.format(date)).toString();
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE),
				new File("screenshots/"+ fileName + ".png"));;
		} else {
			System.out.println("SUCCESS!");
		}
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.quit();
	}
	
}
