package TBR.Regression.PayandBill;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateNewTimesheetByAssignment extends PayandBillRegressionSuitebase {
	
	public static String jobTitle = "AddJob";
	@Test
	public void generateNewTimesheetByAssignment() throws InterruptedException{
		browserUrl();
		
		//clicks on Pay & Bill and then clicks on Timesheets
		payBilltoTimesheet();
		
		//clicks on generate Timesheet
		waitForElementClickable(10, "timesheetGenerateX");
		getObject("timesheetGenerateX").click();
		
		//moves on to Step 1- Filtering By Assignment
		LOGS.debug("moves on to Step 1- Filtering By Assignment");
		System.out.println("moves on to Step 1- Filtering By Assignment");
		waitForElementClickableId(10, "timesheetCandidateNameId");
		getObjectById("timesheetCandidateNameId").click();
		waitForElementClickableId(10, "timesheetCandidateNameId");
		getObjectById("timesheetCandidateNameId").sendKeys("Sherlock");
		
		getObjectByLinkText("sherlockCandidateLt").click();
		getObjectById("timesheetAssignmentTitleId").click();
		waitForElementClickableId(10, "timesheetAssignmentTitleId");
		getObjectById("timesheetAssignmentTitleId").sendKeys("AddJob");
		
		waitForElementClickableLinkText(10, "AddJobLt");
		getObjectByLinkText("AddJobLt").click();
		getObject("timesheetByAssignmentNextX").click();
		
		//moves on to step 2-choose by date
		LOGS.debug("moves on to step 2-choose by date");
		System.out.println("moves on to step 2-choose by date");
		waitForElementClickableId(10, "timesheetChooseDateRangeId");
		getObjectById("timesheetChooseDateRangeId").click();
		Thread.sleep(3000);
		Select monthByAssignment = new Select(getObject("candidatePassportMonthX"));
		monthByAssignment.selectByVisibleText("Dec");
	    Select yearByAssignment = new Select(getObject("candidatePassportYearX"));
	    yearByAssignment.selectByValue("2015");
	    waitForElementClickable(10, "dec8x");
	    getObject("dec8x").click();
	    getObject("timesheetDateRangeFinishX").click();
	    Thread.sleep(6000);
	    
	   /* //for checking the success message of time sheet generation
	    waitForElement(10, "successMessageByAssignmentX");
	    String SuccessMessage = getObjectText("successMessageByAssignment");
	    System.out.println(SuccessMessage);
	    
	    Assert.assertEquals(SuccessMessage, "timesheet\nOperation success");
	    System.out.println("success message matched and therefore timsheet is generated successfully");
	    Thread.sleep(6000);*/
	    
	    //now click on all time sheets
	    getObject("allTimeSheetsX").click();
	    getObject("timesheetFilterX").click();
	    getObject("timesheetFilterX").sendKeys("AddJob");
	    Thread.sleep(5000);
	    String jobName = getObject("firstSearchedJobX").getText();
	    System.out.println(jobName);
		
	    //checking the searched job and appeared job are same and if same then the time sheet is generated successfully
	    Assert.assertEquals(jobName, jobTitle);
	    System.out.println("matched! timesheet is generated for "+jobName);
	}


}
