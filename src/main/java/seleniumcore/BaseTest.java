package seleniumcore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ProfilePage;




public abstract class BaseTest {
	
	public WebDriver driver;
	public String applicationUrl="https://www.naukri.com/";
	
	public LoginPage loginPage = new LoginPage(driver);
	public HomePage homePage= new HomePage(driver);
	public ProfilePage profilePage= new ProfilePage(driver);
	public LogoutPage logoutPage= new LogoutPage(driver);

	
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	@Parameters("browserName")
	public void setUp(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-geolocation");
			options.addArguments("--disable-notifications");
			driver=new ChromeDriver();
			Reporter.log("Chrome launched",true);
		}else if(browserName.equals("Edge")) {
			driver= new EdgeDriver();
			Reporter.log("Edge launched",true);
		}else {
			driver=new ChromeDriver();
			Reporter.log("Default Browser i.e chrome launched",true);
		}
		/**
		 * Maximize windoW
		 */
		driver.manage().window().maximize();

		/**
		 * Delete cookies and set timeout
		 */
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		/**
		 * Open application URL
		 */
		driver.navigate().to(applicationUrl);
	}
	
	
	@AfterMethod
	public void postcondition()
	{
		driver.quit();
	}
	
}
