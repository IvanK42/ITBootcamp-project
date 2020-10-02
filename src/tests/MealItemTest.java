package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartSummaryPage;
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
		//Thread.sleep set for assuring all the elements load 
		Thread.sleep(500);
		meal.add("3");

		Assert.assertEquals(notif.getMessageContent(), "Meal Added To Cart", "[ERROR] Meal not added to cart");
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
		//Thread.sleep set for the message to load 
		Thread.sleep(500);
		Assert.assertEquals(notif.getMessageContent(), "Please login first!");

		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		login.logIn(this.email, this.password);

		this.driver.navigate().to(this.baseUrl + "/meal/bread-and-chocolate");
		meal.favorite();
		Assert.assertEquals(notif.getMessageContent(), "Product has been added to your favorites.", "[ERROR] Meal not added to favorites");
			
	}
	
	@Test (priority = 10)
	public void clearCartTest() throws IOException, InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/meals");
		LocationPopupPage location = new LocationPopupPage(this.driver);
		NotificationSystemPage notif = new NotificationSystemPage(this.driver);
		MealPage meal = new MealPage(this.driver);
		CartSummaryPage cart = new CartSummaryPage(this.driver);
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheet("Meals");
		SoftAssert sAssert = new SoftAssert();
	
		location.setLocation("City Center - Albany");

		for (int i = 1; i < sheet1.getLastRowNum(); i++) {
			XSSFRow row = sheet1.getRow(i);

			XSSFCell cell1 = row.getCell(0);
			//Thread.sleep set for assuring all the elements load 
			Thread.sleep(500);
			this.driver.navigate().to(cell1.getStringCellValue());

			meal.add("1");
			
			sAssert.assertEquals(notif.getMessageContent(), "Meal Added To Cart");
		}
		
		cart.clearAllFromCart();
		Assert.assertEquals(notif.getMessageContent(), "All meals removed from Cart successfully", "[ERROR] Cart is not clear");
		
		fis.close();
		wb.close();
	}

}
