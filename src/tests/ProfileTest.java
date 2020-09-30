package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	@Test (priority = 0)
	public void editProfileTest() throws IOException, InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		LocationPopupPage popup = new LocationPopupPage(this.driver);
		ProfilePage profile = new ProfilePage (this.driver);
		LoginPage login = new LoginPage(this.driver);
		NotificationSystemPage notif = new NotificationSystemPage(this.driver);
		AuthPage authPage = new AuthPage(this.driver);
		
		popup.clickClose();
		login.logIn(this.email, this.password);
		Assert.assertEquals(notif.getMessageContent(), "Login Successfull");
		
		this.driver.navigate().to(this.baseUrl + "/member/profile");

		profile.updateLoginInfo("Test", "Test", "Test 123", "061/12345678","18000",  "India" , "Bihar", "Ara");		
		Assert.assertEquals(notif.getMessageContent(), "Setup Successful");
		
		authPage.logout();
		Assert.assertEquals(notif.getMessageContent(), "Logout Successfull!");
	}
	
	@Test (priority = 5)
	public void changeProfileImg() throws IOException, InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		LocationPopupPage popup = new LocationPopupPage(this.driver);
		ProfilePage profile = new ProfilePage (this.driver);
		LoginPage login = new LoginPage(this.driver);
		NotificationSystemPage notif = new NotificationSystemPage(this.driver);
		AuthPage authPage = new AuthPage(this.driver);
		
		popup.clickClose();
		login.logIn(this.email, this.password);
		Assert.assertEquals(notif.getMessageContent(), "Login Successfull");
		
		this.driver.navigate().to(this.baseUrl + "/member/profile");
		String imgPath = new File("images/ss.png").getCanonicalPath();
		profile.uploadClick();
		profile.uploadImg(imgPath);

		Assert.assertEquals(notif.getMessageContent(), "Profile Image Uploaded Successfully");
		notif.noMessage();
		
		profile.removeImg();
		notif.noMessage();

		authPage.logout();
		Assert.assertEquals(notif.getMessageContent(), "Logout Successfull!");
	}

}
