package TBR.Rough_Work;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class ForgotPasswordRough extends TestBase{
	
	@BeforeSuite
	public void init() throws IOException{
     initialize();
	}
	
	@Test
	public void forgotPassword(){
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		getObjectByLinkText("forgotPasswordLt").click();
		getObjectById("usernameFpId").sendKeys();
		getObject("nextFpX").click();
		String s = driver.findElement(By.id("username")).getAttribute("placeholder");
		//WebWait("#username");
		System.out.println(s);
		
		
		


}
}