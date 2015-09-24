package TBR.Rough_Work;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class CandidateManualCheckInRough extends TestBase{
	public static WebDriver driver1 = null;
	
	@Test
	public void candidateManualCheck() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		System.out.println("logged in Recruiters Portal");
		driver1 = new FirefoxDriver();
		driver1.get("https://staging.tempbuddy.com");
		//login_Candidate();
		  driver1.findElement(By.id("username")).sendKeys("temp-buddy");
		  driver1.findElement(By.id("password")).sendKeys("qwerty1");
		  driver1.findElement(By.id("_submit")).click();
		System.out.println("in worker portal");
		Thread.sleep(3000);
		driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
		Thread.sleep(10000);
		driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
		Thread.sleep(5000);
		driver1.close();
		System.out.println("closed driver1");
		getObject("clientsLinkx").click();
		//getObject("addNewClientX").click();
		//Thread.sleep(5000);
		driver.quit();
		System.out.println("closed driver");
		
		
		/*Set<String> windows = driver.getWindowHandles();
		String recruiterHandle = driver.getWindowHandle();
		
		//opening another window using javascript
		((JavascriptExecutor)driver).executeScript("window.open();");
		Set<String> window2 = driver.getWindowHandles();
		window2.removeAll(windows);
		String workerHandle = (String) window2.toArray()[0];
		driver.switchTo().window(workerHandle);
		driver.get("https://staging.tempbuddy.com");
		login_Candidate();
		System.out.println("in worker portal");
		driver.switchTo().window(recruiterHandle);
		System.out.println("back on recruiter portal");*/
		
	}

}
