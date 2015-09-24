package TBR.FunctionalTestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInError extends FunctionalSuiteBase{
	
	@Test
	public void logInErrorMsg() throws InterruptedException{
		openBrowser();
		//selects the url specified in CONFIG properties file
		driver.get(CONFIG.getProperty("testSiteName"));
		//login with credentials
		getObjectById("usernameId").sendKeys("invalid");
		getObjectById("passwordId").sendKeys("invalid");
		getObjectById("logInId").click();
		Thread.sleep(1000);
		//element gets the text of the wrong message, prints and compares whether they are equal or not
		WebElement element = getObject("log_invalidX");
		String strng = element.getText();
		System.out.println(strng);
		//compareStrings(strng, "bad dgaghah");
		Assert.assertEquals("Bad credentials", strng);
		}
	
	//passing usernames and password with empty fields
	@Test
	public void emptyFields(){
	openBrowser();
	//selects the url specified in CONFIG properties file
	driver.get(CONFIG.getProperty("testSiteName"));
	//login with credentials
	getObjectById("usernameId").sendKeys("");
	getObjectById("passwordId").sendKeys("");
	getObjectById("logInId").click();

	}

}
