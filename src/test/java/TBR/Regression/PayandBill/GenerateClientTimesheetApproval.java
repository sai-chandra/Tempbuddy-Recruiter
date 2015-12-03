package TBR.Regression.PayandBill;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateClientTimesheetApproval extends PayandBillRegressionSuitebase {
	@Test
	public void generateClientTimeSheetApproval() throws InterruptedException{
	browserUrl();
	
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

}
