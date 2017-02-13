package TBR.Regression.PayandBill;

import java.sql.SQLException;

import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;

import com.relevantcodes.extentreports.LogStatus;

public class ExportPayslip extends PayandBillRegressionSuitebase{
		
		@Test
		public void exportPayslip() throws InterruptedException, ClassNotFoundException, SQLException{
			
		logger =report.startTest("ExportPayslip");
			
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "ExportPayslip"));
		logger.log(LogStatus.PASS, path);
		
		 //moving on to payslip for generating new payslip
		getObject("payBillX").click();
		getObject("payslipLinkX").click();
		waitForElementClickable(10, "exportPayslipX");
		getObject("exportPayslipX").click();
		
		//for payslip Export  Date Starting
		getObjectById("exportDateStartingPayslipId").click();
		Select monthForExportCandidatesStart = new Select(getObject("exportMonthDatePickerPayslipX"));
		monthForExportCandidatesStart.selectByVisibleText("Aug");
		Select yearForExportTimesheetstart = new Select(getObject("exportYearDatePickerPayslipX"));
		yearForExportTimesheetstart.selectByValue("2015");
		getObject("exportDateStartingAug20X").click();
		Thread.sleep(5000);
		
		//For payslip Export Date Ending
		getObjectById("exportDateEndingPayslipId").click();
		Select monthForExportCandidatesEnd = new Select(getObject("exportMonthDatePickerPayslipX"));
		monthForExportCandidatesEnd.selectByVisibleText("Dec");
		Select yearForExportTimesheetend = new Select(getObject("exportYearDatePickerPayslipX"));
		yearForExportTimesheetend.selectByValue("2015");

		getObject("dec31X").click();
		getObjectById("exportPayslipCandidateNameId").sendKeys("Sherlock");
		getObjectByLinkText("sherlockCandidateLt").click();
		
		//click on Next Step 1
		getObjectByCss("exportNextCss").click();
		
		//moving on to Create New ExportPaySlips Step 2 
		//getObjectById("exportStyleSheetPayslipId").sendKeys("");
		getObjectByCss("exportPayslipExportCss").click();
		}
		
		@AfterMethod
	    public void screenShot(ITestResult result){
	    	if(result.getStatus()==ITestResult.FAILURE)
	    	{
		String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "ExportPayslip");
		String image = logger.addScreenCapture(screenshot_path);
		logger.log(LogStatus.FAIL, "ExportPayslip", image);
	    	}
	    	report.endTest(logger);
	    	report.flush();
		}
}