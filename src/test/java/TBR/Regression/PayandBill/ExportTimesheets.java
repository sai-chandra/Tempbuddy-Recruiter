package TBR.Regression.PayandBill;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExportTimesheets extends PayandBillRegressionSuitebase{
	@Test
	public void exportTimesheetrough() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		
		//get the current window handle
		String parentHandle = driver.getWindowHandle();
				
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
		getObject("dateDatePickerSep3X").click();
		
		//For Timesheets Export Date Ending
		/*getObjectById("exportDateEndingId").click();
		Select monthForExportTimesheetend = new Select(getObject("exportDateEndingMonthX"));
		monthForExportTimesheetend.selectByVisibleText("Sep");
		Select yearForExportTimesheetend = new Select(getObject("exportDateEndingYearX"));
		yearForExportTimesheetend.selectByValue("2015");*/
		getObjectById("exportCandidateNameId").sendKeys("tempBuddy");
		getObjectByLinkText("candidateName1Step6Lt").click();
		getObjectByCss("exportExportTimesheetCss").click();
		
		for (String winHandle : driver.getWindowHandles()){
			//switch focus of webdriver to the next found window handle(that's your newly opened window)
			driver.switchTo().window(winHandle);
			}
			
			String message = getObjectText("exportNothingToExport");
			System.out.println(message);
			
			Assert.assertEquals(message, "Nothing to export");
			System.out.println("success, nothing to export");
			}
}
