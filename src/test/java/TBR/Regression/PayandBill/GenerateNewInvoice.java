package TBR.Regression.PayandBill;

import java.sql.SQLException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;

import com.relevantcodes.extentreports.LogStatus;

public class GenerateNewInvoice extends PayandBillRegressionSuitebase{
	
	public static String clientUsed = "Watsons";
	@Test
	public void generateNewInvoice() throws InterruptedException, ClassNotFoundException, SQLException{
		
		logger =report.startTest("GenerateNewInvoice");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "GenerateNewInvoice"));
		logger.log(LogStatus.PASS, path);
		
		getObject("payBillX").click();
		
		//moving on to Invoice Generation
	    getObject("invoiceLinkX").click();
	    getObject("invoiceGenerateNewInvoiceX").click();
	    Thread.sleep(2000);
	    
	    //moving on to step 1 create new invoice
	    getObjectById("invoiceClientNameId").sendKeys("Watsons");
        waitForElementClickableLinkText(10, "watsonsClientLt");
	    getObjectByLinkText("watsonsClientLt").click();
	    getObjectById("invoiceDateEndingId").click();
	    Select monthInvoice = new Select(getObject("monthDatePickerX"));
	    monthInvoice.selectByVisibleText("Dec");
	    Select yearInvoice = new Select(getObject("yearDatePickerX"));
	    yearInvoice.selectByValue("2015");
	    getObject("dec31X").click();
	    getObject("invoiceGenerateX").click();
	    Thread.sleep(6000);
	    getObject("invoiceAllInvoicesX").click();
	    
	   
	    getObject("invoiceFilterX").click();
	    getObject("invoiceFilterX").sendKeys("Watsons");
	    Thread.sleep(3000);
	    //getObjectByLinkText("watsonsClientLt").click();
	    String FirstClientInvoice = getObjectText("invoiceFirstClientX");
	    System.out.println("client displayed on the screen is "+FirstClientInvoice);
	    Assert.assertEquals(FirstClientInvoice, clientUsed);
	    System.out.println("clients matched! Invoice generated successfully");

	}
		@AfterMethod
		public void screenShot(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "GenerateNewInvoice");
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "GenerateNewInvoice", image);
		}
		report.endTest(logger);
		report.flush();
	}

}
