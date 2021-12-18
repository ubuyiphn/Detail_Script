package Ubuy.Save_Price_Of_API_URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import mail.Screenshot;
import mail.SendMail;

public class test 
{
	public static boolean availability;
	public static ChromeDriver driver;
    public static void main(String[] args) throws InterruptedException, IOException, MessagingException
    {
    	WebDriverManager.chromedriver().setup();
    	
    	driver = new ChromeDriver();
    	
    	driver.get("https://www.ubuy.co.in/product/14S8A54M-viparspectra-dimmable-xs1000-grow-light-with-4-x27-x4-x27-mylar-hydroponic-grow-tent-complete-kit-fo?utm_source=ubuy_gogsc");
  
    	//driver.get("https://www.a.ubuy.com.kw/en/product/4Y6U7A6-lifevac-choking-rescue-device-home-kit-for-adult-and-children-first-aid-kit-portable-choking-rescue");
    	
    	Thread.sleep(5000);
    	
    	close_tabs();
    }

    public static Boolean verify_xpath(String x_path)
	{
		try
		{
			availability = driver.findElement(By.xpath(x_path)).isDisplayed();
			
			return availability;
		}
		
		catch(Exception e)
		{
			availability = false;
			
			return availability;
		}
	}

    public static void close_tabs() throws InterruptedException, IOException, MessagingException
	{
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		
		int tab_count = 0;
		
		System.out.println("Tab size is "+ tabs.size());
		
		while(tab_count < tabs.size()) 
		{
		   System.out.println("Tab count is "+tab_count);
			
			driver.switchTo().window(tabs.get(tab_count));
		
		   if(verify_xpath("//h3[@class='h2-heading price']") == true)
		    {			
		    	System.out.println("close_tabs url.");
			
			    driver.close();
			
			    tab_count++;
		    }
		
		    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
		    {			
			    Thread.sleep(5000);
			
			    if(verify_xpath("//h3[@class='h2-heading price']") == true)
		    	{
			    	System.out.println("h2-heading price url.");
				
			    	driver.close();
			    }
			 
			    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
		    	{
			    	System.out.println("product-not-found url.");
				
			    	driver.close();
			    }
			
			    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
			    {
			    	System.out.println("out-of-stock url.");
				
			    	driver.close();
			    }
			
			    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
			    {
			    	System.out.println("loader-spin-overlay url.");
			 	
			    	driver.close();	
			    }
			
			    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
			    {
			    	System.out.println("detail-page-skelton container url.");
				
			    	driver.close();
			    }
			    
			    else
			    {
			    	System.out.println("Problem unknown 1");
			    	
			    	Screenshot.takescreenshot();
					
					SendMail.send_error_mail();
					
					driver.close();
			    }
			    tab_count++;
		    }
		
		    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
		    {			
		    	Thread.sleep(5000);
			
		    	if(verify_xpath("//h3[@class='h2-heading price']") == true)
		      	{
		    		System.out.println("h2-heading price url.");
		 		
				    driver.close();
			    }
			
			    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
			    {
			    	System.out.println("product-not-found url.");
				
			     	driver.close();
		    	}
			
			    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
		    	{
			    	System.out.println("out-of-stock url.");
				
			    	driver.close();
		    	}
			
			    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
			    {
			    	System.out.println("loader-spin-overlay url.");
			    	
			    	driver.close();
			    }
			
			    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
			    {
			    	System.out.println("detail-page-skelton container url.");
			    	
			    	driver.close();
			    }
		    	
			    else
			    {
			    	System.out.println("Problem unknown 2");
			    	
			    	Screenshot.takescreenshot();
					
					SendMail.send_error_mail();
					
					driver.close();
			    }
		    	tab_count++;
			
		    }
		
		    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
	    	{
		    	System.out.println("product-not-found url.");
			
		     	driver.close();
			
		    	tab_count++;
		    }
		   
		    else if(verify_xpath("//div[@class='bundle-container mb-3']") == true)
		    {
		    	System.out.println("Problem unknown 3");
		    	
//		    	Screenshot.takescreenshot();
//				
//				SendMail.send_error_mail();
				
				driver.close();
				
				tab_count++;
		    }
		
		    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
		    {
			    System.out.println("Out of stock url.");
			
		    	driver.close();
		  	
		    	tab_count++;
		    }
		    
		  
	    }
	}
}

	

