package Ubuy.Save_Price_Of_API_URL;

import java.util.ArrayList;
import java.util.List;

public class fetch_browser_data 
{    
    static int first_index = 0;
    
    static int last_index = 0;
    
    static int last_https_index = 0;
    
    static String detail_url = null;
    
    public static List<String> all_urls = new ArrayList<>();
	
	public static void get_url()
	{
		String html =  Browser.js.executeScript("return document.body.innerHTML;").toString();
		
		last_https_index = html.lastIndexOf("\"]");
		
		while(first_index <= last_https_index)
		{
			first_index = html.indexOf("https:",last_index);
			
			last_index = html.indexOf("\",",last_index+1);
		    
		    try
		    {
		        detail_url = html.substring(first_index, last_index).replace("\\/", "/");
		
		        all_urls.add(detail_url);
		    }
		    
		    catch(Exception e)
		    {
		    	if(last_index == -1)
		    	{
		    		Index.data_stream.append("Total URL count is "+all_urls.size());
		    		
		    		System.out.println("All urls have been added to list.\r\n");
		    		
		    		System.out.println(all_urls);
		    	
		    	    break;
		        }
		
		    }

	    }
	}
}
