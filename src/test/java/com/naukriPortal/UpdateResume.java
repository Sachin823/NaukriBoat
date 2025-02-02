package com.naukriPortal;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateResume  {
	
	public static WebDriver driver;
	
	@Test
	public void updateResumeTest() throws Exception {
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.naukri.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
		driver.findElement(By.id("login_Layer")).click();
		
		WebElement element = driver.findElement(By.xpath("//*[text()='Email ID / Username']/following-sibling::input"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: ; border: 3px solid red;')", element);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys("sh.sachin020@gmail.com");
		element=driver.findElement(By.xpath("//*[text()='Password']/following-sibling::input"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys("Password@123");
		
		element=driver.findElement(By.xpath("//button[text()='Login']"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element=driver.findElement(By.xpath("//div[@class='view-profile-wrapper']/a"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element=driver.findElement(By.xpath("//input[@value='Update resume']"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		StringSelection selection= new StringSelection("C:\\Users\\SACHIN\\eclipse-workspace\\NaukriBoat\\src\\main\\resources\\Sachin.Sharma.Resume.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);;
		
		Robot robot=new Robot();
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.setAutoDelay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		element=driver.findElement(By.id("attachCVMsgBox"));
		wait.until(ExpectedConditions.visibilityOf(element));
		
		element= driver.findElement(By.xpath("//div[@class='nI-gNb-drawer']"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		element=driver.findElement(By.xpath("//a[@title='Logout']"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
		wait.until(ExpectedConditions.invisibilityOf(element));
		Assert.assertEquals(driver.getTitle(), "Jobs - Recruitment - Job Search - Employment - Job Vacancies - Naukri.com");
		driver.quit();
		
		
	}

}
