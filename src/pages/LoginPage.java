package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver) {
		super(driver);
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
		getEmail().clear();
		getEmail().sendKeys(email);
		getPassword().clear();
		getPassword().sendKeys(password);
		getLoginButton().click();
	}
	
	
}
