package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

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
		
		popup.clickClose();
		login.logIn(this.email, this.password);
		Assert.assertEquals(notif.getMessageContent(), "Login Successfull");
		
		this.driver.navigate().to(this.baseUrl + "/member/profile");

		profile.updateLoginInfo("Ivan", "Kostadinovic", "Knjazevacka 123", "061/12345678","18000",  "India" , "Bihar", "Ara");
				
		Assert.assertEquals(notif.getMessageContent(), "Setup Successfull");
		
		

	}
	
	
}
