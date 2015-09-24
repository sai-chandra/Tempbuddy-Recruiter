package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class ExportTimesheetRough extends TestBase{

	@Test
	public void exportTimesheetrough() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		
		//moving on to payslip for generating new payslip
		getObject("payBillX").click();
		getObject("timeSheetLinkX").click();
		getObject("exportTimesheetsX").click();
		
		//getObjectById("exportFormatTimesheetsId").sendKeys("");
		//for Export Timesheets Date Starting
		getObjectById("exportExportDateStartingId").click();
		Select monthForExportTimesheetstart = new Select(getObject("exportDateStartingMonthX"));
		monthForExportTimesheetstart.selectByVisibleText("Sep");
		Select yearForExportTimesheetstart = new Select(getObject("exportDateStartingYearX"));
		yearForExportTimesheetstart.selectByValue("2015");
		getObject("exportDateStartingSep3X").click();
		
		//For Timesheets Export Date Ending
		getObjectById("exportDateEndingId").click();
		Select monthForExportTimesheetend = new Select(getObject("exportDateEndingMonthX"));
		monthForExportTimesheetend.selectByVisibleText("Sep");
		Select yearForExportTimesheetend = new Select(getObject("exportDateEndingYearX"));
		yearForExportTimesheetend.selectByValue("2015");
		getObjectById("exportCandidateNameId").sendKeys("");
		getObject("exportDateStartingSep3X").click();

}
}