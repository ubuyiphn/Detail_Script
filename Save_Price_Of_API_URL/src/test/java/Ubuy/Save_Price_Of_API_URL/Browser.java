package Ubuy.Save_Price_Of_API_URL;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

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
    	
    	driver = new ChromeDriver();
    	
    	driver.manage().window().maximize();
    	
    	js = (JavascriptExecutor) driver;
    }
	
	public void hit_api_url()
	{
		driver.get("https://www.a.ubuy.com.kw/am/google_content_api/get_products_url_script.php?time=23234");
	}
	
	public void open_new_tabs(int min_limit, int max_limit) throws InterruptedException, MalformedURLException
	{
		url_count = min_limit;
		
		try {
			
			while(url_count <= fetch_browser_data.all_urls.size() && url_count <= max_limit  && url_count >= min_limit)
			{
			    String url = fetch_browser_data.all_urls.get(url_count).toString();
			
			    js.executeScript("window.open('')");
			    
			    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
				
				driver.switchTo().window(tabs.get(url_count));
				
				driver.get(url);
			    
			    url_count++;
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
	
	public void close_tabs() throws InterruptedException
	{
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		
		int tab_count = 0;
		
		while(tab_count<url_count) 
		{
		
		    driver.switchTo().window(tabs.get(tab_count));
		
		    if(verify_xpath("//h3[@class='h2-heading price']") == true)
		    {			
		    	price_available_url.add(driver.getCurrentUrl());
			
			    driver.close();
			
			    tab_count++;
		    }
		
		    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
		    {
		    	driver.navigate().refresh();
			
			    Thread.sleep(3000);
			
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
			 	
			    	driver.close();	
			    }
			
			    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
			    {
			    	not_refreshed_url.add(driver.getCurrentUrl());
				
			    	driver.close();	
			    }
			    tab_count++;
			
		    }
		
		    else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
		    {
		    	driver.navigate().refresh();
			
		    	Thread.sleep(3000);
			
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
				
			    	driver.close();
			    }
			
			    else if(verify_xpath("//div[@class='detail-page-skelton container card-skeleton']") == true)
			    {
			    	not_refreshed_url.add(driver.getCurrentUrl());
				
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
		    Thread.sleep(1000);
		
	    }
		
	}
	
	public void get_url_with_status()
	{
		int count = 1;
		
		System.out.println("URL in which price is available are : \n");
		
		for(String url : price_available_url)
		{
			System.out.println(count+". "+url);
			
			count++;
		}
		
		System.out.println("\nNot Refreshed page url are : \n");
		
		count = 1;
		
        for(String url : not_refreshed_url)
        {
			System.out.println(count+". "+url);
			
			count++;
		}
		
		System.out.println("\nNot found page url are : \n");
		
		count = 1;
		
		for(String url : not_found_page_url)
        {
			System.out.println(count+". "+url);
			
			count++;
		}
		
		System.out.println("\nOut of stock page url are : \n");
		
		count = 1;
		
		for(String url : out_of_stock_url)
        {
			System.out.println(count+". "+url);
			
			count++;
		}
		
		System.out.println("\nRestricted page url are : \n");
		
		count = 1;
		
		for(String url : restricted_product_url)
        {
			System.out.println(count+". "+url);
			
			count++;
		}
	}
	
}
