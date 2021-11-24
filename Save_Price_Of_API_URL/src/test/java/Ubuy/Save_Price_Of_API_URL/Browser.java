package Ubuy.Save_Price_Of_API_URL;

import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser 
{
    WebDriver driver;
    
    public static JavascriptExecutor js;
    
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
	
	public void open_new_tabs() throws InterruptedException
	{
		int url_count = 1;
		
		while(url_count<=30)
		{
		
		    String url = fetch_browser_data.all_urls.get(url_count);
		
		    System.out.println(url);
		
		    js.executeScript("window.open()");
		    
		    url_count++;
		    
		}
	}
	
}
