package Ubuy.Save_Price_Of_API_URL;

public class test
{
	public static void main(String[] args) throws InterruptedException
    {
        test1 t1 = new test1();
	    
	    t1.start();
	    
    	test2 t2 = new test2();
    	
    	test2.sleep(10000);
	    
	    t2.start();
	    
    }
}

class test1 extends Thread
{
	public void run()
    {
    	Browser browser = new Browser();

    	browser.launch_chrome();
    	
    	browser.hit_api_url();
    	
    	fetch_browser_data.get_url();
    	
        Browser.driver.get(fetch_browser_data.all_urls.get(0));
        
        try
        {
      	    browser.open_new_tabs(1,10);
    	
    	    browser.close_tabs();
        }
        
        catch(Exception e)
        {
        	System.out.println(e);
        }
    	
    	browser.get_url_with_status();
    }
}

class test2 extends Thread
{	
	public void run()
	{
		Browser browser = new Browser();

    	browser.launch_chrome();
    	
    	 try
         {
     	    browser.open_new_tabs(11,20);
     	
     	    browser.close_tabs();
         }
         
         catch(Exception e)
         {
         	System.out.println(e);
         }
     	
     	browser.get_url_with_status();
	}
}
