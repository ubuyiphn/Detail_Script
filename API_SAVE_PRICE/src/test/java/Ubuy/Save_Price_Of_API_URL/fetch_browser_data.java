package Ubuy.Save_Price_Of_API_URL;

import java.util.ArrayList;
import java.util.List;

public class fetch_browser_data 
{     
    static String detail_url = null;
    
    public static List<String> all_urls = new ArrayList<>();
	
	public static void get_url()
	{
		int first_index = 0;
		    
		int last_index = 0;
		    
		int last_https_index = 0;
		
		System.out.println("Trying to get urls.");
		
		String html =  Browser.js.executeScript("return document.body.innerHTML;").toString();
		
		last_https_index = html.lastIndexOf("\"]");
		
		System.out.println("last https index is "+last_https_index);
		
		while(first_index <= last_https_index)
		{	        
			first_index = html.indexOf("https:",last_index);
			
			last_index = html.indexOf("\",",last_index+1);
			
		//	System.out.println(first_index+" indexes "+last_index);
		    			
			if(last_index == -1)
	        {
	            Index.console_output_stream.append("\r\n\r\n\r\nTotal URL count is "+all_urls.size()+"\r\n\r\n\r\n");
	            	
	            System.out.println("All urls have been added to list.\r\n");
	            	
	           // System.out.println(all_urls);
	            
	            break;
	        }
	        
	        else
	        {
		        detail_url = html.substring(first_index, last_index).replace("\\/", "/");
		        
		        all_urls.add(detail_url);
		        
		       // System.out.println(detail_url);
	        }
	    }
	}
}
