package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasicPage {

	private WebDriver driver;
	
	
	public LoginPage(WebDriver driver) {
			this.driver = driver;
	}
	
	public WebElement getEmail() {
			return this.driver.findElement(By.xpath("//*[@id=\"frm_fat_id_frmLogin\"]/fieldset[1]/input"));
	}
	
	public WebElement getPassword() {
		return this.driver.findElement(By.xpath("//*[@id=\"frm_fat_id_frmLogin\"]/fieldset[2]/input"));
}
	
	public WebElement getLoginButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"frm_fat_id_frmLogin\"]/fieldset[4]/input"));
}
	
	public void logIn(String email, String password) {
		getEmail().sendKeys(email);
		getPassword().sendKeys(password);
		getLoginButton().click();
	}
	
	
}
