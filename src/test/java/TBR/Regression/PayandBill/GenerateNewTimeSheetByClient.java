package TBR.Regression.PayandBill;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

	public class GenerateNewTimeSheetByClient extends PayandBillRegressionSuitebase {
		public static String jobTitle = "testtimesheet";
		@Test
		public void generateNewTimesheetByClient() throws InterruptedException{
			browserUrl();
			
			//clicks on Pay & Bill and then clicks on Timesheets
			payBilltoTimesheet();
			
			//clicks on generate Timesheet
			waitForElementClickable(10, "timesheetGenerateX");
			getObject("timesheetGenerateX").click();
			
			//clicks on By Client
			waitForElementClickable(10, "timesheetByClientX");
			getObject("timesheetByClientX").click();
			
			//moves on to Step 1- Filtering By Client
			LOGS.debug("moves on to step 1- Filtering By Client");
			System.out.println("moves on to step 1- Filtering By Client");
			Thread.sleep(2000);
			getObjectById("timesheetClientNameId").sendKeys("Watsons");
			waitForElementClickableLinkText(10, "watsonsClientLt");
			getObjectByLinkText("watsonsClientLt").click();
			getObject("timesheetClientNextX").click();
			
			//moves on to Step 2 Choose Date Range
			LOGS.debug("moves on to step 2- Choose date Range");
			System.out.println("moves on to step 2- Choose date Range");
			waitForElementClickableId(10, "timesheetByClientDateRangeId");
			getObjectById("timesheetByClientDateRangeId").click();
			Thread.sleep(3000);
			Select monthByAssignment = new Select(getObject("timesheetByClientMonthX"));
			monthByAssignment.selectByVisibleText("Dec");
		    Select yearByAssignment = new Select(getObject("timesheetByClientYearX"));
		    yearByAssignment.selectByValue("2015");
		    getObject("dec5X").click();
		    getObject("timesheetByClientFinishX").click();
		    Thread.sleep(6000);
		    
		    //now click on all time sheets
		    getObject("allTimeSheetsX").click();
		    getObject("timesheetFilterX").click();
		    getObject("timesheetFilterX").sendKeys("testtimesheet");
		    Thread.sleep(8000);
		    String jobName = getObject("firstSearchedJobX").getText();
		    System.out.println(jobName);
			
		    //checking the searched job and appeared job are same and if same then the time sheet is generated successfully
		    Assert.assertEquals(jobName, jobTitle);
		    System.out.println("matched! timesheet is generated for "+jobName);
	}
	}

