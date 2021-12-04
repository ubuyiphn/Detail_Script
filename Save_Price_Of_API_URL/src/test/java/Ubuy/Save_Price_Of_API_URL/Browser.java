package Ubuy.Save_Price_Of_API_URL;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import mail.Screenshot;
import mail.SendMail;

public class Browser 
{
    public static WebDriver driver;
    
    public static JavascriptExecutor js;
    
    public int url_count;
    
    public Boolean availability;
    
    public static List<String>not_refreshed_url = new ArrayList<>();
    
    public static List<String>not_found_page_url = new ArrayList<>();
    
    public static List<String>out_of_stock_url = new ArrayList<>();
    
    public static List<String>restricted_product_url = new ArrayList<>();
    
    public static List<String>price_available_url = new ArrayList<>();
    
	
	public void launch_chrome()
    {
		WebDriverManager.chromedriver().setup();
		
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/chromedriver.exe");
    	
    	ChromeOptions options = new ChromeOptions();
    	
    	//options.addArguments("headless");
    	
    	driver = new ChromeDriver(options);
    	
    	driver.manage().window().maximize();
    	
    	js = (JavascriptExecutor) driver;
    }
	
	
		public static String print_Current_Date_And_Time()
		{
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			
			 LocalDateTime now = LocalDateTime.now();  
			 
			 return dtf.format(now);
		}

	
	public void hit_api_url()
	{
		//driver.get("https://www.a.ubuy.com.kw/am/google_content_api/get_products_url_script.php?time=23234");
		
		driver.get("https://www.a.ubuy.com.kw/am/google_content_api2_1/get_products_url_script.php?time=23sdfsdfs");
	}
	
	public void open_new_tabs(int min_limit, int max_limit) throws InterruptedException, MalformedURLException
	{
		url_count = min_limit;
		
		int tab_count = 1;
		
		try {
			
			while(url_count <= fetch_browser_data.all_urls.size() && url_count <= max_limit  && url_count >= min_limit)
			{
			    String url = fetch_browser_data.all_urls.get(url_count).toString();
			
			    js.executeScript("window.open('')");
			    
			    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
				
				driver.switchTo().window(tabs.get(tab_count));
				
				driver.get(url);
				
				System.out.println(url);
			    
			    url_count++;
			    
			    tab_count++;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public Boolean verify_xpath(String x_path)
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
	
	public void close_tabs() throws InterruptedException, IOException, MessagingException
	{
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		
		int tab_count = 0;
		
		while(tab_count <= url_count) 
		{
		
		    driver.switchTo().window(tabs.get(tab_count));
		
		    if(tab_count == 0)
		    {
		    	driver.close();
		    	
		    	tab_count++;
		    }
		    
		    else if(verify_xpath("//h3[@class='h2-heading price']") == true)
		    {			
		    	price_available_url.add(driver.getCurrentUrl());
			
			    driver.close();
			
			    tab_count++;
		    }
		
		    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
		    {			
			    Thread.sleep(5000);
			
			    if(verify_xpath("//h3[@class='h2-heading price']") == true)
		    	{
			    	price_available_url.add(driver.getCurrentUrl());
				
			    	driver.close();
			    }
			 
			    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
		    	{
			    	not_found_page_url.add(driver.getCurrentUrl());
				
			    	driver.close();
			    }
			
			    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
			    {
			    	out_of_stock_url.add(driver.getCurrentUrl());
				
			    	driver.close();
			    }
			
			    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
			    {
			    	not_refreshed_url.add(driver.getCurrentUrl());
			 	
			    	Screenshot.takescreenshot();
					
					SendMail.send_error_mail();
			    	
			    	driver.close();	
			    }
			
			    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
			    {
			    	not_refreshed_url.add(driver.getCurrentUrl());
				
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
		    		price_available_url.add(driver.getCurrentUrl());
		 		
				    driver.close();
			    }
			
			    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
			    {
			    	not_found_page_url.add(driver.getCurrentUrl());
				
			     	driver.close();
		    	}
			
			    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
		    	{
			    	out_of_stock_url.add(driver.getCurrentUrl());
				
			    	driver.close();
		    	}
			
			    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
			    {
			    	not_refreshed_url.add(driver.getCurrentUrl());
				
			    	Screenshot.takescreenshot();
					
					SendMail.send_error_mail();
			    	
			    	driver.close();
			    }
			
			    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
			    {
			    	not_refreshed_url.add(driver.getCurrentUrl());
				
			    	Screenshot.takescreenshot();
					
					SendMail.send_error_mail();	
			    	
			    	driver.close();
			    }
		    	tab_count++;
			
		    }
		
		    else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
	    	{
	    		not_found_page_url.add(driver.getCurrentUrl());
			
		     	driver.close();
			
		    	tab_count++;
		    }
		
		    else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
		    {
			    out_of_stock_url.add(driver.getCurrentUrl());
			
		    	driver.close();
		  	
		    	tab_count++;
		    }
	    }
		
	}
	
	public void get_url_with_status()
	{
		int count = 1;
		
		Index.price_saved_urls_file_stream.append("URL in which price is available are : \r\n");
		
		for(String url : price_available_url)
		{
			Index.price_saved_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
		price_available_url.clear();
		
		//System.out.println("price saved urls count is "+price_available_url.size());
		
		Index.not_loaded_urls_file_stream.append("\r\nNot loaded page url are : \r\n");
		
		count = 1;
		
        for(String url : not_refreshed_url)
        {
        	Index.not_loaded_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
        not_refreshed_url.clear();
        
        //System.out.println("not_refreshed_url count is "+not_refreshed_url.size());
        
        Index.not_found_urls_file_stream.append("\r\nNot found page url are : \r\n");
		
		count = 1;
		
		for(String url : not_found_page_url)
        {
			Index.not_found_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
		not_found_page_url.clear();
		
		//System.out.println("not_found_page_url count is "+not_found_page_url.size());
		
		Index.out_of_stock_urls_file_stream.append("\r\nOut of stock page url are : \r\n");
		
		count = 1;
		
		for(String url : out_of_stock_url)
        {
			Index.out_of_stock_urls_file_stream.append(count+". "+url+"\r\n");
			
			count++;
		}
		
		out_of_stock_url.clear();
		
		//System.out.println("out_of_stock_url count is "+out_of_stock_url.size());
		
		Index.restricted_urls_file_stream.append("\r\nRestricted page url are : \r\n");
		
		count = 1;
		
		for(String url : restricted_product_url)
        {
			Index.restricted_urls_file_stream.append(count+". "+url+"\r\n\r\n");
			
			count++;
		}
		
		restricted_product_url.clear();
		
		//System.out.println("restricted_product_url count is "+restricted_product_url.size());
		
		fetch_browser_data.all_urls.clear();
		
		//System.out.println(fetch_browser_data.all_urls);
		
		
	}
	
}
