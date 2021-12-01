package mail;

import java.io.File;
import javax.mail.*;
import javax.mail.internet.*;

import Ubuy.Save_Price_Of_API_URL.Index;

public class SetMailBody extends SetPropertiesOfSSLEmail
 {
	public void set_error_mail_body() 
	 {
	 try {
		 System.setProperty("mail.smtp.ssl.protocols", "TLSv1.3");
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to,cc));            
            
            message.setSubject("Error on api detail page");

			MimeBodyPart messagebodypart = new MimeBodyPart();
			messagebodypart.setText("Hello Team \nPlease have a look to Error.\nThis mail generated when detail page doesn't load.\n... ");

			MimeBodyPart attachmentpart = new MimeBodyPart();
			attachmentpart.attachFile(new File(Screenshot.screenshotpath));

			MimeBodyPart signaturepart = new MimeBodyPart();
			signaturepart.setText(".....\n\n\n\n\n Thank You & Regards\n Himanshu Khandelwal\n Quality Analyst\n Ubuy ");
			
			MimeBodyPart consoleoutput = new MimeBodyPart();
			consoleoutput.attachFile(Index.console_output_file_path);
			
			MimeBodyPart speed_data = new MimeBodyPart();
			speed_data.attachFile(Index.data_file_path);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagebodypart);
			multipart.addBodyPart(attachmentpart);
			multipart.addBodyPart(signaturepart);
			multipart.addBodyPart(consoleoutput);
			multipart.addBodyPart(speed_data);
			
			message.setContent(multipart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}