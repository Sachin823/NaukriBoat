package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import seleniumcore.BasePage;

public class ProfilePage extends BasePage {
	
	
	/**Class constructor***/
	public ProfilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@value='Update resume']")
	private WebElement updateResumeButton;
	
	@FindBy(id = "attachCVMsgBox")
	private WebElement msgBar;
	
	@FindBy(xpath = "//div[@class='nI-gNb-drawer']")
	private WebElement profileBarButton;
	
	
	
	public void updateResume(WebDriver driver,String fileName) throws Exception {
		waitForElement(driver, updateResumeButton);
		waitAndClick(driver,updateResumeButton);
		_normalWait(timeout);
		uploadFileUsingRobotclass(fileName);
		
		waitForElementPresent(msgBar,20);
		if (msgBar.isDisplayed()) {
			Reporter.log("Updated Successfully");
		}
		Assert.assertEquals(msgBar.isDisplayed(), true);
		waitAndClick(driver,profileBarButton);
	}

}
