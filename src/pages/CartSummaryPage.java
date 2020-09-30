package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasicPage {
		
	public CartSummaryPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getClearAll() {
		return this.driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/a[2]"));
}
	public void clearAllFromCart() {
		getClearAll().click();
	}
	
	
	
	
}
