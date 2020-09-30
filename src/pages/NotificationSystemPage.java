package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {
	private WebDriverWait wait = new WebDriverWait(this.driver, 10);
	
	public NotificationSystemPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getMessage() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
}
	
	public String getMessageContent() {
		return getMessage().getText();
}

	public void noMessage() {
		wait.until(ExpectedConditions.attributeContains((By.xpath("//*[contains(@class, 'system_message')]")), "style", "display: none;"));
	}	
}
