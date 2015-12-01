package TBR.Regression_Testcases;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateNewTimsheetByAssignment extends RegressionSuiteBase {
	public static String jobTitle = "qwertyjob";
	@Test
	public void generateNewTimesheetByAssignment() throws InterruptedException{
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		getObject("payBillX").click();
		getObject("timeSheetLinkX").click();
		getObject("timesheetGenerateX").click();
		
		//moves on to Step 1- Filtering By Assignment
		getObjectById("timesheetCandidateNameId").click();
		waitForElementClickableId(10, "timesheetCandidateNameId");
		getObjectById("timesheetCandidateNameId").sendKeys("Sherlock");
		
		getObjectByLinkText("sherlockCandidateLt").click();
		getObjectById("timesheetAssignmentTitleId").click();
		waitForElementClickableId(10, "timesheetAssignmentTitleId");
		getObjectById("timesheetAssignmentTitleId").sendKeys("qwertyjob");
		
		getObjectByLinkText("qwertyJobLt").click();
		getObject("timesheetByAssignmentNextX").click();
		//moves on to step 2-choose by date
		getObjectById("timesheetChooseDateRangeId").click();
		Thread.sleep(3000);
		Select monthByAssignment = new Select(getObject("candidatePassportMonthX"));
		monthByAssignment.selectByVisibleText("Nov");
	    Select yearByAssignment = new Select(getObject("candidatePassportYearX"));
	    yearByAssignment.selectByValue("2015");
	    getObject("timesheetNov28X").click();
	    getObject("timesheetDateRangeFinishX").click();
	    Thread.sleep(6000);
	    
	    //now click on all time sheets
	    getObject("allTimeSheetsX").click();
	    getObject("timesheetFilterX").click();
	    getObject("timesheetFilterX").sendKeys("qwertyjob");
	    Thread.sleep(5000);
	    String jobName = getObject("firstSearchedJobX").getText();
	    System.out.println(jobName);
		
	    //checking the searched job and appeared job are same and if same then the time sheet is generated successfully
	    Assert.assertEquals(jobName, jobTitle);
	    System.out.println("matched! timesheet is generated for "+jobName);
	}

}
