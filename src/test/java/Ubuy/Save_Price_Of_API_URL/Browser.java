package Ubuy.Save_Price_Of_API_URL;

import java.awt.Robot;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import mail.Screenshot;
import mail.SendMail;

public class Browser 
{
    public int url_count;
    
    public Boolean availability;
    
    public static List<String>not_refreshed_temp_url = new ArrayList<>();
    
    public static List<String>not_refreshed_url = new ArrayList<>();
    
    public static List<String>not_found_page_url = new ArrayList<>();
    
    public static List<String>out_of_stock_url = new ArrayList<>();
    
    public static List<String>restricted_product_url = new ArrayList<>();
    
    public static List<String>price_available_url = new ArrayList<>();
    
    public static Robot r;
	
	public void hitAPIURL(WebDriver driver) throws InterruptedException
	{
		driver.get("https://www.a.ubuy.com.kw/am/google_content_api2_1/get_products_url_script.php?time=23sdfsdfs");
    	
   	 	Thread.sleep(3000);
	}
	
		public static String print_Current_Date_And_Time()
		{
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			
			 LocalDateTime now = LocalDateTime.now();  
			 
			 return dtf.format(now);
		}

	public Boolean verify_xpath(String x_path,WebDriver driver)
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
	
	public void close_tabs(WebDriver driver) throws InterruptedException, IOException, MessagingException
	{
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		
		System.out.println("Tab size is "+ tabs.size());
		for(String temp : tabs) 
		{
		    driver.switchTo().window(temp);
		    System.out.println(driver.getCurrentUrl());
		    System.out.println(tabs.indexOf(temp)+"\r\nWhile closing tabs  "+temp);
		    if(driver.getCurrentUrl().equals("https://www.a.ubuy.com.kw/am/google_content_api2_1/get_products_url_script.php?time=23sdfsdfs"))
		    {
		    	driver.close();
		    }
		    
		    else if(verify_xpath("//h3[@class='h2-heading price']",driver) == true)
		    {			
		    	price_available_url.add(driver.getCurrentUrl());
			
			    driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']",driver) == true)
		    {			
			    	not_refreshed_temp_url.add(driver.getCurrentUrl());
				
			    	driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div",driver) == true)
		    {			
		    	not_refreshed_temp_url.add(driver.getCurrentUrl());
				
		    	driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='product-not-found m-auto']/p",driver) == true)
	    	{
	    		not_found_page_url.add(driver.getCurrentUrl());
			
		     	driver.close();
		    }
		
		    else if(verify_xpath("//span[@class='out-of-stock ml-2']",driver) == true)
		    {
			    out_of_stock_url.add(driver.getCurrentUrl());
			
		    	driver.close();
		    }
		    
		    else
		    {
		    	System.out.println("Problem unknown"+driver.getCurrentUrl());
		    
		    	Screenshot.takescreenshot(driver);
			
		    	SendMail.send_error_mail();
				
		    	driver.close();
		    }
	    }
	}
	
	public void get_url_with_status()
	{
		int count = 1;
		
		Static_Data.price_saved_urls_file_stream.append("URL in which price is available are : \r\n");
		
		for(String url : price_available_url)
		{
			Static_Data.price_saved_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
		price_available_url.clear();
		
		//System.out.println("price saved urls count is "+price_available_url.size());
		
		Static_Data.not_loaded_urls_file_stream.append("\r\nNot loaded page url are : \r\n");
		
		count = 1;
		
        for(String url : not_refreshed_url)
        {
        	Static_Data.not_loaded_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
        not_refreshed_temp_url.clear();
        
        not_refreshed_url.clear();
        
        //System.out.println("not_refreshed_temp_url count is "+not_refreshed_temp_url.size());
        
        Static_Data.not_found_urls_file_stream.append("\r\nNot found page url are : \r\n");
		
		count = 1;
		
		for(String url : not_found_page_url)
        {
			Static_Data.not_found_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
		not_found_page_url.clear();
		
		//System.out.println("not_found_page_url count is "+not_found_page_url.size());
		
		Static_Data.out_of_stock_urls_file_stream.append("\r\nOut of stock page url are : \r\n");
		
		count = 1;
		
		for(String url : out_of_stock_url)
        {
			Static_Data.out_of_stock_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
		out_of_stock_url.clear();
		
		//System.out.println("out_of_stock_url count is "+out_of_stock_url.size());
		
		Static_Data.restricted_urls_file_stream.append("\r\nRestricted page url are : \r\n");
		
		count = 1;
		
		for(String url : restricted_product_url)
        {
			Static_Data.restricted_urls_file_stream.append(count+". "+url+"\r\n\r\n");
			
			count++;
		}
		
		restricted_product_url.clear();
		
		//System.out.println("restricted_product_url count is "+restricted_product_url.size());
		
		fetch_browser_data.all_urls.clear();
		
		//System.out.println(fetch_browser_data.all_urls);
	}
	
	public void close_non_refreshed_tabs(WebDriver driver) throws IOException, InterruptedException, MessagingException
	{
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		
		System.out.println("Tab size in non refreshed tabs "+tabs.size());
		
		for(String temp : tabs) 
		{
		    driver.switchTo().window(temp);
		    System.out.println(tabs.indexOf(temp)+"\r\nWhile closing tabs"+temp);
		    
		    if(driver.getCurrentUrl().equals("data:,"))
		    {
	    		driver.close();
		    }
		    
		    else if(verify_xpath("//h3[@class='h2-heading price']",driver) == true)
		    {			
		    	price_available_url.add(driver.getCurrentUrl());
			
			    driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']",driver) == true)
		    {			
		    	not_refreshed_url.add(driver.getCurrentUrl());
				
		    	Screenshot.takescreenshot(driver);
					
				SendMail.send_error_mail();
		    
		    	driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div",driver) == true)
		    {			
		    	not_refreshed_url.add(driver.getCurrentUrl());
				
		    	Screenshot.takescreenshot(driver);
					
				SendMail.send_error_mail();
		    
		    	driver.close();
		    }
		
		    else if(verify_xpath("//div[@class='product-not-found m-auto']/p",driver) == true)
	    	{
	    		not_found_page_url.add(driver.getCurrentUrl());
			
		     	driver.close();
		    }
		
		    else if(verify_xpath("//span[@class='out-of-stock ml-2']",driver) == true)
		    {
			    out_of_stock_url.add(driver.getCurrentUrl());
			
		    	driver.close();
		    }
		    
		   	else
		   	{
		   		System.out.println("Problem unknown"+driver.getCurrentUrl());
		    	
		   		Screenshot.takescreenshot(driver);
				
		   		SendMail.send_error_mail();
				
		   		driver.close();
		   	}
	    }
	}
	
}
