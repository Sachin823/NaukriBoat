package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumcore.BasePage;


public class HomePage extends BasePage {
	
	
	/**Class constructor***/
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='view-profile-wrapper']/a")
	private WebElement viewProfileButton;
	
	
	
	public ProfilePage clickOnViewProfileButton(WebDriver driver) {
		waitAndClick(driver,viewProfileButton);
		return PageFactory.initElements(driver, ProfilePage.class);
	}

}
