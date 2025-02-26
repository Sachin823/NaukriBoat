package seleniumcore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ProfilePage;
import utilities.ScreenShotLib;

public abstract class BaseTest {
	
	
	public WebDriver driver;
	public String resultPath;
	public ExtentTest test;
	public ExtentReports extent;
	public static Properties prop;
	
	public LoginPage loginPage = new LoginPage(driver);
	public HomePage homePage= new HomePage(driver);
	public ProfilePage profilePage= new ProfilePage(driver);
	public LogoutPage logoutPage= new LogoutPage(driver);
	
	@BeforeSuite
	public void before() throws Exception {
		// Create Result repository for report.
		String timeStamp = getFormattedTimeStamp().replace("-", "").replace(":", "").replace(".", "");
		String path = System.getProperty("user.dir");
		resultPath = path + "/Result/Suite_" + timeStamp;
		new File(resultPath).mkdirs();
		extent = new ExtentReports(resultPath + "/CustomReport.html", true);
		
	}
	
	public BaseTest() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("C:\\Users\\SACHIN\\QA\\WEB_AUTOMATION_FRAMEWORK\\config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * @function: Get formatted Time stamp
	 * 
	 */
	public String getFormattedTimeStamp() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-uuuu HH:mm:ss");
		String formatDateTime = currentTime.format(formatter);
		return formatDateTime;
	}
	
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
			//options.addArguments("--headless=new");
			driver=new ChromeDriver(options);
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
		driver.navigate().to(prop.getProperty("applicationUrl"));
	}
	
	@AfterMethod
	public void postcondition(ITestResult result)
	{
		if(result.isSuccess()) {		
			test.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success");
		}
		else if(result.getStatus()==ITestResult.SKIP) {
            test.log(LogStatus.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
        }
		else {
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			
			//String packagename= result.getMethod().getRealClass().getPackage().getName();
			String filename=result.getMethod().getMethodName();
			ScreenShotLib slib= new ScreenShotLib();
			String path= slib.getscreenshot(driver, filename);
			test.log(LogStatus.FAIL, test.addScreenCapture(path));
			Reporter.log("Some issue has been detect, See ScreeShot under Screenshot folder",true);
		}
		extent.endTest(test);
		Reporter.log("Browser closed",true);
	}
	
	public void Startlog(String MethodName)	{ 
		test = extent.startTest(MethodName);
	}
	
	public void logStep(String message) {
		Reporter.log(message, true);
		test.log(LogStatus.INFO, message);
	}
	
	
	@AfterSuite
	public void endReport(){
		
                extent.flush();
                extent.close(); 
        		driver.quit();

    }
	
	

}
