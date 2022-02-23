package mail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot 
{ 
	public static String screenshotpath;

	public static void takescreenshot(WebDriver driver) throws IOException
    {
		screenshotpath = System.getProperty("user.dir")+"/error/img.png";
		
		TakesScreenshot scrnshot = ((TakesScreenshot) driver);
    
    	File SrcFile = scrnshot.getScreenshotAs(OutputType.FILE);
    	
    	File DestFile = new File(screenshotpath);
    	
    	FileUtils.copyFile(SrcFile, DestFile);
    }
   
}


