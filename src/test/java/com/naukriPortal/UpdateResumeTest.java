package com.naukriPortal;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ProfilePage;
import seleniumcore.BaseTest;

public class UpdateResumeTest extends BaseTest{
	

	@Test
	public void resumeUpdateProcess() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		homePage=loginPage.loginInApplication(driver,"sh.sachin020@gmail.com", "Password@123");
		
		HomePage homePage= new HomePage(driver);
		profilePage=homePage.clickOnViewProfileButton(driver);
		
		ProfilePage profilePage= new ProfilePage(driver);
		profilePage.updateResume(driver,"C:\\Users\\SACHIN\\eclipse-workspace\\NaukriBoat\\src\\main\\resources\\Sachin.Sharma.Resume.pdf");
		
		LogoutPage logoutPage= new LogoutPage(driver);
		logoutPage.logOutOfApplication(driver);
		
		
	
		
		
		
	}

}
