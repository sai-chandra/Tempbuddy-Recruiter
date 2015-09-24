package TBR.Rough_Work;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class DayBookRough extends TestBase{
	
	@Test
	public void dayBookRough() throws IOException, InterruptedException, AWTException{
		 initialize();
	   	  openBrowser();
	   	  driver.get(CONFIG.getProperty("testSiteName"));
	   	  login_Valid();
		
	   	  
	      Set<String> handles =  driver.getWindowHandles();
	      System.out.println(handles.size());
	      Iterator<String> iterate = handles.iterator();
	      Thread.sleep(5000);
	      String dayBookNumber = getObjectValue("dayBookBullsEyeStatsX");
	      Thread.sleep(4000);
	   	  System.out.println(dayBookNumber);
	   	  getObject("dayBookBullsEyeX").click();
		  getObjectById("dayBookExportPdfId").click();
	   	  handles = driver.getWindowHandles();
	   	  iterate = handles.iterator();
	      String firstWindow = iterate.next();
	  	  String SecondWindow = iterate.next();
	   	
	   	 
	   
	  
	   	  driver.switchTo().window(SecondWindow);
	   	 //waitForElementClickableId(15, "dayBookExportPdfDownloadId");
	   	  
	   	  System.out.println(driver.getCurrentUrl());
	   	  driver.findElement(By.xpath("//*[@id='download']")).click();
	     Thread.sleep(4000);
	   
	     Robot rob = new Robot();
	   	  rob.keyPress(KeyEvent.VK_ENTER);
	   	  rob.keyRelease(KeyEvent.VK_ENTER);
	   	  rob.keyPress(KeyEvent.VK_ENTER);
	   	  rob.keyRelease(KeyEvent.VK_ENTER);
	   	  rob.keyPress(KeyEvent.VK_ENTER);
	   	  rob.keyRelease(KeyEvent.VK_ENTER);
	   	  rob.keyPress(KeyEvent.VK_ENTER);
	   	  rob.keyRelease(KeyEvent.VK_ENTER);
	   	  rob.keyPress(KeyEvent.VK_ENTER);
	   	  rob.keyRelease(KeyEvent.VK_ENTER);
	   	  rob.keyPress(KeyEvent.VK_ENTER);
	   	  rob.keyRelease(KeyEvent.VK_ENTER);
	   	  //getObjectById("dayBookExportPdfDownloadId").click();
	   	 
	   	  

	}

}
