package TBR.Regression.Client;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;

public class AddNewClient extends ClientRegressionSuiteBase{
	@DataProvider
	public Object[][] getAddNewClientData(){
    return TestUtil.getDataIntoHashTable(ClientsExcel, "AddNewClient");
	}
	@Test(dataProvider="getAddNewClientData")
	public void addNewClient(Hashtable<String, String>data) throws InterruptedException, ClassNotFoundException, SQLException{
		
		logger =report.startTest("AddNewClient");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "AddNewClient"));
		logger.log(LogStatus.PASS, path);
		
		getObject("clientsLinkx").click();
		waitForElementClickable(10, "addNewClientX");
		getObject("addNewClientX").click();
		
		/*on Step 1 Basic information*/
		waitForElementId(20, "nameClientId");
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
		waitForElementClickable(10, "addContactX");
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
		System.out.println("hi");
		//clickAction("addContactButtonX");
		Thread.sleep(4000);
		System.out.println("oye");
		getObject("addContactButtonX1").click();
		System.out.println("hey");
		//waitForElementClickable(10, "nextS2X");
		Thread.sleep(3000);
		clickAction("nextS2X");
		System.out.println("howdy");
		//clickAction("nextS2X");
		//getObject("nextS2X").click();
		System.out.println("success on Step 2 Contact Information");
		
		Thread.sleep(4000);
		/*moves on to Create New Client Step 3: Additional Locations*/
		System.out.println("success on Step 3 Additional Location");
		//waitForElementClickable(10, "addLocS3X");
		clickAction("addLocS3X");
	    //getObject("addLocS3X").click();
		//waitForElementClickableId(10, "clientCreatLocationLabelId");
		Thread.sleep(3000);
        getObjectById("clientCreatLocationLabelId").sendKeys(data.get("AddLocationLabel"));
        getObjectById("clientCreateAddressId").sendKeys(data.get("AddClientAddress"));
        getObjectById("clientCreatePhonesId").sendKeys(data.get("AddClientsPhones"));
        getObject("clientCreateAddX").click();
        clickActionCss("finishS3Css");
        //getObjectByCss("finishS3Css").click();
	    Thread.sleep(5000);
	    driver.navigate().refresh();
	    /*moves on to dash board*/
	    
	    getObject("clientsLinkx").click();
	    System.out.println("waited");
	    //explicitWaitXpath("allClientsX");
	    getObject("allClientsX").click();
	    // getObject("allClientsX").click();
	    //getObjectByLinkText("allClientsLt").click();
	    Thread.sleep(20000);
	    getObject("clientSearchFieldX").click();
	    getObject("clientSearchFieldX").sendKeys(clientName);
	    
	    TimeUnit.MINUTES.sleep(1);
	    String clientAppearedOnScreen = getObject("clientMatchSearchX").getText();
	    System.out.println("the client appeared on the screen is" +clientAppearedOnScreen);
	    Assert.assertEquals(clientAppearedOnScreen, clientName);
	    System.out.println("new client is created successfully");
	    
	    //New Test Code:
	    String sqlQuery = "Select * from TempBuddy.Clients ORDER BY id DESC LIMIT 1";
	    statement = con.createStatement();
	    result = statement.executeQuery(sqlQuery);
	    
	    System.out.println("following is the result:");
	    System.out.println(result);
	    
	    //Database Testing:
	    result.next();
	    if(!result.getString("name").equals(data.get("Name")))
	    	System.out.println("Name is stored wrong");
	    else{
	    	System.out.println("Name is stored correct");
	    }
	    
	    
	}
	@AfterMethod
    public void screenShot(ITestResult result){
	if(result.getStatus()==ITestResult.FAILURE)
	{
		String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "AddNewClient");
		String image = logger.addScreenCapture(screenshot_path);
		logger.log(LogStatus.FAIL, "AddNewClient", image);
	}
	report.endTest(logger);
	report.flush();
}
}
