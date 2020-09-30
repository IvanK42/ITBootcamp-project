package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;

public class MealItemTest extends BasicTest {

	@Test (priority = 0)
	public void addMealToCartTest() throws IOException, InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		LocationPopupPage location = new LocationPopupPage(this.driver);
		NotificationSystemPage notif = new NotificationSystemPage(this.driver);
		MealPage meal = new MealPage(this.driver);

		location.clickClose();
		
		meal.add("2");
		
		
		Assert.assertEquals(notif.getMessageContent(), "The Following Errors Occurred:"+"\nPlease Select Location");		 											
		notif.noMessage();
		
		location.openLocationDialog();
		location.setLocation("City Center - Albany");
		Thread.sleep(500);
		meal.add("3");

		Assert.assertEquals(notif.getMessageContent(), "Meal Added To Cart");
	}
	
	@Test (priority = 5)
	public void addMealToFavoriteTest() throws IOException, InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		LocationPopupPage location = new LocationPopupPage(this.driver);
		LoginPage login = new LoginPage(this.driver);
		NotificationSystemPage notif = new NotificationSystemPage(this.driver);
		MealPage meal = new MealPage(this.driver);

		location.clickClose();
		
		meal.favorite();
		Thread.sleep(500);
		Assert.assertEquals(notif.getMessageContent(), "Please login first!");

		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		login.logIn(this.email, this.password);

		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		meal.favorite();
		Assert.assertEquals(notif.getMessageContent(), "Product has been added to your favorites.");
			
	}
	
}
