package scripts;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ProfilePage;
import seleniumcore.BaseTest;

public class UpdateResumeTest extends BaseTest{
	

	@Test
	public void resumeUpdateProcess() throws Exception {
		Startlog("Update Resume Test");
		
		logStep("Login to Application");
		LoginPage loginPage = new LoginPage(driver);
		homePage=loginPage.loginInApplication(driver,"sh.sachin020@gmail.com", "Password@123");
		
		logStep("Navigate to Profile");
		HomePage homePage= new HomePage(driver);
		profilePage=homePage.clickOnViewProfileButton(driver);
		
		logStep("Update Resume");
		ProfilePage profilePage= new ProfilePage(driver);
		profilePage.updateResume(driver,"C:\\Users\\SACHIN\\QA\\WEB_AUTOMATION_FRAMEWORK\\src\\test\\resources\\Sachin.Sharma.Resume.pdf");
		
		logStep("Logout from Application");
		LogoutPage logoutPage= new LogoutPage(driver);
		logoutPage.logOutOfApplication(driver);
		
		
	
		
		
		
	}

}
