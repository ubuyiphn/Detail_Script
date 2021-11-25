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
    WebDriver driver;
    
    public static JavascriptExecutor js;
    
    public int url_count;
    
    public Boolean availability;
    
	public Browser(WebDriver driver)
    {
    	this.driver = driver;
    }
	
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
	
	public void open_new_tabs() throws InterruptedException, MalformedURLException
	{
		url_count = 1;
		
		while(url_count<=1)
		{
		
		    String url = fetch_browser_data.all_urls.get(url_count).toString();
		
		    System.out.println(url);
		
		    js.executeScript("window.open('')");
		    
		    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    	
	    	driver.switchTo().window(tabs.get(url_count));
	    	
	    	driver.get(url);
		    
		    url_count++;
		    
		}
		
	}
	
	public Boolean verify_xpath(String x_path)
	{
		try
		{
			availability = driver.findElement(By.xpath(x_path)).isDisplayed();
			
			System.out.println(x_path+""+availability);
			
			return availability;
		}
		
		catch(Exception e)
		{
			availability = false;
			
			System.out.println(x_path+""+availability);
			
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
			System.out.println("Price is available.");
			
			driver.close();
			
			tab_count++;
		}
		
		else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
		{
			System.out.println("Page not refreshed completely.");
			
			driver.navigate().refresh();
			
			Thread.sleep(3000);
			
			if(verify_xpath("//h3[@class='h2-heading price']") == true)
			{
				System.out.println("Price is available.");
				
				driver.close();
			}
			
			else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
			{
				System.out.println("Page not found.");
				
				driver.close();
			}
			
			else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
			{
				System.out.println("Out of stock.");
				
				driver.close();
			}
			
			else if(verify_xpath("//div[@class='loader-spin-overlay loading']/div") == true)
			{
				System.out.println("Page not refreshed completely.");
				
				driver.close();
				
			}
			tab_count++;
			
		}
		
		else if(verify_xpath("//div[@class='product-not-found m-auto']/p") == true)
		{
			System.out.println("Page not found.");
			
			driver.close();
			
			tab_count++;
		}
		
		else if(verify_xpath("//span[@class='out-of-stock ml-2']") == true)
		{
			System.out.println("Out of stock.");
			
			driver.close();
			
			tab_count++;
		}
		Thread.sleep(1000);
		
	}
		
	}
	
}
