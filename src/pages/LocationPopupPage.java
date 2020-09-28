package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {
	private WebDriver driver;
	private JavascriptExecutor js = (JavascriptExecutor) driver;

	
	
	public LocationPopupPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getHeaderLocation() {
		return this.driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[1]/div/a"));
	}
	
	
	public WebElement getClose() {
		return this.driver.findElement(By.xpath("//*[@id=\"location-popup\"]/div/div/div/div/a"));
	}
	
	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}
	
	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	
	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}
	
	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	
	public void openDialog() {
		getHeaderLocation().click();
	}
	
	public void setLocation(String locationName) {
		getKeyword().click();
		String locAttribute = getLocationItem(locationName).getAttribute("data-value");
		js.executeScript(getLocationInput()+".value"+ "=",locAttribute);
		js.executeScript("arguments[0].click()");
	}
	
	public void clickSubmit() {
		getClose().submit();
	}
	
}
