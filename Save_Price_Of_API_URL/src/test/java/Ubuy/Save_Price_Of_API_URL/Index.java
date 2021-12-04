package Ubuy.Save_Price_Of_API_URL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.mail.MessagingException;
import mail.Screenshot;
import mail.SendMail;

public class Index
{
	public static File console_output_file_path = new File(System.getProperty("user.dir")+"/Console-Output.txt");
	
	public static File price_saved_urls_file_path = new File(System.getProperty("user.dir")+"/price-saved-urls.txt");
	
	public static File out_of_stock_urls_file_path = new File(System.getProperty("user.dir")+"/out-of-stock-urls.txt");
	
	public static File not_found_urls_file_path = new File(System.getProperty("user.dir")+"/not-found-urls.txt");
	
	public static File restricted_urls_file_path = new File(System.getProperty("user.dir")+"/restricted-urls.txt");
	
	public static File not_loaded_urls_file_path = new File(System.getProperty("user.dir")+"/not-loaded-urls.txt");
	
	public static PrintStream price_saved_urls_file_stream;
	
	public static PrintStream out_of_stock_urls_file_stream;
	
	public static PrintStream not_found_urls_file_stream;
	
	public static PrintStream restricted_urls_file_stream;
	
	public static PrintStream not_loaded_urls_file_stream;
	
	public static PrintStream console_output_stream;
	
	public static void main(String[] args) throws InterruptedException, IOException, MessagingException
    {
		Browser browser = new Browser();
		
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
		
		//System.setOut(console_output_stream);
		
		//System.setErr(console_output_stream);
		
		try
		{
			price_saved_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
			
			out_of_stock_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
			
			not_found_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
			
			restricted_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
			
			not_loaded_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
			
			while(true)
		    {
    	        browser.launch_chrome();
    	
    	        browser.hit_api_url();
    	
    	        fetch_browser_data.get_url();
    	
    	        Thread.sleep(3000);
    	
    	        Browser.driver.close();
    	
    	        int min_limit = 0;
    	
    	        int max_limit = min_limit+2;
        
    	        while(min_limit <= 3/*fetch_browser_data.all_urls.size()*/)
    	        {
             
    	        	try
                    {
                    	browser.launch_chrome();
            	
                    	browser.open_new_tabs(min_limit,max_limit);
    	
    	                browser.close_tabs();
                    }
        
                    catch(Exception e)
                    {
                    	System.out.println(e);
                    }
    	
    	            min_limit = max_limit + 1;
    	    
    	            max_limit = min_limit + 2;
    	        }
    	    
    	        browser.get_url_with_status();
    	        
                System.out.println("Loop is completed and mail is going to sent.");
    	        
    	        SendMail.send_data_mail();
            }
        }
	
		catch(Exception e)
		{
			System.out.println(e);
			
			Screenshot.takescreenshot();
			
			SendMail.send_error_mail();
		}
    }
}

