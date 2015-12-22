package TBR.Rough_Work;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class Roster extends TestBase {
	@Test
	public void roster() throws IOException, InterruptedException{
		initialize();
		browserUrl();
		
		String a = "//*[@id='client-rows']/div/div/div/div[";
		String b = "]/table/thead/tr/td[1]";
		/*Scheduling*/
		waitForElementClickable(10, "rosterViewIconX");
		getObject("rosterViewIconX").click();
		waitForElement(10, "rosterViewTabX");
		getObject("rosterViewTabX").click();
		Thread.sleep(4000);
		System.out.println("here");
        List<WebElement> elementList = driver.findElements(By.cssSelector("client.col-xs-2.expand"));
        
		Iterator<WebElement> iter = elementList.iterator();
		
		while(iter.hasNext())
		{
			WebElement element = iter.next();
			if(element.getText() == "Watsons")//getAttribute("data-original-title").equals("Watsons")){
			element.click();
			System.out.println("clicked");
			//element.click();
			//System.out.println("clicked");
			break;
			}
			
		}
		
		
		/*getObjectByLinkText("watsons").click();
		WebElement element = driver.findElement(By.cssSelector(".client.col-xs-2.expand"));
		String attvalue = element.getAttribute("data-original-title");
		
		driver.findElement(By.xpath("//*[@data-toogle='tooltip'][@data-original-title='Watsons']")).click();
		//waitForElement(10, "rosterWatsonsX");
	String abc = getObjectText("rosterViewClientX");
	System.out.println(abc);
        driver.quit();
		//getObjectByLinkText("watsons").click();
*/	}
	

