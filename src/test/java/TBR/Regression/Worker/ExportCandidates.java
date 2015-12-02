package TBR.Regression.Worker;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ExportCandidates extends CandidateRegressionSuiteBase {
	
	@Test
	public void exportCandidates() throws IOException, InterruptedException{
		browserUrl();
		
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
*/		
		getObject("exportExportX").click();
 }
}