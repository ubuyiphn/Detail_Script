package mail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Ubuy.Save_Price_Of_API_URL.Browser;

public class Screenshot 
{ 
	public static String screenshotpath = System.getProperty("user.dir")+"/error.png";

	public static void takescreenshot() throws IOException
    {
        TakesScreenshot scrnshot = ((TakesScreenshot)Browser.driver);
    
    	File SrcFile = scrnshot.getScreenshotAs(OutputType.FILE);
    	
    	File DestFile = new File(screenshotpath);
    	
    	FileUtils.copyFile(SrcFile, DestFile);
    }
   
}


