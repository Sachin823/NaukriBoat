package seleniumcore;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	public  WebDriver driver;
	public int timeout = 8;
	public WebElement lastElem = null;
	public WebElement element;
	public long explicit_timeout = 40;
	
	/** normal wait for thread. */
	public void _normalWait(long timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void javascriptHilightingElement(WebDriver driver, WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 3px solid red;')", webElement);
		lastElem = webElement;
	}

	/**
	 * Function: UnHighlight the WebElement and capture screenshot of the event
	 * @param WebElement
	 */
	public void unhighLightElement(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 0px;');", lastElem);
	}
	/**
	 * Move to Particular Element
	 * 
	 * @param element
	 */
	public void moveToElement(WebDriver driver, WebElement element) {
		Actions actn = new Actions(driver);
		actn.moveToElement(element).build().perform();
		javascriptHilightingElement(driver,element);
		_normalWait(timeout);
		unhighLightElement(driver);
	}
	
	public void waitAndClick(WebDriver driver,WebElement element) {
		_normalWait(timeout);
		waitForElement(driver,element);
		moveToElement(driver,element);
		element.click();
	}
	
	/** Handle locator type */
	public By ByLocator(String locator) {
		By result;
		if (locator.startsWith("//") || locator.startsWith("(//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.id(locator.replace("#", ""));
		} else if (locator.startsWith("name=")) {
			result = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		} else {
			result = By.className(locator);
		}
		return result;
	}
	
	/**
	 * Pass the element to string and it will return the instance of WebElement
	 * @param element
	 * @return
	 */
	private WebElement _getWebElementOnExistence(String element) {
		WebElement element1 = driver.findElement(ByLocator(element));
		return element1;
	}
	
	/** Wait for element to be present */
	public void waitForElement(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicit_timeout));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/** Input text as string */
	public void inputText(WebElement element, String text) {
		//javascriptHilightingElement(element);
		element.clear();
		element.click();
		element.sendKeys(text);
	}
	
	/**
	 * Input the string in text box
	 * @param element
	 * @param text
	 */
	public void inputText(String element, String text) {
		WebElement element1 = _getWebElementOnExistence(element);
		inputText(element1, text);
	}
	
	/**
	 * 
	 * File uploading using Robot Class
	 * 
	 * @param fileName
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void uploadFileUsingRobotclass(String fileName) throws InterruptedException, AWTException {
		/*
		 * String FilePath = System.getProperty("user.dir") + File.separator +
		 * "src\\main\\resources" + File.separator + fileName;
		 * 
		 * FilePath = FilePath.replace("\\", "\\");
		 */
		_normalWait(timeout);
		StringSelection sel = new StringSelection(fileName);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);

		Robot robot = new Robot();

		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/** Wait for element to be present by web element */
	public WebElement waitForElementPresent(WebElement webElement, int timeOutInSeconds) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			element = wait.until(ExpectedConditions.visibilityOf(webElement));
			return element;
		} catch (Exception e) {

		}
		return null;
	}
	
	
	

}
