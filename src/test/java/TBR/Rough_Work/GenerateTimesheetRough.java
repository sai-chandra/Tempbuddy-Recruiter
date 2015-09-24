package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class GenerateTimesheetRough extends TestBase{
	public String jobTitle = "Sep3-2";
	@Test
	public void generateTimeSheet() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		getObject("payBillX").click();
		getObject("timeSheetLinkX").click();
		getObject("timesheetGenerateX").click();
		//moves on to Step 1- Filtering By Assignment
		getObjectById("timesheetCandidateNameId").click();
		Thread.sleep(3000);
		getObjectById("timesheetCandidateNameId").sendKeys("Sherlock");
	    getObjectByLinkText("sherlockCandidateLt").click();
		/*Select candidate = new Select(getObject("timesheetCandidateNameId"));
		candidate.getFirstSelectedOption().click();
*/		
        getObjectById("timesheetAssignmentTitleId").click();
		Thread.sleep(3000);
		getObjectById("timesheetAssignmentTitleId").sendKeys("Sep3-2");
		
		getObjectByLinkText("sep3-2JobLt").click();
		getObject("timesheetByAssignmentNextX").click();
		//moves on to step 2-choose by date
		getObjectById("timesheetChooseDateRangeId").click();
		Thread.sleep(3000);
		Select monthByAssignment = new Select(getObject("candidatePassportMonthX"));
		monthByAssignment.selectByVisibleText("Sep");
	    Select yearByAssignment = new Select(getObject("candidatePassportYearX"));
	    yearByAssignment.selectByValue("2015");
	    getObject("dateDatePickerSep3X").click();
	    getObject("timesheetDateRangeFinishX").click();
	    
		Thread.sleep(8000);
	    
	    //now click on all time sheets
	    getObject("allTimeSheetsX").click();
	    getObject("timesheetFilterX").click();
	    getObject("timesheetFilterX").sendKeys("Sep3-2");
	    Thread.sleep(5000);
	    String jobName = getObject("firstSearchedJobX").getText();
	    System.out.println(jobName);
		//checking the searched job and appeared job are same and if same then the time sheet is generated successfully
	    Assert.assertEquals(jobName, jobTitle);
	    System.out.println("matched! timesheet is generated for "+jobName);
	}

}
