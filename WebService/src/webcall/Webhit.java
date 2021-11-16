package webcall;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Webhit extends PageLoadTime {
	public static WebDriver driver;
	static String mailurl; // to send url with mail for slow detail

	public static void main(String[] args)

			throws IOException, JSONException, org.json.simple.parser.ParseException, InterruptedException

	{
		pricesave();
	}

	public static void pricesave() throws IOException, InterruptedException {
		
		

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\my\\Pictures\\chromedriver\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// "E:\\library\\chromedriver_win32\\chromedriver.exe");
		// driver = new ChromeDriver();
		// driver.manage().window().maximize();

		// TO RUN IN HEADLESS CHROME BROWSER
		ChromeOptions op = new ChromeOptions();
		op.addArguments("window-size=1400,800");
		// op.addArguments("headless");
		op.addArguments("--blink-settings=imagesEnabled=false"); // to disable
																	// image
																	// load
		driver = new ChromeDriver(op);
		

		// To open multiple browser tabs
		for (int url_count = 0; url_count < 30; url_count++) {
			((JavascriptExecutor) driver).executeScript("window.open()");
		}

		// PASRSING JSON (getting data)
		int m = 0; // to skip calling the pageloadtime class 3 times to send
					// mail after 1 hr
		int url_count = 1;

		for (;;) {
			try {

				String urlString = "https://www.a.ubuy.com.kw/am/google_content_api/get_products_url_script.php?time=23234";
				URL url1 = new URL(urlString);

				HttpURLConnection con = (HttpURLConnection) url1.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();

				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				System.out.println(timeStamp); // to check the time taken to
												// load 120 urls

				System.out.println("Sending get request : " + url1);
				System.out.println("Response code : " + responseCode);
				// Reading response from input Stream
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String output;
				StringBuffer response = new StringBuffer();

				while ((output = in.readLine()) != null)

				{
					response.append(output);
				}

				in.close();
				System.out.println(response.toString());
				JSONParser parser = new JSONParser();
				try {
					Object obj = parser.parse(response.toString());
					JSONArray url_count_array = (JSONArray) obj;
					System.out.println(url_count_array.size());

					for (int url_count = 0; url_count < url_count_array.size(); url_count++) {
						System.gc();
						// System.out.println("The 1st element of url_count_array");
						ArrayList<String> tabs_count = new ArrayList<String>(driver.getWindowHandles());
						for (int tabs_count = 0; tabs_count < 30; tabs_count++) {
							int sum_url_tabs = url_count + tabs_count;

							driver.switchTo().window(tabs.get(tabs_count));
							String url0 = (String) url_count_array.get(sum_url_tabs);

							// to check if detail is still loading, proxy issue
							if (tabs_count == 3 || tabs_count == 6 || tabs_count == 9 || tabs_count == 12 || tabs_count == 15) {
								try {

									String text = driver
											.findElement(By
													.xpath("//*[@class='title h1 mb-2' or contains(text(),'Don�t worry...') or contains(@alt,'not-available')]"))
											.getText();
									System.out.println("LOADED");

								} catch (Exception e) {

									mailurl = driver.getCurrentUrl(); // to send
																		// url
																		// with
																		// mail
																		// for
																		// slow
																		// detail

									// To skip the first mail with about:blank
									// url
									if (mailurl.contains("about:blank") == false) {
										emailme(); // calling email method
										System.out.println("Slow Detail Email Sent");
									}

									else {
										System.out.println("blank url");
									}

								}

							}

							if (sum_url_tabs != 5) {
								driver.get(url0);
								System.out.println("url " + url_count + " - " + url0);
								url_count++;
							} else {

								m++;
								System.out.println(m);
								// to skip calling the pageloadtime class 4
								// times to send mail after 1 hr
								if (m == 5) {
									// Calling main method from PageLoadTime
									// class
									main(url0);
									m = 0;
								} else {

									driver.get(url0);
									System.out.println("url " + url_count + " - " + url0);
									url_count++;

								}
							}

						}
						url_count += 29;
					}
				} catch (Exception e) {
					e.printStackTrace();

					// driver.manage().timeouts().implicitlyWait(30,
					// TimeUnit.SECONDS);
					driver.quit();
					Thread.sleep(900000);
					pricesave();
				}
			} catch (Exception e) {
				e.printStackTrace();
				// driver.manage().timeouts().implicitlyWait(30,
				// TimeUnit.SECONDS);
				driver.quit();
				Thread.sleep(900000);
				pricesave();
			}
		}
	}

	// TO GET MAIL & SCREENSHOT THROUGH SMTP
	public static void emailme() {

		try {

			temp(new File("C:/Users/my/AppData/Local/Temp")); // calling temp
																// method to
																// delete temp
																// files

			TakesScreenshot screenshot = (TakesScreenshot) driver;

			@SuppressWarnings("unused")
			File src = screenshot.getScreenshotAs(OutputType.FILE);

			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(file, new File("F:\\AutomateUbuyScript\\WebService\\Screenshots\\screenshot.png"));
			System.out.println("Screenshot Taken");

			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath("F:\\AutomateUbuyScript\\WebService\\Screenshots\\screenshot.png");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Error");
			attachment.setName("Screen");
			attachment.getURL();

			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator("manayasam@gmail.com", "omitit123"));
			email.setSSLOnConnect(true);
			email.setFrom("manayasam@gmail.com");
			email.setSubject("Detail PROXY ISSUE");
			email.setMsg("DETAIL PAGE NOT LOADING/LOADING SLOW �" + mailurl);
			System.out.println(mailurl);
			email.addTo("shailendra.singh@ubuy.com");
			// email.addTo("om@ubuy.com");
			// email.addTo("akhil@ubuy.com");
			// email.addTo("ramesh.saini@ubuy.com");
			email.addTo("himanshu.khandelwal@ubuy.com");

			email.attach(attachment);
			email.send();

		}

		catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}

	// METHOD TO DELETE SYSTEM TEMP FILE
	public static void temp(File directoryPath) throws IOException {
		if (directoryPath != null && directoryPath.exists()) {

			if (directoryPath.isDirectory()) {
				File[] fileList = directoryPath.listFiles();
				for (int url_count = 0; url_count < fileList.length; url_count++) {
					temp(fileList[url_count]); // recursion to delete content of folder
										// inside folder
				}
				if (!"Temp".equalsIgnoreCase(directoryPath.getName())) { // not
																			// to
																			// delete
																			// the
																			// temp
																			// folder
																			// itself
					System.out.println("folder � delete " + directoryPath.getAbsolutePath());
					directoryPath.delete();
				}
			} else {
				System.out.println("file � delete " + directoryPath.getAbsolutePath());
				directoryPath.delete();
			}
		}
	}

}
