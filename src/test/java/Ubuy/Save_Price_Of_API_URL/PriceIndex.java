package Ubuy.Save_Price_Of_API_URL;

import java.io.IOException;
import javax.mail.MessagingException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import mail.Screenshot;
import mail.SendMail;

public class PriceIndex {
	public static void main(String[] args) throws InterruptedException, IOException, MessagingException {
		Browser browser = new Browser();

		while (true) 
		{
			Static_Data.Static_Data();
			
			//Chrome Launched 
			WebDriverManager.chromedriver().setup();
			final ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
//			options.addArguments("--no-sandbox");
			final WebDriver driver = new ChromeDriver(options);
	    	driver.manage().window().maximize();
	    	
	    	//Created object of JavascriptExecutor
	    	final JavascriptExecutor js = (JavascriptExecutor) driver;
	    	
			try 
			{
				//Hit api url
				browser.hitAPIURL(driver);
				System.out.println("browser launched for api url.");

				//Fetched all urls and saved into List
				fetch_browser_data.get_url(js);
				
				//All urls are going to hit one by one and going to close one by one
				System.out.println("detail page browser going to hit.");
				fetch_browser_data.hitURL(driver,js);
				browser.close_tabs(driver);

				//Data displaying on console outpus
				System.out.println("\r\n\r\nSaved price url count is " + Browser.price_available_url.size());
				System.out.println("Not refreshed temp url count is " + Browser.not_refreshed_temp_url.size());
				System.out.println("Not refreshed url count is " + Browser.not_refreshed_url.size());
				System.out.println("not found url count is " + Browser.not_found_page_url.size());
				System.out.println("out of stock url count is " + Browser.out_of_stock_url.size());
				System.out.println("Restricted url count is " + Browser.restricted_product_url.size() + "\r\n\r\n");
				System.out.println("Non refreshed url function started...");

				//First time non-loaded urls are going to hit again one by one and going to close one by one
				
				if(Browser.not_refreshed_temp_url.size()!=0)
				{
					System.out.println("driver going into this ..."+Browser.not_refreshed_temp_url.size());
					WebDriverManager.chromedriver().setup();
					final ChromeOptions options1 = new ChromeOptions();
					options1.addArguments("headless");
//					options1.addArguments("--no-sandbox");
					final WebDriver driver1 = new ChromeDriver(options1);
			    	driver1.manage().window().maximize();
			    	final JavascriptExecutor js1 = (JavascriptExecutor) driver1;
					fetch_browser_data.hitNonLoadedURLs(driver1,js1);
					browser.close_non_refreshed_tabs(driver1);
				}
				System.out.println("Not refreshed url remaining are " + Browser.not_refreshed_url.size());
				browser.get_url_with_status();

				//Now all urls have been hit and mail is going.
				System.out.println("Loop is completed and mail is going to sent.");
				SendMail.send_data_mail();
			} 
			catch (Exception e) 
			{
				System.out.println(e);

				try 
				{
					//Screenshot will be taken and mail is going to sent
					Screenshot.takescreenshot(driver);
					SendMail.send_error_mail();
					Browser.price_available_url.clear();
					Browser.not_refreshed_temp_url.clear();
					Browser.not_refreshed_url.clear();
					Browser.not_found_page_url.clear();
					Browser.out_of_stock_url.clear();
					Browser.restricted_product_url.clear();
					fetch_browser_data.all_urls.clear();
					driver.quit();
				} 
				catch (Exception mail) 
				{
					System.out.println("Couldn't take screenshot.");

					System.out.println(mail);

					//All .txt files data is going to clear
					Browser.price_available_url.clear();
					Browser.not_refreshed_temp_url.clear();
					Browser.not_refreshed_url.clear();
					Browser.not_found_page_url.clear();
					Browser.out_of_stock_url.clear();
					Browser.restricted_product_url.clear();
					fetch_browser_data.all_urls.clear();

					driver.quit();
				}
			}
			System.gc();
		}

	}
}
