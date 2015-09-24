package TBR.Rough_Work;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class JustRough extends TestBase{
	private static int statuscode;
	@Test
	public void allLinksJobsspecific() throws IOException, InterruptedException{
		initialize();
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	login_Valid();
	getObject("jobsLinkX").click();
	getObject("allJobsX").click();
	Select perPage100 = new Select(getObject("showJobsPerPageX"));
    perPage100.selectByValue("100");
    Thread.sleep(10000);
   // List element = new ArrayList();
   // element = driver.findElements(By.cssSelector(".JobCell.sorting_3"));
    
    List<WebElement> money = driver.findElements(By.cssSelector(".JobCell.sorting_3"));
    money.addAll(driver.findElements(By.tagName("a")));
    
    for(int i =0;i<money.size();i++){
    	//System.out.println(abc);
    	if(!(money.get(i).getAttribute("href")==null)&& !(money.get(i).getAttribute("href").equals(""))){
        	if(money.get(i).getAttribute("href").contains("http")){
        		//find http status code
        		statuscode = getResponseCode(money.get(i).getAttribute("href").trim());
        		System.out.println("no of links are "+i+", "+money.get(i).getAttribute("href")  +statuscode);
        		//check broken link
        		if(statuscode == 404){
        			System.out.println("Broken of Link "+i+" "+money.get(i).getAttribute("href"));
        			
        		}
        	}
        	}
        }
    	
    	driver.close();	
    	}
    	
    	public static int getResponseCode(String urlString) throws IOException{
    		URL  u = new URL(urlString);
    		HttpURLConnection huc = (HttpURLConnection)u.openConnection();
    		huc.setRequestMethod("GET");
    		huc.connect();
    		return huc.getResponseCode();
    		
    	}

    
    	
    
    //System.out.println(element);
    //Thread.sleep(10000);
    //List<WebElement> links = driver.findElements(By.tagName("a"));

}
