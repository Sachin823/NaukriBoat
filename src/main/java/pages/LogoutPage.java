package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumcore.BasePage;

public class LogoutPage extends BasePage {
	
	
	/**Class constructor***/
	public LogoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement logoutButton;
	
	
	
	public void logOutOfApplication(WebDriver driver) {
		waitAndClick(driver,logoutButton);
	}

}
