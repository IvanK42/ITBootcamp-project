package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public NotificationSystemPage(WebDriver driver) {
			this.driver = driver;
			this.wait = new WebDriverWait (driver, 10);
	}
	
	public WebElement getMessage() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
}
	
	public String getMessageContent() {
		return getMessage().getText();
}
	
	public void noMessage() {
		wait.until(ExpectedConditions.attributeContains((By.xpath("//*[@id=\"frm_fat_id_frmLogin\"]/fieldset[1]/input")), "style", "display: none;"));
	}	
}
