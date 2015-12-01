package TBR.Regression_Testcases;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateNewTimesheetByClient extends RegressionSuiteBase {
	public static String jobTitle = "locked";
	@Test
	public void generateNewTimesheetByClient() throws InterruptedException{
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		getObject("payBillX").click();
		getObject("timeSheetLinkX").click();
		getObject("timesheetGenerateX").click();
		
		//moves on to Step 1- Filtering By Client
		getObject("timesheetByClientX").click();
		getObjectById("timesheetClientNameId").sendKeys("Watsons");
		waitForElementClickableLinkText(10, "watsonsClientLt");
		getObjectByLinkText("watsonsClientLt").click();
		getObject("timesheetClientNextX").click();
		
		//moves on to Step 2 Choose Data Range
		getObjectById("timesheetByClientDateRangeId").click();
		Thread.sleep(3000);
		Select monthByAssignment = new Select(getObject("timesheetByClientMonthX"));
		monthByAssignment.selectByVisibleText("Nov");
	    Select yearByAssignment = new Select(getObject("timesheetByClientYearX"));
	    yearByAssignment.selectByValue("2015");
	    getObject("timesheetNov28X").click();
	    getObject("timesheetByClientFinishX").click();
	    Thread.sleep(6000);
	    
	    //now click on all time sheets
	    getObject("allTimeSheetsX").click();
	    getObject("timesheetFilterX").click();
	    getObject("timesheetFilterX").sendKeys("locked");
	    Thread.sleep(8000);
	    String jobName = getObject("firstSearchedJobX").getText();
	    System.out.println(jobName);
		
	    //checking the searched job and appeared job are same and if same then the time sheet is generated successfully
	    Assert.assertEquals(jobName, jobTitle);
	    System.out.println("matched! timesheet is generated for "+jobName);
}
}