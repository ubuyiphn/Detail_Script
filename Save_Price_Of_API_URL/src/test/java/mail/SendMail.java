package mail;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class SendMail extends SetMailBody{
	@SuppressWarnings("static-access")
	public static void send_error_mail() throws MessagingException
	{
		SetMailBody body = new SetMailBody();
		Properties properties = System.getProperties();
		properties.put("mail.transport.protocol", protocol);
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		session = Session.getInstance(properties, new javax.mail.Authenticator() 
		{
		   protected PasswordAuthentication getPasswordAuthentication() 
		   {
			   return new PasswordAuthentication(username, password);
		   }
		});
		transport = session.getTransport();
		body.set_error_mail_body();
		try 
		{
			transport.connect(host, username, password);
			transport.send(message);
		} 
		catch (MessagingException mex) 
		{
			mex.printStackTrace();
		} finally 
		{
			transport.close();
		}

	
	
}
}

