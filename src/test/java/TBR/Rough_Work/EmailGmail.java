package TBR.Rough_Work;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class EmailGmail {
	
	WebDriver driver = new FirefoxDriver();
 
	@Test
	public void emailGmail(){
	
	driver.get("https://gmail.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	//driver.findElement(By.id("signInGmailId")).click();
	driver.findElement(By.id("Email")).sendKeys("s.padmanabuni@tempbuddy.co");
	driver.findElement(By.id("next")).click();
	driver.findElement(By.id("Passwd")).sendKeys("tempbuddy");
	driver.findElement(By.id("signIn")).click();
	//String a = "- Dear tempBuddy, Thank you for your application, we can now confirm you that you have been assigned";
	//driver.findElement(By.linkText("- Dear tempBuddy, Thank you for your application, we can now confirm you that you have been assigned")).click();
	//driver.findElement(By.cssSelector(".y2")).click();
	
	List<WebElement> li = driver.findElements(By.cssSelector(".y2"));
	System.out.println("webElement text are as follows:");
	for(WebElement webElement:li){
		System.out.println(webElement.getText());
	}
	System.out.println(li.size());
	//driver.findElements(By.name("TempBuddy information")).c
	driver.findElement(By.linkText("- Dear tempBuddy, Thank you for your application, we can now confirm you that you have been assigned")).click();
	
}
}