package Ubuy.Save_Price_Of_API_URL;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public class Main 
{
    static WebDriver driver;
        
    public static void main(String[] args) throws InterruptedException, MalformedURLException
    {
    	Browser browser = new Browser(driver);

    	browser.launch_chrome();
    	
    	browser.hit_api_url();
    	
    	//Thread.sleep(2000);
    	
    	fetch_browser_data.get_url();
    	
    	browser.driver.get(fetch_browser_data.all_urls.get(0));
    	
    	browser.open_new_tabs();
    	
    	browser.close_tabs();
    		
    }
}
