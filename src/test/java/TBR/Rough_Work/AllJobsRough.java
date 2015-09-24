package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class AllJobsRough extends TestBase{
	
	@Test
	public void allJobsRough() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		
		//(WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated("nowShowingTextId")));
		//click on jobs and all jobs
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		//show jobs 10 per page
		/*Select perPage = new Select(getObject("showJobsPerPageX"));
	    perPage.selectByValue("10");
	   
	    if(driver.getPageSource().contains("Now Showing 1 to 10 out of")){
	    	System.out.println("success");
	    }else{
	    	System.out.println("fail");
	    }
	    */
	   // Thread.sleep(8000);
	    //show jobs 25 per page
	   // waitForElement(10, "nowShowingTextX"); 	 
	  		Select perPage25 = new Select(getObject("showJobsPerPageX"));
	  	    perPage25.selectByValue("25");
	  	    getObject("showJobsPerPageX").sendKeys(Keys.ENTER);
	  	    
	  	    String aa = "html/body/div[2]/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[";
	  	    String bb = "]/td[1]";
	  	    
	  	    for(int i=1;i<=25;i++){
	  	    	String abc = driver.findElement(By.xpath(aa+i+bb)).getText();
	  	    	System.out.println(+i);
	  	    	System.out.println(abc);
	  	    	
	  	    	//Thread.sleep(5000);
	  	    	
	  	    	
	  	    }
	  	 
	  	    
	  	    
	  	    WebElement scroll = getObject("nowShowingTextX");
	  	    scroll.sendKeys(Keys.PAGE_DOWN);
	  	  waitForElement(10, "nowShowingTextX");
	  	  String a = getObject("nowShowingTextX").getText();
	  	  System.out.println(a);
	  	 if(driver.getPageSource().contains("Now Showing 1 to 25 out of")){
	  	    	System.out.println("success");
	  	    }else{
	  	    	System.out.println("fail");
	  	    }
	  	/*//show jobs 50 per page
			Select perPage50 = new Select(getObject("showJobsPerPageX"));
		    perPage50.selectByValue("50");
		    getObject("showJobsPerPageX").sendKeys(Keys.ENTER);
		    WebElement scroll50 = getObject("nowShowingTextX");
	  	    scroll50.sendKeys(Keys.PAGE_DOWN);
	  	  String b = getObject("nowShowingTextX").getText();
	  	  System.out.println(b);
	  	    waitForElement(10, "nowShowingTextX");
		    if(driver.getPageSource().contains("Now Showing 1 to 50 out of")){
		    	System.out.println("success");
		    }else{
		    	System.out.println("fail");
		    }
		  //show jobs 100 per page
			Select perPage100 = new Select(getObject("showJobsPerPageX"));
		    perPage100.selectByValue("100");
		    getObject("showJobsPerPageX").sendKeys(Keys.ENTER);
		    WebElement scroll100 = getObject("nowShowingTextX");
	  	    scroll50.sendKeys(Keys.PAGE_DOWN);
	  	    waitForElement(10, "nowShowingTextX");
	  	  String c = getObject("nowShowingTextX").getText();
	  	  System.out.println(c);
		    if(driver.getPageSource().contains("Now Showing 1 to 100 out of")){
		    	System.out.println("success");
		    }else{
		    	System.out.println("fail");
		    }*/
	  	    
	
	    
	    
	    
		
	}

}
