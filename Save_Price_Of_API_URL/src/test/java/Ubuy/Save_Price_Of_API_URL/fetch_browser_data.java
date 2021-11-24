package Ubuy.Save_Price_Of_API_URL;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class fetch_browser_data 
{
    WebDriver driver;
    
    static int first_index = 0;
    
    static int last_index = 0;
    
    static int last_https_index = 0;
    
    static String detail_url = null;
    
    public static List<String> all_urls = new ArrayList<>();

    fetch_browser_data(WebDriver driver)
    {
    	this.driver = driver;
    }
	
	public static void get_url()
	{
		String html =  Browser.js.executeScript("return document.body.innerHTML;").toString();
		
		//System.out.println(html);
		
		last_https_index = html.lastIndexOf("\"]");
		
		//System.out.println(last_https_index);
		
		while(first_index <= last_https_index)
		{
			first_index = html.indexOf("https:",last_index);
			
			last_index = html.indexOf("\",",last_index+1);
		
		    //System.out.println(first_index);
		
		   // System.out.println(last_index);		
		    
		   // System.out.println(last_https_index);
		    
		    try
		    {
		    detail_url = html.substring(first_index, last_index).replace("\\/", "/");
		
		    all_urls.add(detail_url);
		
		   // System.out.println(detail_url);
		    }
		    
		    catch(Exception e)
		    {
		    	if(last_index == -1)
		    	{
		    	
		    	System.out.println("All urls have been added to list.");
		    	
		    	System.out.println(all_urls);
		    	
		    	break;
		        }
		
		    }

	    }
	}
}
