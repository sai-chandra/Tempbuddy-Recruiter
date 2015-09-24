package TBR.FunctionalTestcases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.TestUtil;

public class ForgotPassword extends FunctionalSuiteBase {
	
	@DataProvider
	public Object[][] getFpData(){
	return TestUtil.getDataIntoHashTable(excel, "ForgotPassword");
	}
	
	@Test(dataProvider="getFpData")
	public void forgotPassword(Hashtable<String, String> data){
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	getObjectByLinkText("forgotPasswordLt").click();
	getObjectById("usernameFpId").sendKeys(data.get("fpusername"));
	getObject("nextFpX").click();
	driver.quit();
	//explicitWaitTextAlert("Please fill out this field.");
	
	
	
	
	
	
	
	}
	

}
