package TBR.Regression.PayandBill;

import java.sql.SQLException;

import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;

import com.relevantcodes.extentreports.LogStatus;

public class ExportInvoice extends PayandBillRegressionSuitebase {
	@Test
	public void exportInvoices() throws InterruptedException, ClassNotFoundException, SQLException{
		
		logger =report.startTest("ExportInvoice");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
	
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "ExportInvoice"));
		logger.log(LogStatus.PASS, path);
		
		getObject("payBillX").click();
		getObject("invoiceLinkX").click();
		getObject("exportInvoicesX").click();
		
		//moves on to Create New ExportInvoices Wizard Step 1 Export
		//for Invoice Export  Date Starting
		getObjectById("exportDateStartingInvoiceId").click();
		Select monthForExportCandidatesStart = new Select(getObject("exportDateStartingInvoiceMonthX"));
		monthForExportCandidatesStart.selectByVisibleText("Nov");
		Select yearForExportTimesheetstart = new Select(getObject("exportDateStartingInvoiceYearX"));
		yearForExportTimesheetstart.selectByValue("2015");
		getObject("exportDateStartingNov28X").click();
		Thread.sleep(5000);
		
		//For Invoice Export Date Ending
		getObjectById("exportDateEndingInvoiceId").click();
		Select monthForExportCandidatesEnd = new Select(getObject("exportDateEndingInvoiceMonthX"));
		monthForExportCandidatesEnd.selectByVisibleText("Dec");
		Select yearForExportTimesheetend = new Select(getObject("exportDateEndingInvoiceYearX"));
		yearForExportTimesheetend.selectByValue("2016");

		getObject("dec31X").click();
		getObjectById("exportClientNameInvoiceId").sendKeys("Watsons");
		getObjectByLinkText("watsonsClientLt").click();
		
		getObject("exportInvoiceExportX").click();
	}
	
	    @AfterMethod
	    public void screenShot(ITestResult result){
	    	if(result.getStatus()==ITestResult.FAILURE)
	    	{
		String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "ExportInvoice");
		String image = logger.addScreenCapture(screenshot_path);
		logger.log(LogStatus.FAIL, "ExportInvoice", image);
	    	}
	    	report.endTest(logger);
	    	report.flush();
}
}
