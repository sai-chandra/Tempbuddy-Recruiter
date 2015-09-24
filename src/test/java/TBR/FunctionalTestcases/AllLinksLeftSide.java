package TBR.FunctionalTestcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AllLinksLeftSide extends FunctionalSuiteBase{
	
	@Test
	public void allLinksLeftSide(){
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	login_Valid();
	WebElement block = getObject("leftSideBarX");
	List<WebElement> block1 =block.findElements(By.tagName("a")); 
	System.out.println(block1.size());
	for(int i=0;i<=block1.size();i++){
	System.out.println(block1.get(i).getText());
	}
	}

}
