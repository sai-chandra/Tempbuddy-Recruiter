package TBR.Regression.PayandBill;

import java.sql.SQLException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;

import com.relevantcodes.extentreports.LogStatus;

	public class GenerateNewTimeSheetByClient extends PayandBillRegressionSuitebase {
		public static String jobTitle = "testtimesheet";
		@Test
		public void generateNewTimesheetByClient() throws InterruptedException, ClassNotFoundException, SQLException{
			
			logger =report.startTest("GenerateNewTimeSheetByClient");
			
			/*browserUrl() opens up a browser, goes to Staging url & performs login*/
			browserUrl();
			
			logger.log(LogStatus.INFO, "Browser started");
			String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "GenerateNewTimeSheetByClient"));
			logger.log(LogStatus.PASS, path);
			
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
			@AfterMethod
			public void screenShot(ITestResult result){
			if(result.getStatus()==ITestResult.FAILURE)
			{
				String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "GenerateNewTimeSheetByClient");
				String image = logger.addScreenCapture(screenshot_path);
				logger.log(LogStatus.FAIL, "GenerateNewTimeSheetByClient", image);
			}
			report.endTest(logger);
			report.flush();
		}
	}

