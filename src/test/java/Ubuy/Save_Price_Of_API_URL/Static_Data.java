package Ubuy.Save_Price_Of_API_URL;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Static_Data 
{
	public static File console_output_file_path = new File(System.getProperty("user.dir")+"/console/console-output.txt");
	
	public static File price_saved_urls_file_path = new File(System.getProperty("user.dir")+"/price-saved/price-saved-urls.txt");
	
	public static File out_of_stock_urls_file_path = new File(System.getProperty("user.dir")+"/out-of-stock/out-of-stock-urls.txt");
	
	public static File not_found_urls_file_path = new File(System.getProperty("user.dir")+"/not-found/not-found-urls.txt");
	
	public static File restricted_urls_file_path = new File(System.getProperty("user.dir")+"/restricted/restricted-urls.txt");
	
	public static File not_loaded_urls_file_path = new File(System.getProperty("user.dir")+"/not-loaded/not-loaded-temp_urls.txt");
	
	public static PrintStream price_saved_urls_file_stream;
	
	public static PrintStream out_of_stock_urls_file_stream;
	
	public static PrintStream not_found_urls_file_stream;
	
	public static PrintStream restricted_urls_file_stream;
	
	public static PrintStream not_loaded_urls_file_stream;
	
	public static PrintStream console_output_stream;
	
	public static void Static_Data() throws IOException
	{
		price_saved_urls_file_stream = new PrintStream(price_saved_urls_file_path);
    
		out_of_stock_urls_file_stream = new PrintStream(out_of_stock_urls_file_path);
     
		not_found_urls_file_stream = new PrintStream(not_found_urls_file_path);
     
		restricted_urls_file_stream = new PrintStream(restricted_urls_file_path);
     
		not_loaded_urls_file_stream = new PrintStream(not_loaded_urls_file_path);
		
		console_output_stream = new PrintStream(console_output_file_path);
		
		price_saved_urls_file_path.createNewFile();
        
		out_of_stock_urls_file_path.createNewFile();
         
		not_found_urls_file_path.createNewFile();
         
		restricted_urls_file_path.createNewFile();
         
		not_loaded_urls_file_path.createNewFile();
         
		console_output_file_path.createNewFile();
		
//		System.setOut(console_output_stream);
//        
//        System.setErr(console_output_stream);
        
        System.out.println("Script is going to start.");
         
       price_saved_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
         	
       out_of_stock_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
         	
       not_found_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
         	
       restricted_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
         	
       not_loaded_urls_file_stream.append("Script start date and time is "+Browser.print_Current_Date_And_Time()+"\r\n");
       
       System.out.println("Script is going to start.");
	}
	
}
