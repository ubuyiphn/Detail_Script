package Ubuy.Save_Price_Of_API_URL;

import java.net.MalformedURLException;

public class Main 
{
    public static void main(String[] args) throws InterruptedException, MalformedURLException
    {
    	Browser browser = new Browser();

    	browser.launch_chrome();
    	
    	browser.hit_api_url();
    	
    	fetch_browser_data.get_url();
    	
    	Browser.driver.get(fetch_browser_data.all_urls.get(0));
    	
    	browser.open_new_tabs(1,2);
    	
    	browser.close_tabs();
    	
    	browser.get_url_with_status();
    }
}
