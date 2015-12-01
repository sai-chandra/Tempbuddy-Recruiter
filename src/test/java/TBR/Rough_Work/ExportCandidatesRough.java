package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class ExportCandidatesRough extends TestBase{

	@Test
	public void exportCandidatesRough() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		 //moving on to payslip for generating new payslip
		getObject("candLinkX").click();
		getObject("exportCandidatesX").click();
		
		/*//for Export candidates Date Starting
		getObjectById("exportExportDateStartingCandidatesId").click();
		Select monthForExportCandidatesStart = new Select(getObject("exportMonthDatePickerCandidatesX"));
		monthForExportCandidatesStart.selectByVisibleText("Sep");
		Select yearForExportTimesheetstart = new Select(getObject("exportYearDatePickerCandidatesX"));
		yearForExportTimesheetstart.selectByValue("2015");
		getObject("dateDatePickerSep3X").click();
		
		//For Export candidates Date Ending
		getObjectById("exportDateEndingCandidatesId").click();
		Select monthForExportCandidatesEnd = new Select(getObject("exportMonthDatePickerCandidatesX"));
		monthForExportCandidatesEnd.selectByVisibleText("Nov");
		Select yearForExportTimesheetend = new Select(getObject("exportYearDatePickerCandidatesX"));
		yearForExportTimesheetend.selectByValue("2015");
		getObject("exportDateStartingNov28X").click();
		//Thread.sleep(2000);
*/		getObject("exportExportX").click();

Thread.sleep(3000);
driver.switchTo().alert().accept();

}
}