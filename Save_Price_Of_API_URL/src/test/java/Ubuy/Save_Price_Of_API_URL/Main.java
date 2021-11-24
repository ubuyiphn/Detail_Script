package Ubuy.Save_Price_Of_API_URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Main 
{
    static WebDriver driver;
        
    public static void main(String[] args) throws InterruptedException
    {
    	Browser browser = new Browser(driver);

    	browser.launch_chrome();
    	
    	browser.hit_api_url();
    	
    	Thread.sleep(2000);
    	
    	fetch_browser_data.get_url();
    	
    	browser.driver.get(fetch_browser_data.all_urls.get(0));
    		
    }
}
