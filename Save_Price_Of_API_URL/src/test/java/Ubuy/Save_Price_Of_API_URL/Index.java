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
	
	public static File data_file_path = new File(System.getProperty("user.dir")+"/Data.txt");
	
	public static PrintStream data_stream;
	
	public static PrintStream console_output_stream;
	
	public static void main(String[] args) throws InterruptedException, IOException, MessagingException
    {
		Browser browser = new Browser();
		
		data_file_path.createNewFile();
		
		console_output_file_path.createNewFile();
		
		data_stream = new PrintStream(new FileOutputStream(data_file_path));
		
		console_output_stream = new PrintStream(new FileOutputStream(console_output_file_path));
		
		System.setOut(console_output_stream);
		
		System.setErr(console_output_stream);
		
		try
		{
			data_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
			
			while(true)
		    {
    	        browser.launch_chrome();
    	
    	        browser.hit_api_url();
    	
    	        fetch_browser_data.get_url();
    	
    	        Thread.sleep(3000);
    	
    	        Browser.driver.close();
    	
    	        int min_limit = 0;
    	
    	        int max_limit = min_limit+9;
        
    	        while(max_limit <= fetch_browser_data.all_urls.size())
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
    	    
    	            max_limit = min_limit + 9;
    	        }
    	    
    	        browser.get_url_with_status();
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

