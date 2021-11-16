package webcall;

//TO SEND THE TIME TAKEN TO LOAD DETAIL PAGE WITH IMAGE every hour
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLoadTime 
{
	public static WebDriver driver2;
	static long totalTime; // to send detail page load time
	static long totalTime2; // to send detail page load time
	public static void main(String urll) 
	{
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--blink-settings=imagesEnabled=true");
		op.addArguments("window-size=1400,800");
		driver2 = new ChromeDriver(op);
		try 
		{
			long start = System.currentTimeMillis();
			driver2.get(urll);
			WebDriverWait wait = new WebDriverWait(driver2, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='title h1 mb-2' or contains(text(),'Don�t worry...') or contains(@alt,'not-available')]")));
			long finish = System.currentTimeMillis();
			totalTime = finish - start;
			System.out.println("Total Time for DETAIL load(MilliSeconds) - " + totalTime);
			emailme2("Detail Speed(MilliSeconds) �" +totalTime);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//TO SEND THE TIME TAKEN TO LOAD SEARCH PAGE
		try {
			String generatedString = RandomStringUtils.randomAlphabetic(10);
			driver2.findElement(By.xpath("//input[@autofocus='autofocus']")).sendKeys(generatedString);
			long start2 = System.currentTimeMillis();
			// //body/main[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/button[1]     -- Old xpath
			driver2.findElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/div[2]/div[1]/form[1]/button[1]")).click();
			WebDriverWait wait = new WebDriverWait(driver2, 60);
			// //body/div[6]/div[3]/div[1]/div[1]/div[2]/div[3]/div[3]   Old xpath
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[2]/div[3]/div[3]")));
			long finish2 = System.currentTimeMillis();
			totalTime2 = finish2 - start2;
			System.out.println("Total Time for SEARCH load(MilliSeconds) - " + totalTime2);
			emailme2("Search Speed(MilliSeconds) �" +totalTime2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
		driver2.quit();
	}
	// TO GET MAIL & SCREENSHOT OF "PAGE WITH IMAGE" LOAD TIME
	public static void emailme2(String string) 
	{
		try {
			TakesScreenshot screenshot = (TakesScreenshot) driver2;
			@SuppressWarnings("unused")
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			File file = ((TakesScreenshot) driver2).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("/home/anuj/git/Atomation/WebServiceHit/Screenshots/screenshot.png"));
			System.out.println("Screenshot Taken");
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath("/home/anuj/git/Atomation/WebServiceHit/Screenshots/screenshot.png");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Error");
			attachment.setName("Screen");
			attachment.getURL();
			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("manayasam@gmail.com", "omitit123"));
			email.setSSLOnConnect(true);
			email.setFrom("manayasam@gmail.com");
			email.setSubject(string);
			email.setMsg(string);
			email.addTo("shailendra.singh@ubuy.com");
			//email.addTo("ramesh.saini@ubuy.com");
			email.addTo("himanshu.khandelwal@ubuy.com");
			email.addTo("om@ubuy.com");
		   //	email.addTo("pradeep.singh@ubuy.com");
			email.attach(attachment);
			email.send();
		}
		catch (Exception e) 
		{
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}
}
