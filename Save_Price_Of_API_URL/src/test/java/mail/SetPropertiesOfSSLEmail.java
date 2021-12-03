package mail;

import javax.mail.*;

public class SetPropertiesOfSSLEmail {
	public static String to = "rahul.sharma@ubuy.com";
	public static String from = "ubuyiphn@gmail.com";
	public static String host = "smtp.gmail.com";
	public static final String username = "ubuyiphn@gmail.com";
	public static final String password = "ubuy@123";
	public static Session session;
	public static Transport transport;
	public static Message message;
	public static int port = 465;
	public static String protocol = "smtp";
	
	
}