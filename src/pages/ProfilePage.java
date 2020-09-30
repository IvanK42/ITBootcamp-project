package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {
    private JavascriptExecutor js = (JavascriptExecutor) driver;

	public ProfilePage(WebDriver driver) {
		super(driver);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[1]/div[1]/fieldset/input"));
	}
	
	public WebElement getLastName() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[1]/div[2]/fieldset/input"));
	}
	
	public WebElement getAddress() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[2]/div[2]/fieldset/input"));
	}
	
	public WebElement getPhoneNo() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[3]/div[1]/fieldset/input"));
	}
	
	public WebElement getZipCode() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[3]/div[2]/fieldset/input"));
	}
	
	public WebElement getCountry() {
		return this.driver.findElement(By.xpath("//*[@id=\"user_country_id\"]"));
	}
	
	public WebElement getState() {
		return this.driver.findElement(By.xpath("//*[@id=\"user_state_id\"]"));
	}
	
	public WebElement getCity() {
		return this.driver.findElement(By.xpath("//*[@id=\"user_city\"]"));
	}
	
	public WebElement getSave() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfoFrm\"]/div[5]/div/fieldset/input"));
	}
	
	public void uploadClick() {
		WebElement uploadBtn = this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a"));
		js.executeScript("arguments[0].click()", uploadBtn);
		
	}
	public void uploadImg(String image) {
		WebElement img = this.driver.findElement(By.xpath("//*[@id=\"form-upload\"]/input"));
		img.sendKeys(image);
	}
	
	public void removeImg() {
		WebElement removeBtn = this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a[2]"));
		js.executeScript("arguments[0].click()", removeBtn);
	}
	
	public void updateLoginInfo(String fname, String lname, String address, String phone, String zip, String country, String state, String city) throws InterruptedException {
		getFirstName().clear();
		getFirstName().sendKeys(fname);

		getLastName().clear();
		getLastName().sendKeys(lname);
		
		getAddress().clear();
		getAddress().sendKeys(address);

		getPhoneNo().clear();
		getPhoneNo().sendKeys(phone);
		
		getZipCode().clear();
		getZipCode().sendKeys(zip);
		
		Select selectCountry = new Select(getCountry());
		selectCountry.selectByVisibleText(country);
		Thread.sleep(2000);

		Select selectState = new Select(getState());
		selectState.selectByVisibleText(state);	
		Thread.sleep(2000);
		
		Select selectCity = new Select(getCity());
		selectCity.selectByVisibleText(city);
		
		js.executeScript("arguments[0].click()", getSave());

	}
	
}
