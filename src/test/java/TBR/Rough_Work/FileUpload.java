package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class FileUpload extends TestBase {

	
	@Test
	public void fileUpload() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		getObject("settingLinkX").click();
		getObject("settingsBrandingX").click();
		WebElement fileInput = driver.findElement(By.id("headerIcon"));
				//getObjectById("brandingHeaderIconId");
		
		fileInput.sendKeys("C:\\Users\\tempbuddy\\Downloads\\tempBuddy.jpeg");
		String a = getObjectTextId("HeaderIconUpdateMessagesId");
		Assert.assertEquals("File type is not valid. Only png format is allowed", a);
		System.out.println("Header Icon does not take JPEG images");
		
		Thread.sleep(4000);
		
		fileInput.sendKeys("C:\\Users\\tempbuddy\\Downloads\\tempBuddyPng1.png");
		String b = getObjectTextId("HeaderIconUpdateMessagesId");
		Assert.assertEquals("Image size is not valid", b);
		System.out.println("Header Icon only takes PNG images that are of the size 150x40 pixels");
		
		Thread.sleep(4000);
		WebElement fileInput1 = driver.findElement(By.id("headerIcon"));
		fileInput1.sendKeys("C:\\Users\\tempbuddy\\Desktop\\TempBuddyOriginalImages\\DuplicateImages\\icons\\pinkBanner 150 x 40.PNG");
		fileInput1.sendKeys("C:\\Users\\tempbuddy\\Desktop\\TempBuddyOriginalImages\\DuplicateImages\\icons\\pinkBanner 150 x 40.PNG");
		Thread.sleep(10000);
		getObjectById("brandingIconSubmitButtonId").click();
		
	//	Thread.sleep(6000);
	//	driver.navigate().refresh();

	//	Thread.sleep(6000);
	}
}
