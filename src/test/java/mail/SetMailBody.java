package mail;

import java.io.File;
import javax.mail.*;
import javax.mail.internet.*;
import Ubuy.Save_Price_Of_API_URL.Static_Data;

public class SetMailBody extends SetPropertiesOfSSLEmail
 {
	public void set_error_mail_body() 
	 {
	 try 
	 {
		System.setProperty("mail.smtp.ssl.protocols", "TLSv1.3");
         
		message = new MimeMessage(session);
         
		message.setFrom(new InternetAddress(from));
         
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));            
		
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));           
            
        message.setSubject("Error on api detail page");

	    MimeBodyPart messagebodypart = new MimeBodyPart();
		messagebodypart.setText("Hello Team \nPlease have a look to Error.\nThis mail generated when detail page doesn't load.\n... ");

		MimeBodyPart attachmentpart = new MimeBodyPart();
		attachmentpart.attachFile(new File(Screenshot.screenshotpath));

		MimeBodyPart signaturepart = new MimeBodyPart();
		signaturepart.setText(".....\n\n\n\n\n Thank You & Regards\n Himanshu Khandelwal\n Quality Analyst\n Ubuy ");
			
		MimeBodyPart consoleoutput = new MimeBodyPart();
		consoleoutput.attachFile(Static_Data.console_output_file_path);
		
//		MimeBodyPart price_saved_urls_data = new MimeBodyPart();
//		price_saved_urls_data.attachFile(Index.price_saved_urls_file_path);
//			
//		MimeBodyPart out_of_stock_urls_data = new MimeBodyPart();
//		out_of_stock_urls_data.attachFile(Index.out_of_stock_urls_file_path);
//			
//		MimeBodyPart not_found_urls_data = new MimeBodyPart();
//		not_found_urls_data.attachFile(Index.not_found_urls_file_path);
//			
//		MimeBodyPart restricted_urls_data = new MimeBodyPart();
//		restricted_urls_data.attachFile(Index.restricted_urls_file_path);
//		
//		MimeBodyPart not_loaded_urls_data = new MimeBodyPart();
//		not_loaded_urls_data.attachFile(Index.not_loaded_urls_file_path);			

        Multipart multipart = new MimeMultipart();

		multipart.addBodyPart(messagebodypart);
		
		multipart.addBodyPart(attachmentpart);
		
		multipart.addBodyPart(signaturepart);
		
		multipart.addBodyPart(consoleoutput);
		
//		multipart.addBodyPart(price_saved_urls_data);
//		
//		multipart.addBodyPart(out_of_stock_urls_data);
//		
//		multipart.addBodyPart(not_found_urls_data);
//		
//		multipart.addBodyPart(restricted_urls_data);
//		
//		multipart.addBodyPart(not_loaded_urls_data);
			
		message.setContent(multipart);
	} 
	
	 catch (Exception e) 
	{
		e.printStackTrace();
	}
}
	
	public void set_data_mail_body() 
	 {
	 try 
	 {
		 System.setProperty("mail.smtp.ssl.protocols", "TLSv1.3");
         
		 message = new MimeMessage(session);
         
		 message.setFrom(new InternetAddress(from));
         
		 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
		 
		 message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
           
         message.setSubject("Check Data of api loaded urls");

		MimeBodyPart messagebodypart = new MimeBodyPart();
		messagebodypart.setText("Hello Team \nNow all urls have been hit.\nThis mail generated when all urls have been hit..\n... ");

//		MimeBodyPart attachmentpart = new MimeBodyPart();
//		attachmentpart.attachFile(new File(Screenshot.screenshotpath));

		MimeBodyPart signaturepart = new MimeBodyPart();
		signaturepart.setText(".....\n\n\n\n\n Thank You & Regards\n Himanshu Khandelwal\n Quality Analyst\n Ubuy ");
			
		MimeBodyPart consoleoutput = new MimeBodyPart();
		consoleoutput.attachFile(Static_Data.console_output_file_path);
		
		MimeBodyPart price_saved_urls_data = new MimeBodyPart();
		price_saved_urls_data.attachFile(Static_Data.price_saved_urls_file_path);
			
		MimeBodyPart out_of_stock_urls_data = new MimeBodyPart();
		out_of_stock_urls_data.attachFile(Static_Data.out_of_stock_urls_file_path);
			
		MimeBodyPart not_found_urls_data = new MimeBodyPart();
		not_found_urls_data.attachFile(Static_Data.not_found_urls_file_path);
			
		MimeBodyPart restricted_urls_data = new MimeBodyPart();
		restricted_urls_data.attachFile(Static_Data.restricted_urls_file_path);
			
		MimeBodyPart not_loaded_urls_data = new MimeBodyPart();
		not_loaded_urls_data.attachFile(Static_Data.not_loaded_urls_file_path);			

		Multipart multipart = new MimeMultipart();
	
		multipart.addBodyPart(messagebodypart);
		
//		multipart.addBodyPart(attachmentpart);
		
		multipart.addBodyPart(signaturepart);
		
		multipart.addBodyPart(consoleoutput);
		
		multipart.addBodyPart(price_saved_urls_data);
		
		multipart.addBodyPart(out_of_stock_urls_data);
		
		multipart.addBodyPart(not_found_urls_data);
		
		multipart.addBodyPart(restricted_urls_data);
		
		multipart.addBodyPart(not_loaded_urls_data);
			
		message.setContent(multipart);
	} 
    catch (Exception e) 
	{
		e.printStackTrace();
	}
	}
}