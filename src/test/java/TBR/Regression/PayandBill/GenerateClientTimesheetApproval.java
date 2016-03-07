package TBR.Regression.PayandBill;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;

import com.relevantcodes.extentreports.LogStatus;

public class GenerateClientTimesheetApproval extends PayandBillRegressionSuitebase {
	@Test
	public void generateClientTimeSheetApproval() throws InterruptedException{
		
		logger =report.startTest("ExportTimesheets");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "generateClientTimeSheetApproval"));
		logger.log(LogStatus.PASS, path);
	
	getObject("payBillX").click();
	getObject("timeSheetLinkX").click();
	//moving on to Generate Client TimeSheet Approval
    getObject("timesheetGenerateClientApprovalX").click();
    waitForElementId(10, "timesheetByClientNameId");
    //Step 1 Choose Client
    getObjectById("timesheetByClientNameId").sendKeys("Watsons");
    getObjectByLinkText("watsonsClientLt").click();
    getObject("timesheetByClientNextX").click();
    //Step 2 Create New TimeSheet Approval
    getObject("timesheetByClientGenerateX").click();
    
     
   /* waitForElement(10, "successMessageByAssignmentX");
    String SuccessMessage = getObjectText("successMessageByAssignmentX");
    System.out.println(SuccessMessage);
    
    Assert.assertEquals(SuccessMessage, "timesheet\nOperation success");
    System.out.println("success message matched and therefore timsheet is generated successfully");*/
}
	@AfterMethod
	public void screenShot(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE)
		{
	String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "generateClientTimeSheetApproval");
	String image = logger.addScreenCapture(screenshot_path);
	logger.log(LogStatus.FAIL, "generateClientTimeSheetApproval", image);
		}
		report.endTest(logger);
		report.flush();
	}
}
