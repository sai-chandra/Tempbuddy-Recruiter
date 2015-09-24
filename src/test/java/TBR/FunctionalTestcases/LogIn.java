package TBR.FunctionalTestcases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.TestUtil;

public class LogIn extends FunctionalSuiteBase {
	
	@DataProvider
	public Object[][] getLoginData(){
	return TestUtil.getDataIntoHashTable(excel, "LogIn");
	}
	
	@Test(dataProvider = "getLoginData")
	public void logIn(Hashtable<String, String> data){
	//web driver is initialized and opens selected browser	
	openBrowser();
	//selects the url specified in CONFIG properties file
	driver.get(CONFIG.getProperty("testSiteName"));
	//login with credentials
	getObjectById("usernameId").sendKeys(data.get("usernames"));
	getObjectById("passwordId").sendKeys(data.get("passwords"));
	getObjectById("logInId").click();
	//signOut
	//getObject("signOutLinkX").click();
	//getObject("signOutX").click();
	//should return back to login page
	}
}
