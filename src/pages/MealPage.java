package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getAdd() {
		return this.driver.findElement(By.xpath("//*[@id=\"body\"]/section[1]/div/div/div[2]/div/div[3]/div[2]/a"));
	}

	public WebElement getFavorite() {
		return this.driver.findElement(By.xpath("//*[@class=\"favourite  itemfav link\"]"));
	}
	
	public WebElement getQuantity() {
		return this.driver.findElement(By.xpath("//*[@id=\"body\"]/section[1]/div/div/div[2]/div/div[3]/div[1]/ul/li[3]/input"));
	}
	
	public void favorite() {
		getFavorite().click();
	}
	
	public void add(String quantity) {
		getQuantity().sendKeys(Keys.CONTROL + "a");
		getQuantity().sendKeys(Keys.DELETE);
		getQuantity().sendKeys(quantity);
		getAdd().click();
	}
}
