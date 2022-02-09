package Ubuy.Save_Price_Of_API_URL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.mail.MessagingException;
import mail.Screenshot;
import mail.SendMail;

public class PriceIndex
{
	public static File console_output_file_path;
	
	public static File price_saved_urls_file_path;
	
	public static File out_of_stock_urls_file_path;
	
	public static File not_found_urls_file_path;
	
	public static File restricted_urls_file_path;
	
	public static File not_loaded_urls_file_path;
	
	public static PrintStream price_saved_urls_file_stream;
	
	public static PrintStream out_of_stock_urls_file_stream;
	
	public static PrintStream not_found_urls_file_stream;
	
	public static PrintStream restricted_urls_file_stream;
	
	public static PrintStream not_loaded_urls_file_stream;
	
	public static PrintStream console_output_stream;
	
	public static void main(String[] args) throws InterruptedException, IOException, MessagingException
    {
		Browser browser = new Browser();
		
		while(true)
	    {
			try
			{
				System.gc();
				
				console_output_file_path = new File(System.getProperty("user.dir")+"/console/console-output.txt");
                 
				price_saved_urls_file_path = new File(System.getProperty("user.dir")+"/price-saved/price-saved-urls.txt");
				
				out_of_stock_urls_file_path = new File(System.getProperty("user.dir")+"/out-of-stock/out-of-stock-urls.txt");
				
				not_found_urls_file_path = new File(System.getProperty("user.dir")+"/not-found/not-found-urls.txt");
				
				restricted_urls_file_path = new File(System.getProperty("user.dir")+"/restricted/restricted-urls.txt");
				
				not_loaded_urls_file_path = new File(System.getProperty("user.dir")+"/not-loaded/not-loaded-temp_urls.txt");
				
				price_saved_urls_file_path.createNewFile();
                 
                out_of_stock_urls_file_path.createNewFile();
                 
                not_found_urls_file_path.createNewFile();
                 
                restricted_urls_file_path.createNewFile();
                 
                not_loaded_urls_file_path.createNewFile();
                 
                console_output_file_path.createNewFile();
                 
                price_saved_urls_file_stream = new PrintStream(new FileOutputStream(price_saved_urls_file_path));
                 
                out_of_stock_urls_file_stream = new PrintStream(new FileOutputStream(out_of_stock_urls_file_path));
                 
                not_found_urls_file_stream = new PrintStream(new FileOutputStream(not_found_urls_file_path));
                 
                restricted_urls_file_stream = new PrintStream(new FileOutputStream(restricted_urls_file_path));
                 
                not_loaded_urls_file_stream = new PrintStream(new FileOutputStream(not_loaded_urls_file_path));
                 
                console_output_stream = new PrintStream(new FileOutputStream(console_output_file_path));
                 
                System.setOut(console_output_stream);
                 
                System.setErr(console_output_stream);
                 
                price_saved_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
                 	
                out_of_stock_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
                 	
                not_found_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
                 	
                restricted_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
                 	
                not_loaded_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
			
                System.out.println("api urls browser going to launched.");
  	        	
  	        	browser.launch_chrome();
  	        	
  	        	System.out.println("browser launched for api url.");
            
                browser.hit_api_url();
                 
                System.out.println("api url has been hit.");
                 
                Thread.sleep(2000);
            
                fetch_browser_data.get_url();
            
                Thread.sleep(3000);
            
                Browser.driver.close();
            
                int min_limit = 0;
            
                int max_limit = fetch_browser_data.all_urls.size();
            
                while(min_limit <= fetch_browser_data.all_urls.size())
    	        {
                    System.gc();
    	        	 
                   	System.out.println(min_limit+"detail page browser going to hit."+max_limit);
    	        		
    	        	browser.launch_chrome();
            	
                   	browser.open_new_tabs(min_limit,max_limit);
    	
    	            browser.close_tabs();
    	                
    	            System.out.println("\r\n\r\nSaved price url count is "+Browser.price_available_url.size());
    	                
    	            System.out.println("Not refreshed temp url count is "+Browser.not_refreshed_temp_url.size());
    	                
    	            System.out.println("Not refreshed url count is "+Browser.not_refreshed_url.size());
    	                
    	            System.out.println("not found url count is "+Browser.not_found_page_url.size());
    	                
    	            System.out.println("out of stock url count is "+Browser.out_of_stock_url.size());
    	                
    	            System.out.println("Restricted url count is "+Browser.restricted_product_url.size()+"\r\n\r\n");
    	                
    	            System.out.println("max limit"+max_limit);
    	                
    	            if(max_limit == fetch_browser_data.all_urls.size() )
    	             {
    	              	 System.out.println("Non refreshed url function started...");
    	                	
    	              	 browser.hit_non_refreshed_urls();
    	                	
    	               	 browser.close_non_refreshed_tabs();
    	                	 
    	               	 System.out.println("Not refreshed url remaining are "+Browser.not_refreshed_url.size());
    	             }
               
    	           min_limit = max_limit + 1;
    	    
    	           max_limit = min_limit + 29;
    	       }
    	    
    	        browser.get_url_with_status();
    	        
                System.out.println("Loop is completed and mail is going to sent.");
    	        
    	        SendMail.send_data_mail();
            }
				catch(Exception e)
				{
					System.out.println(e);
					
					try
					{
					    Screenshot.takescreenshot();
					
					    SendMail.send_error_mail();
					}
					catch(Exception mail)
					{
						System.out.println("Couldn't take screenshot.");
						
						System.out.println(mail);
						
						Browser.price_available_url.clear();
						
						Browser.not_refreshed_temp_url.clear();
				        
						Browser.not_refreshed_url.clear();
				        
						Browser.not_found_page_url.clear();
				        
						Browser.out_of_stock_url.clear();
				        
						Browser.restricted_product_url.clear();
				        
				        fetch_browser_data.all_urls.clear();
						
						Browser.driver.quit();
					}
				}
			System.gc();
        }
	
		
    }
}

