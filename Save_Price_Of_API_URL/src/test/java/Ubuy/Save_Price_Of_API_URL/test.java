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
	
	
	while(true) 
	{
	
	    
	
	    
	    
	    if(verify_xpath("//h3[@class='h2-heading price']") == true)
	    {			
	    	System.out.println("2");
	    	driver.close();
		
		    
	    }
	
	    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
	    {			
		    Thread.sleep(5000);
		
		    if(verify_xpath("//h3[@class='h2-heading price']") == true)
	    	{
		    	System.out.println("3");
			
		    	driver.close();
		    }
		 
		    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
	    	{
		    	System.out.println("4");
			
		    	driver.close();
		    }
		
		    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
		    {
		    	System.out.println("5");
			
		    	driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
		    {
		    	
		    	System.out.println("6");
		    	
		    	driver.close();	
		    }
		
		    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
		    {
		    	System.out.println("7");
		    		
		    	driver.close();
		    }
		    
	    }
	
	    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
	    {			
	    	Thread.sleep(5000);
		
	    	if(verify_xpath("//h3[@class='h2-heading price']") == true)
	      	{
	    		
	    		System.out.println("8");
			    driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
		    {
		    	
		    	System.out.println("9");
		     	driver.close();
	    	}
		
		    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
	    	{
		    	System.out.println("10");
			
		    	driver.close();
	    	}
		
		    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
		    {
		    	
			
		    	System.out.println("11");
		    	
		    	driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
		    {
		    	
			
		    	System.out.println("12");
		    	
		    	driver.close();
		    }
	    	
		
	    }
	
	    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
    	{
	    	System.out.println("13");
		
	     	driver.close();
		
	    	
	    }
	
	    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
	    {
		    
	    	System.out.println("14");
	    	driver.close();
	  	
	    	
	    }
	    else
	    {
	    	System.out.println("problem unknown");
	    	driver.close();
	    }
    }
}
}
	

