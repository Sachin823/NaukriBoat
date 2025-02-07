package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumcore.BasePage;

public class LoginPage extends BasePage {
	
	
	/**Class constructor***/
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="login_Layer")
	private WebElement loginLayer;
	
	@FindBy(xpath = "//*[text()='Email ID / Username']/following-sibling::input")
	private WebElement userInput;

	@FindBy(xpath = "//*[text()='Password']/following-sibling::input")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;
	
	
	/**
	 * Function: Login application.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public HomePage loginInApplication(WebDriver driver, String userName, String password) {
		waitAndClick(driver,loginLayer);
		inputText(userInput, userName);
		inputText(passwordInput, password);
		waitAndClick(driver,loginButton);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	
	
	

}
