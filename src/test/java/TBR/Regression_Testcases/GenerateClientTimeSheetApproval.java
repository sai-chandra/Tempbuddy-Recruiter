package TBR.Regression_Testcases;

import org.testng.annotations.Test;

public class GenerateClientTimeSheetApproval extends RegressionSuiteBase {
	
	@Test
	public void generateClientTimeSheetApproval(){
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	login_Valid();
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
}
}