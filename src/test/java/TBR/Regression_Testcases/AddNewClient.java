package TBR.Regression_Testcases;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;
/*for adding a New Client*/
public class AddNewClient extends RegressionSuiteBase{
	@DataProvider
	public Object[][] getAddNewClientData(){
    return TestUtil.getDataIntoHashTable(ClientsExcel, "AddNewClient");
	}
	@Test(dataProvider="getAddNewClientData")
	public void addNewClient(Hashtable<String, String>data) throws InterruptedException{
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		getObject("clientsLinkx").click();
		getObject("addNewClientX").click();
		
		/*on Step 1 Basic information*/
		waitForElementId(15, "nameClientId");
		getObjectById("nameClientId").sendKeys(data.get("Name"));
		String clientName = getObjectById("nameClientId").getAttribute("value");
		System.out.println("the name of the client is" +clientName);
		getObjectById("addressClientId").sendKeys(data.get("Address"));
		getSelectedByText("sectorClientId", "Banking");
		getObjectById("phoneClient").sendKeys(data.get("PhoneNumber"));
		getObjectById("phoneExClientId").sendKeys(data.get("Extension"));
		getObjectById("refClient").sendKeys(data.get("ExternalRef"));
		getObject("nextClientX").click();
		System.out.println("success on Step 1 Basic Information");
		
		//moves on to step 2 Contact information
		getObject("addContactX").click();
		System.out.println("moves on to Add contact screen");
		waitForElementId(10, "acNameId");
		getObjectById("acNameId").sendKeys(data.get("AddContactName"));
		getObjectById("acCompanyPosId").sendKeys(data.get("CompanyPosition"));
		getObjectById("acEmailId").sendKeys(data.get("AddContactEmail"));
		getObjectById("acPhoneId").sendKeys(data.get("AddContactPhone"));
		getObjectById("acExtenId").sendKeys(data.get("AddContactExten"));
		getObjectById("acAlterPhone1").sendKeys(data.get("AddContactAlternativePhone"));
		getObjectById("acAlterPhone2").sendKeys(data.get("AddContactAlternativePhone1"));
		getObject("addContactButtonX").click();
		getObject("nextS2X").click();
		System.out.println("success on Step 2 Contact Information");
		
		/*moves on to Create New Client Step 3: Additional Locations*/
		System.out.println("success on Step 3 Additional Location");
	    getObject("addLocS3X").click();
	    waitForElementId(10, "clientCreatLocationLabelId");
        getObjectById("clientCreatLocationLabelId").sendKeys(data.get("AddLocationLabel"));
        getObjectById("clientCreateAddressId").sendKeys(data.get("AddClientAddress"));
        getObjectById("clientCreatePhonesId").sendKeys(data.get("AddClientsPhones"));
        getObject("clientCreateAddX").click();
        getObjectByCss("finishS3Css").click();
	    Thread.sleep(5000);
	    driver.navigate().refresh();
	    /*moves on to dash board*/
	    
	    getObject("clientsLinkx").click();
	    System.out.println("waited");
	    //explicitWaitXpath("allClientsX");
	    getObject("allClientsX").click();
	    // getObject("allClientsX").click();
	    //getObjectByLinkText("allClientsLt").click();
	    Thread.sleep(10000);
	    getObject("clientSearchFieldX").click();
	    getObject("clientSearchFieldX").sendKeys(clientName);
	    
	    Thread.sleep(5000);
	    String clientAppearedOnScreen = getObject("clientMatchSearchX").getText();
	    System.out.println("the client appeared on the screen is " +clientAppearedOnScreen);
	    Assert.assertEquals(clientAppearedOnScreen, clientName);
	    System.out.println("new client is created successfully");
	    
	    }
	
	@AfterMethod
	public void screenShot(){
		CaptureScreenShot.captureScreenShot(driver, "AddNewClient");
	}
}

