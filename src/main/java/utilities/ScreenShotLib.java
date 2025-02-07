package utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class ScreenShotLib {
	
	
	public  String getscreenshot(WebDriver driver ,String filename)	{
		
		File srcfile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\screenShots\\" + filename + ".png";
		File destfile= new File(path);
		
		try {
			Files.copy(srcfile, destfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
