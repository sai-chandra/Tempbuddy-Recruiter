package TBR.Rough_Work;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class CheckResponseUrlAddedJobRough {
	
	        private static final String SERVER_NAME = "http://staging.tempbuddy.com";
	        private static String responseValue = "";
	        private static WebDriver driver = null;
		    public static void main(String[] args) throws Exception {
			
			CloseableHttpClient httpclient = HttpClients.custom().build();
			try {
	        HttpGet httpget = new HttpGet(SERVER_NAME);
	        CloseableHttpResponse response1 = httpclient.execute(httpget);
	        String CSRFName = "";
	        try {
	        CSRFName = getCSFRTokenFromResponse(response1);
	        } finally {
                response1.close();
            }

            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI(SERVER_NAME + "/login_check")).addParameter("_csrf_token", CSRFName)
                    .addParameter("_username", "Sai_C")
                    .addParameter("_remember_me", "on")
                    .addParameter("_submit", "Log In")
                    .addParameter("_password", "qwerty")
                    .build();
            CloseableHttpResponse response2 = httpclient.execute(login);
            try {
            HttpUriRequest web = RequestBuilder.post().setUri(new URI(SERVER_NAME + "/web/rec/add/job/"))
            		                    .addParameter("address", "Parnell Street,dublin 1")
                         				.addParameter("details[billingType]", "HOURLY")
                         				.addParameter("details[billrate]", "19.41")
                         				.addParameter("details[bookingfee]", "0")
                         				.addParameter("details[candid]", "")
                         				.addParameter("details[cat_db_0]", "TB_6421")
                         				.addParameter("details[cat_id_0]", "20")
                         				.addParameter("details[categories]", "1")
                         				.addParameter("details[clientaddress]", "2nd floor, 40 Dame street, Dublin 2")
                         				.addParameter("details[clientid]", "430")
                         				.addParameter("details[clientname]", "ClientTemp2")
                         				.addParameter("details[description]", "")
                         				.addParameter("details[externalRef]", "123")
                         				.addParameter("details[feefrequency]", "hourly")
                         				.addParameter("details[fixPaymentIn]", "0")
                         				.addParameter("details[fixPaymentOut]", "0")
                         				.addParameter("details[lat]", "53.34814")
                         				.addParameter("details[lng]", "-6.2574")
                         				.addParameter("details[locationID]", "12502278-3055-11e5-bc7a-e69845920f91")
                         				.addParameter("details[location]", "18")
                         				.addParameter("details[margin]", "0.05")
                         				.addParameter("details[maxPositions]", "1")
                         				.addParameter("details[minimumHoursPerShift]", "0")
                         				.addParameter("details[overwritePayrateWithCand]", "1")
                         				.addParameter("details[payrate]", "15.00")
                         				.addParameter("details[pool]", "")
                         				.addParameter("details[radius]", "")
                         				.addParameter("details[startDateDays]", "1")
                         				.addParameter("details[startDateFinish]", "")
                         				.addParameter("details[startDateHours]", "4")
                         				.addParameter("details[startDatePattern]", ",u1,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,{\"weeks\":1,\"shifts\": [{\"startHour\": \"04:30\", \"endHour\": \"08:30\", \"dateStart\": \"2015-8-16\", \"dateEnd\": \"2015-8-16\", \"weekDay\": \"7\" }] }")
                         				.addParameter("details[startDateStartDay]", "2015-8-16")
                         				.addParameter("details[startDateStartHour]", "04:30")
                         				.addParameter("details[startDateWeeks]", "1")
                         				.addParameter("details[startDate]", "Starting on 2015-8-16 at 04:30")
                         				.addParameter("details[strictPaymentOut]", "0")
                         				.addParameter("details[strictPayment]", "0")
                         				.addParameter("details[taxes1]", "0.1075")
                         				.addParameter("details[taxes2]", "0")
                         				.addParameter("details[taxes3]", "0")
                         				.addParameter("details[taxes4]", "0")
                         				.addParameter("details[taxes5]", "0")
                         				.addParameter("details[taxes6]", "0")
                         				.addParameter("details[taxes7]", "0")
                         				.addParameter("details[taxesHolidays]", "0.125")
                         				.addParameter("details[taxesNumHolidays]", "29")
                         				.addParameter("details[taxes]", "0.2325")
                         				.addParameter("details[templateID]", "0")
                         				.addParameter("details[title]", "Testing Job")
                         				.addParameter("details[total]", "0")
                         				.build();
                 CloseableHttpResponse response3 = httpclient.execute(web);
                 readResponse(response3);
                 webDriverCheck();
                 } finally {
                 response2.close();
                 }
                 } finally {
                 httpclient.close();
                 }
                 }
                 //method for extracting the csrf token value from the xml content 
                 public static String getCSFRTokenFromResponse(CloseableHttpResponse response) throws Exception {
    	         String token = null;
    	         BufferedReader reader = null;
    	         String line = null;
    	         HttpEntity entity = response.getEntity();

    	         reader = new BufferedReader(new InputStreamReader(entity.getContent())); 
    	         while ((line = reader.readLine()) != null) {
    			 if (line.indexOf("_csrf_token") > 0) {
    		     System.out.println("CSFR Line: " + line);
     			 int startIndex = line.indexOf("value=\"");
     			 int endIndex = line.indexOf("\" />");
     			 token = line.substring(startIndex + 7, endIndex);
     			 //System.out.println("CSFR Line: " + token);
     			 }
     	         //content += line;
     	         }
     	         // ensure response is fully consumed
     	         //EntityUtils.consume(entity);
     	         //System.out.println("response content is as follows:" +content);
     	         return token;
                 }
                 //method for reading the response
 	             public static String readResponse(CloseableHttpResponse response) 
 	             throws Exception {
 	             BufferedReader reader = null;
 	             String content = "";
 	             String line = null;
 	             HttpEntity entity = response.getEntity();

 	             reader = new BufferedReader(new InputStreamReader(entity.getContent())); 
 	             while ((line = reader.readLine()) != null) {
 	             content += line;
 	             System.out.println(line);	    }
 	             // ensure response is fully consumed
 	             EntityUtils.consume(entity);
 	             //prints the post repsonse body content
 	             responseValue = content;
 	             System.out.println("response content is as follows:" +content);
 	             return content;
    			 }
 	             //method to check whether the job is created successfully
 	             public static String webDriverCheck(){
 	             String JobUrlFirstPart = "http://staging.tempbuddy.com/web/rec/displayJob/";
 	             String jobIdvalue = responseValue;
 	             String jobUrlLastPart = "/";
 	             driver = new FirefoxDriver();
 	             driver.manage().window().maximize();
 	             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 	             driver.get("http://staging.tempbuddy.com");
 	             driver.findElement(By.id("username")).sendKeys("Sai_C");
 	             driver.findElement(By.id("password")).sendKeys("qwerty");
 	             driver.findElement(By.id("_submit")).click();
 	             driver.get(JobUrlFirstPart+jobIdvalue+jobUrlLastPart);
 	             String a = driver.findElement(By.id("jobTitle")).getAttribute("value");
 	             System.out.println("job title saved is "+a);
 	             //checks the job by its jobtitle
 	             Assert.assertEquals(a, "Testing Job");
 	             System.out.println("Success");
 	             return null;
 	            	 
 	            	 
 	             }
		}

