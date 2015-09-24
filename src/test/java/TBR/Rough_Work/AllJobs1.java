package TBR.Rough_Work;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class AllJobs1 extends TestBase {
	
	@Test
	public void allJobs1() throws IOException{
		
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		
//		String a = "html/body/div[2]/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[";
//		String b = "]/td[";
//		String c = "]";
		//String bb = "]/td[2]";
		/*int i=1;
		int j=1;
		*/
		for(int numberOfRows = 1;numberOfRows<11;numberOfRows++){
			for(int numberOfCols = 1;numberOfCols<8;numberOfCols++){
				
				//System.out.println(driver.findElement(By.xpath(a+numberOfRows+b+numberOfCols)));
				System.out.println(driver.findElement(By.xpath("html/body/div[2]/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/table/tbody/tr["+numberOfRows+"]/td["+numberOfCols+"]")).getText());
						
			}
		}
		//getObject("next2x").click();
		
		
	/*	while(i<=10){
			WebElement d = driver.findElement(By.xpath(a+i+bb));
			i++;
			//j++;
			
		
		System.out.println(d.getText());
		//System.out.println("");
		}*/
		
		
	
		
		
		
		
		/*WebElement We = getObject("firstCellUnderTitleX");
		List<WebElement> rows = We.findElements(By.tagName("tr"));
		for(int rownum = 0;rownum<rows.size();rownum++){
	    List<WebElement> columns =  rows.get(rownum).findElements(By.tagName("th"));
	    System.out.println(rows.get(rownum).getText());
	    System.out.println("Number of columns:"+columns.size());
	    
	    for(int cnum = 0;cnum<columns.size();cnum++){
	    	System.out.println(columns.get(cnum).getText());
	    }
		}
		*/
		
		
		
			}
		
}
