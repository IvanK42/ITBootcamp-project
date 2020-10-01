package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPages extends BasicPage {

	public SearchResultPages(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getResults() {
		return this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}
	
	public ArrayList<String> mealName(){
		ArrayList<String> mealNames = new ArrayList<String>();
		for (int i = 0; i < getResults().size(); i++) {
			mealNames.add(getResults().get(i).getText());
		} return mealNames;
	}
	
	
	
	public int getSearchSize() {
		return getResults().size();
	}
}
